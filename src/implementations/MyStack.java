package implementations;

import utilities.Iterator;
import utilities.StackADT;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.lang.reflect.Array;

/**
 * StackADT utilizing the MyArrayList resource.
 * 
 * @author Christopher Hanlon, Mikael Ly
 * 
 * @param <E> element type
 */
public class MyStack<E> implements StackADT<E> {
	/**
	 * ArrayList resource for this ADT
	 */
	private MyArrayList<E> arr;
	
	/**
	 * Constructor initializes the underlying ArrayList.
	 */
	public MyStack() {
		this.arr = new MyArrayList<>();
	}

	/**
	 * Push an element to the top of the stack.
	 * 
	 * @param element to push
	 * @throws NullPointerException if element is null
	 */
	@Override
	public void push(E element) throws NullPointerException {
		if (element == null) { throw new NullPointerException("Element cannot be null."); }
		arr.add(element);	
	}

	/**
	 * Removes and returns top element on the stack.
	 * 
	 * @return element on top of the stack that was removed
	 * @throws EmptyStackException if stack is empty
	 * 
	 */
	@Override
	public E pop() throws EmptyStackException {
		// if stack is empty
		if (arr.size() == 0) { throw new EmptyStackException(); }
		
		// remove and return the element on top of stack
		return arr.remove(arr.size() - 1);

	}

	/**
	 * Return the top element on stack without removing
	 * 
	 * @return element on top of the stack
	 */
	@Override
	public E peek() throws EmptyStackException {
		if (arr.size() == 0) { throw new EmptyStackException(); }
		
		// return the element on top of stack
		return arr.get(arr.size() - 1);
	}

	/**
	 * @return true if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		if (arr.size() == 0)
		{
			return true;
		}
		else
		{
		return false;
		}
	}

	/**
	 * @return number of items inside the stack
	 */
	@Override
	public int size() {
		return arr.size();
		
	}

	/**
	 * Clear all items from stack.
	 */
	@Override
	public void clear() {

		arr.clear();
	}

	/**
	 * Checks if stack contains an element.
	 * 
	 * @param element to look for
	 * @return true if found
	 * @throws NullPointerException if element is null.
	 */
	@Override
	public boolean contains(E element) throws NullPointerException {
		return arr.contains(element);
	}

	/**
	 * Custom Iterator method for a Stack
	 */
	@Override
	public Iterator<E> iterator() {
		class StackIterator implements Iterator<E> {
			int index = arr.size() - 1;

			@Override
			public boolean hasNext() {
				return index >= 0;
			}

			@Override
			public E next() throws NoSuchElementException {
				if (!hasNext()) {
					throw new NoSuchElementException("No next value");
				}
				return arr.get(index--);
			}
		}
		return new StackIterator();
	}

	/**
	 * Search for an element and return the 1-based position from the top of the stack.
	 * 
	 * @param element - element to search for
	 * @return position of the element
	 */
	@Override
	public int search(E element){
		// If element is null, cannot be found.
		if (element == null) { return -1; }
		
		int pos = 1; // the 1-based position

		// iterate through list from top to find element
		for (int i = arr.size() - 1; i >= 0; i--)
		{
			// get current element and compare with the element, if equals then return the 1 based position
			E current = arr.get(i);
			if (element.equals(current))
			{
				return pos;
			}
			pos++; // if element not found, increment position
		}
		
		// list has been iterated through and nothing has been found
		return -1;
	}


	/**
	 * Convert to a generic type array given an array to store within.
	 * Converts from top-bottom.
	 * If the holder is too small, creates a new array.
	 * If the holder is too big, clear all elements after this.size by setting holder[size] to null.
	 * 
	 * @param holder array to store converted array
	 * @return converted array
	 * @throws NullPointerException if holder is null
	 */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if (holder == null) { throw new NullPointerException("Holder array cannot be null."); }
		
		int n = arr.size();
		
		// if holder has enough length
		if (holder.length >= n) {
			// iterate through and copy array from top to bottom
			for (int i = 0; i < n; i++) {
				holder[i] = arr.get(n - 1 - i);
			}
			
			// once the holder length starts to exceed length of this list, set position after it to null
			// (frees the space after it)
			if (holder.length > n) {
				holder[n] = null;
			}
			
			return holder;
		}
		
		// if holder is too small
		// allocate new array, copy over contents from this list and return it
		E[] convertArray = Arrays.copyOf(holder, n);
		for (int i = 0; i < n; i++) {
			convertArray[i] = arr.get(n - 1 - i);
		}
		return convertArray;
	}

	/**
	 * Convert to a generic type array.
	 * Converts from top-bottom.
	 * 
	 * @return converted array
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		// create new array to store elements
		E[] convertedArray = (E[]) new Object[arr.size()];
		
		// iterate through and replace all positions with elements from this list
		for (int i = 0; i < arr.size(); i++) {
			convertedArray[i] = arr.get(arr.size() - 1 - i); // start from top, iterate through with i
		}
		
		return convertedArray;
	}

	/**
	 * Indicates whether this stack has a fixed capacity.
	 * @return false because size is dynamic
	 */
	@Override
	public boolean stackOverflow() {
		
		return false; //My stack is always able to grow.
	}

	/**
	 * Compares two stacks if they are equal.
	 * 
	 * @param that - stack to compare to
	 * @return true if equal, false if not
	 */
	@Override
	public boolean equals(StackADT<E> that) {
		// stack to compare to cannot be null
		if (that == null) { return false; }
		// if size is different, they aren't equal.
		if (this.size() != that.size()) { return false; }
		
		// compare elements using iterator
		Iterator<E> it1 = this.iterator();
		Iterator<E> it2 = that.iterator();
		
		// for each element in each list, compare. if at any time an element doesn't match up with the other, return false.
		while (it1.hasNext() && it2.hasNext()) {
			E e1 = it1.next();
			E e2 = it2.next();
			if (!e1.equals(e2)) { return false; }
			
		}
		
		// otherwise, these stacks are equal
		return true;
	}

}
