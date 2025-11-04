package utilities;

import java.util.ArrayList;

/**
 * MyArrayList.java
 * 
 * @author 
 * @version 1.0
 * 
 * <p>
 * 
 * 
 */
public class MyArrayList<E> implements ListADT<E> {
	
	/**
	 * This is the ArrayList that will be used for this ADT.
	 * All items/nodes will be stored here.
	 */
	private ArrayList<E> list;
	
	/**
	 * Constructor Method
	 * Initialize the list by creating an ArrayList<E>.
	 */
	public MyArrayList() {
		this.list = new ArrayList<E>(); 
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
	 * 
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method removes an item from the ArrayList given an index.
	 * Index must be greater than 0 and no greater than the ArrayList size.
	 * <p>
	 * Returns the data stored in the item that was removed.
	 * @author Mikael Ly
	 * @date 11/4/2025
	 * 
	 * @param index - index of the item to be removed
	 * @return data of the item that was removed
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// If the index is below 0 or greater than the amount of items in the list,
		// throw an IndexOutOfBoundsException.
		if (index < 0 || index >= this.list.size()) {
			throw new IndexOutOfBoundsException("Index is out of bounds. Index: " + index 
											+ " should be between 0 - " + this.list.size());
		}
		// If index is valid, go ahead with removing the item.
		// Store item data to be returned
		E temp = this.list.get(index);
		
		// Remove the item
		this.list.remove(index);
		
		return temp; // return the data that was removed from the list
	}

	/**
	 * @todo To be done by: Mikael
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
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
	 * @todo To be done by: Mikael
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
