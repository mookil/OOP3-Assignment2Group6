package implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import exceptions.EmptyQueueException;

/**
 * XMLParser.java
 * 
 * This class parses XML files and validates their structure using custom data structures.
 * It uses MyStack to track opening tags and ensure proper nesting,
 * and MyQueue to store and report errors found during parsing.
 * 
 * Takes in an argument.
 * java -jar XMLParser.jar -f<input.txt>
 * 
 * @author Christopher Hanlon, Mikael Ly
 * @version 1.0
 */
public class XMLParser {
	/**
	 * stack of tags
	 */
	private MyStack<String> tagStack;
	/**
	 * queue of error statements
	 */
	private MyQueue<String> errorQueue;
	/**
	 * current line
	 */
	private int lineNumber;
	/**
	 * current file path
	 */
	private String currentFile;
	/**
	 * if this line is in CDATA
	 */
	private boolean inCdata = false;
	
	/**
	 * Constructor initializes the stack and queue for parsing.
	 */
	public XMLParser() {
		this.tagStack = new MyStack<>();
		this.errorQueue = new MyQueue<>();
		this.lineNumber = 0;
		this.currentFile = "";
	}
	
	/**
	 * Parses an XML file and validates its structure.
	 * 
	 * @param filePath the path to the XML file to parse
	 * @return true if the XML is valid, false otherwise
	 */
	public boolean parseFile(String filePath) {
		this.currentFile = filePath;
		this.lineNumber = 0;
		this.tagStack.clear();
		
		try { BufferedReader reader = new BufferedReader(new FileReader(filePath)); 
			String line;
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				parseLine(line.trim());
			}
			
				while (!tagStack.isEmpty()) {
					String unclosedTag = tagStack.pop();
					errorQueue.enqueue("ERROR: Unclosed tag <" + unclosedTag + "> detected.");
				}

			
		} catch (IOException e) {
			errorQueue.enqueue("ERROR: Could not read file: " + filePath);
			return false;
		}
		
		return errorQueue.isEmpty();
	}
	
	/**
	 * Parses a single line of XML content.
	 * 
	 * @param line the line to parse
	 */
	private void parseLine(String line) {
		int i = 0;
		
		while (i < line.length()) {
			if (inCdata) {
				int end = line.indexOf("]]>", i);
				if (end == -1) { 
					break; 
				} else {
					inCdata = false;
					i = end + 3;
					continue;
				}
			}
			
			// look for tag or CDATA start
			int cdataStart = line.indexOf("<![CDATA[", i);
			// find the next tag
			int tagStart = line.indexOf('<', i);
			
			// if CDATA starts before next tag, enter CDATA mode
			if (cdataStart != -1 && (tagStart == -1 || cdataStart < tagStart)) {
				inCdata = true;
				i = cdataStart + 9;
				continue;
			}
			
			
			if (tagStart == -1) {
				break; // No more tags in this line
			}
			
			int tagEnd = line.indexOf('>', tagStart);
			
			if (tagEnd == -1) {
				errorQueue.enqueue("ERROR at line " + lineNumber + ": Unclosed tag angle bracket.");
				break;
			}
			
			String tag = line.substring(tagStart + 1, tagEnd);
			processTag(tag);
			
			i = tagEnd + 1;
		}
	}
	
	/**
	 * Processes an individual XML tag.
	 * 
	 * @param tag the tag content (without angle brackets)
	 */
	private void processTag(String tag) {		
		String trimmed = tag.trim();
		
		// ignore processing instructions
		if (trimmed.startsWith("?") && trimmed.endsWith("?")) {
			return; // Self-closing tags don't need to be tracked
		}
		
		// ignore comments
		if (trimmed.startsWith("!--")) {
			return;
		}
		
		// ignore declarations
		if (trimmed.startsWith("!")) {
			return;
		}
		
		// Check for self-closing tag
		if (trimmed.endsWith("/")) {
			return; // Self-closing tags don't need to be tracked
		}
		
		// Check for closing tag
		if (trimmed.startsWith("/")) {
			String closingTagName = extractTagName(trimmed.substring(1));
			handleClosingTag(closingTagName);
		} else {
			// Opening tag
			String openingTagName = extractTagName(trimmed);
			tagStack.push(openingTagName);
		}
	}
	
	/**
	 * Extracts the tag name from tag content (removes attributes).
	 * 
	 * @param tagContent the full tag content
	 * @return the tag name only
	 */
	private String extractTagName(String tagContent) {
		// Remove trailing whitespace and attributes
		int spaceIndex = tagContent.indexOf(' ');
		if (spaceIndex != -1) {
			return tagContent.substring(0, spaceIndex).trim();
		}
		return tagContent.trim();
	}
	
	/**
	 * Handles a closing tag by matching it with the most recent opening tag.
	 * 
	 * @param closingTagName the name of the closing tag
	 */
	private void handleClosingTag(String closingTagName) {
		if (tagStack.isEmpty()) {
			errorQueue.enqueue("ERROR at line " + lineNumber + ": Closing tag </" 
					+ closingTagName + "> found with no matching opening tag.");
			return;
		}
		
		// normal match
		if (closingTagName.equals(tagStack.peek())) {
			tagStack.pop();
			return;
		}
		
		// mismatch + resync
		while (!tagStack.isEmpty() && !tagStack.peek().equals(closingTagName)) {
			String expected = tagStack.pop();
			errorQueue.enqueue("ERROR at line " + lineNumber
					+ ": Mismtached closing tag </" 
					+ closingTagName
					+ ">, expected </"
					+ expected 
					+ ">.");
		}
		
		// matching opener
		if (!tagStack.isEmpty() && tagStack.peek().equals(closingTagName)) {
			tagStack.pop();
		} else {
			errorQueue.enqueue("ERROR at line " + lineNumber
					+ ": Closing tag </"
					+ closingTagName
					+ "> found with no matching opening tag.");
		}
		
//		String lastOpened = tagStack.peek();
//		if (!closingTagName.equals(lastOpened)) {
//			errorQueue.enqueue("ERROR at line " + lineNumber + ": Mismatched closing tag </" 
//					+ closingTagName + ">, expected </" + lastOpened + ">.");
//			tagStack.pop();
//			return;
//		}
//		
//		tagStack.pop();
	}
	
	/**
	 * Prints all errors found during parsing.
	 * @throws EmptyQueueException if errorQueue is empty
	 */
	public void printErrors() throws EmptyQueueException {
		if (errorQueue.isEmpty()) {
			System.out.println("No errors found. XML is valid.");
			return;
		}
		
		System.out.println("Errors found in " + currentFile + ":");

		
		while (!errorQueue.isEmpty()) {
				String error = errorQueue.dequeue();
				System.out.println(error);

			}
		}
	
	/**
	 * Main method to test the XML parser.
	 * If no arguments provide, parse the sample files.
	 * If 1 argument provided, parse filepath file. (-fFileName.xml)
	 * If else, print error.
	 * 
	 * @param args command line arguments (expects XML file path)
	 * @throws EmptyQueueException if errorQueue is empty
	 */
	public static void main(String[] args) throws EmptyQueueException {
		if (args.length == 0) {
			
			// Test with sample files
			XMLParser parser1 = new XMLParser();
			System.out.println("Parsing sample1.xml:");
			parser1.parseFile("res/sample1.xml");
			parser1.printErrors();
			
			System.out.println("\n");
			
			XMLParser parser2 = new XMLParser();
			System.out.println("Parsing sample2.xml:");
			parser2.parseFile("res/sample2.xml");
			parser2.printErrors();
			
		} else if (args.length == 1) {
			String filePath = "";
			// iterate through args (should only really have 1 arg)
			for (int i = 0; i < args.length; i++) {
				if (args[i].startsWith("-f")) {
					filePath = args[i].substring(2);
					
				}
			}
			XMLParser parser = new XMLParser();
			
			System.out.println("Parsing: " + filePath);
			parser.parseFile(filePath);
			parser.printErrors();
			
		} else {
			System.out.println("Error, unexpected number of arguments. Please only provide a filepath after -f.");
		}
	}
}
