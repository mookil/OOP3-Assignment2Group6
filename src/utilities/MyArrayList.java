package utilities;

import java.util.ArrayList;

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
public class MyArrayList<E> implements ListADT<E> {
	
	/**
	 * This is the object array that will be used for this ADT.
	 * All items/nodes will be stored here.
	 */
	private Object[] list;
	
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
	public MyArrayList() {
		this.list = new Object[DEFAULT_SIZE];
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
	public MyArrayList(int size) {
		this.list = new Object[size];
		this.size = 0;
	}

	
	/**
	 * 
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * 
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
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
	@SuppressWarnings("unchecked")
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
		// TODO Auto-generated method stub
		return null;
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
	 * 
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @todo To be done by: Mikael
	 */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @todo To be done by: Mikael
	 */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Utilize the iterator class
	 * 
	 * @return an Iterator<E> based off of this list
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
