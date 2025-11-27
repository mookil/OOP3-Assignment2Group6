package implementations;

import utilities.Iterator;
import utilities.ListADT;
import java.util.NoSuchElementException;
import java.util.Arrays; // This is only for the toArray and resizing methods

/**
 * MyArrayList.java
 * 
 * This is an ADT imitating the ArrayList library in java.util.*. 
 * Implements the ListADT<E> from ListADT.java, along with all given functions.
 * Stores all items/nodes within an Object[].
 * This ADT should be of a flexible size, capable of adding/removing with no size limit.
 * <p>
 * 
 * 
 * @author Mikael Ly, Christopher Hanlon
 * @version 1.0
 * <p>
 * 
 * 
 */
public class MyArrayList<E> implements ListADT<E>{
	
	/**
	 * This is the object array that will be used for this ADT.
	 * All items/nodes will be stored here.
	 */
	private E[] list;
	
	/**
	 * This is the default size of the array that will be initialized in the constructor.
	 */
	private final static int DEFAULT_SIZE = 10;
	
	/**
	 * This is used to keep track of how many items are currently within the list.
	 * Keep in mind arrays start at 0, and tracks how many items there are.
	 * Therefore, size will always be +1 to the largest index in array.
	 */
	private int size;
	
	/**
	 * Constructor Method
	 * Initialize the list by creating an Object[] with default size.
	 * 
	 * @author Mikael Ly
	 * @date 11/5/2025
	 */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.list = (E[]) new Object[DEFAULT_SIZE];
		this.size = 0;
	}
	
	/**
	 * Constructor Method if given a size.
	 * Initialize the list by creating an Object[] with given size.
	 * (truthfully, size shouldn't matter as this ADT will be of flexible size)
	 * 
	 * @author Mikael Ly
	 * @date last changes made: 11/5/2025
	 * 
	 * @param size = size of the array you want to make
	 */
	@SuppressWarnings("unchecked")
	public MyArrayList(int size) {
		this.list = (E[]) new Object[size];
		this.size = 0;
	}

	
	/**
	 * @return the count of items within this list..
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
	
		this.list = (E[]) new Object[DEFAULT_SIZE];
		this.size = 0;
	}

	
	/**
	 * Add an element at a given index.
	 * 
	 * @param index = index to add element at
	 * @param toAdd - element to add
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		// if the element to be added is null
		if (toAdd == null) { throw new NullPointerException("Element to be added cannot be null."); }
		
		// if the index is out of bounds
		if (index < 0 || index > size) { throw new IndexOutOfBoundsException("Index must be in between 0 and " + size + "."); }
		
		// If resizing is required: create a new bigger array with doubled size and copy the array over
		if (size == list.length) {
			E[] newList = (E[]) new Object[list.length * 2];
			System.arraycopy(list, 0, newList, 0, size);
			list = newList;
		}
		
		// Shift over the array to the right by 1 at index to accomodate the insertion
		System.arraycopy(list, index, list, index + 1, size - index);
		list[index] = toAdd; // add the element to the new list
		size++; // increment size by 1
		
		return true;
		
		
//		if (index < size && index >= 0)
//		{
//			E[] newList = (E[]) new Object[size + 1];
//			for (int i = 0; i < index; i++)
//			{
//				newList[i] = list[i];				
//			}
//			
//			newList[index] = toAdd;
//			
//			for (int i = index; i < size + 1; i++)
//			{
//				newList[i + 1] = list[i];
//			}
//			
//			list = newList;
//			this.size = size+1;
//			return true;
//		}
//		else
//		{
//		return false;
//		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		
		if (toAdd != null)
		{
			E[] newList = (E[]) new Object[size + 1];
			
			for (int i = 0; i <= size; i++)
			{
				newList[i] = list[i];
			}
			
			newList[size+1] = toAdd;
			list = newList;
			size = size + 1;
			return true;
		}
		
		else {
		return false;
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		
		if (toAdd != null)
		{
			E[] newList = (E[]) new Object[size + toAdd.size()];
			
			for (int i = 0; i <= size; i++)
			{
				newList[i] = list[i];
			}
			
			for (int i = 1; i <= toAdd.size() + 1; i++)
			{
				newList[size + i] = toAdd.get(i-1);
			}
			list = newList;
			size = size + toAdd.size();
			return true;
		}
		else {
		return false;
		}
	}

	/**
	 * This returns an element in the array given an index.
	 * (suppressed warning unchecked data-type cast)
	 * 
	 * @author Mikael Ly
	 * @date last changes made: 11/5/2025
	 * 
	 * @param index - index of the item user wants to retrieve
	 * @return data of the item at the given index
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Implement get method
		
		// If the index is below 0 or greater than the amount of items in the list,
		// throw an IndexOutOfBoundsException.
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Index is out of bounds. Index: " + index 
											+ " should be between 0 - " + this.size());
		}
		
		// If index valid, return the item at the given index.
		return (E) list[index];
	}

	/**
	 * This method removes an item from the list given an index.
	 * Index must be greater than 0 and no greater than the list size.
	 * <p>
	 * Returns the data stored in the item that was removed.
	 * <p>
	 * The way this function works is that it pushes the item to be removed all the way to 
	 * the end of the list, and then sets it to null.
	 * 
	 * @author Mikael Ly
	 * @date last changes made: 11/5/2025
	 * 
	 * @param index - index of the item to be removed
	 * @return data of the item that was removed
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// If the index is below 0 or greater than the amount of items in the list,
		// throw an IndexOutOfBoundsException.
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Index is out of bounds. Index: " + index 
											+ " should be between 0 - " + this.size());
		}
		
		// If the list is empty, return null because theres nothing to remove.
		if (isEmpty()) {
			return null;
		}
				
		// If index is valid, go ahead with removing the item.
		// Store item data to be returned
		E temp = get(index);
		
		// Remove the item by shifting all items (to the right of index) to the left by 1
		// (for starting point of index, iterate through til the end of the list)
		for (int i = index; i < size - 1; i++) {
			this.list[i] = this.list[i+1]; // replace current element at pointer with the next element
		}
		
		this.list[size - 1] = null; // remove the element (which was pushed all the way to the end)
		
		return temp; // return the data that was removed from the list
	}

	/**
	 * Removes an element from the list given an element.
	 * <p>
	 * This implements a sorting algorithm to compare through all elements in the Array, 
	 * and removes the given element from the array.
	 * 
	 * @author Mikael Ly
	 * @date last changes made: 11/5/2025
	 * 
	 * @param toRemove - the element to remove from the list
	 * @return element that was just removed, {@code null} if not found
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		// TODO do what da summary says
		
		// Given element cannot be null, if null, throw an exception.
		if (toRemove == null) {
			throw new NullPointerException("Null element cannot be removed.");
		}
		
		// If the list is empty, return null because theres nothing to remove.
		if (isEmpty()) {
			return null;
		}
		
		// iterate through the list to find the element
		for (int i = 0; i < size; i++) {
			if (toRemove.equals(this.list[i])) { // check if the element at this pointer is equal to the given element
				return remove(i); // call the remove(index) method to remove that element at that index
			}
		}
		
		return null; // if code made it to this point, element was not found within the list
	}

	/**
	 * 
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		
		if (index <= size && index >= 0 && toChange != null)
		{
			E element = (E) list[index];
			list[index] = toChange;
			return element;
		}
		
		else
		{
		return null;
		}
	}

	/**
	 * Returns whether the list is empty or not. (if size is greater than 0, it's not empty.)
	 * 
	 * @author Mikael Ly
	 * @date last changes made: 11/5/2025
	 * 
	 * @return {@code true} if empty, {@code false} if item stil contains items.
	 */
	@Override
	public boolean isEmpty() {
		// ternary operator that returns a bool based on condition
		// (condition) ? if condition true : if condition false
		return (this.size > 0) ? false : true;
	}

	/**
	 * Searches the list for a given element.
	 * 
	 * @return true if found, false if not.
	 * @param toFind - the element to search for in the list
	 * @throws NullPointerException if toFind is null.
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) { throw new NullPointerException("Element to find cannot be null."); }
		
		for (int i = 0; i <= size; i++)
		{
			if (list[i].equals(toFind))
			{
				return true;
			}
		}
		return false;

	}

	/**
	 * Copies the elments of this list into a given array.
	 * If array is too small, a new array is created.
	 * 
	 * @param toHold - Array that these elements are to be stored
	 * @return a typed array containing the elements of this ArrayList
	 * @throws NullPointerException - if array is null
	 */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		// if array is null, throw NullPointerException
		if (toHold == null) { throw new NullPointerException("Array cannot be null."); }
		
		// if array is less than size, increase the size of the given array to the size of this list.
		if (toHold.length < size) {
			toHold = Arrays.copyOf(toHold, size);
		}
		
		// Copy elements over to the new array
		System.arraycopy(list, 0, toHold, 0, size);
		
		// If the toHold array ends up bigger than this list, set the first unused slot to null.
		if (toHold.length > size){
			toHold[size] = null;
		}
		
		// Return the array.
		return toHold;
	}

	/**
	 * Return this array list as an Object[].
	 * @return convertedArray
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(list, size);
	}

	/**
	 * Utilize the iterator class
	 * 
	 * @return an Iterator<E> based off of this list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> iterator() {
		@SuppressWarnings("hiding")
		class ListIterator<E> implements Iterator<E>
		{
			int position = 0;
			
			@Override
			public boolean hasNext()
			{
				if (position < size)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
			@Override
			public E next()
			{
				if (this.hasNext())
				{
					position++;
					return (E) list[position - 1];
				}
				else
				{
					throw new NoSuchElementException("No Next Value");
				}
			}
		}
		return new ListIterator<E>();
	}

}
