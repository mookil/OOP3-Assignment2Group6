package implementations;

import utilities.Iterator;
import utilities.QueueADT;

import java.lang.NullPointerException;

import exceptions.EmptyQueueException;

/**
 * This is a Queue Abstract Data Type that stores a type of element.
 * Implements the QueueADT interface.
 * Stores all elements within a MyDLL Doubly Linked List method.
 * Automatically grows when capacity is reached.
 * @author Mikael Ly
 * @param <E>
 */
public class MyQueue<E> implements QueueADT<E> {
	// DLL for storage method
	private MyDLL<E> list;
	
	
	/**
	 * No Arguments constructor (utilizes the DEFAULT_CAPACITY)
	 * Initialize a new Queue ADT.
	 * Head and Tail should be set to index 0, along with size (because no items)
	 */
	@SuppressWarnings("unchecked")
	public MyQueue() {
		list = new MyDLL<>();
	}
	

	/**
	 * Add a given object to the next position in the queue (which is the tail)
	 * This should increase the size and change the tail to the added item.
	 * 
	 * @param toAdd - The object to be added to the next position in the queue
	 * @throws NullPointerException if object is null
	 */
	@Override
	public void enqueue(E toAdd) {
		list.add(toAdd); // add at tail
	}

	/**
	 * Removes and returns the element at the front of the queue.
	 * 
	 * @return the front element that was removed
	 * @throws EmptyQueueException if queue is empty
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		// if queue is empty:
		if (isEmpty()) { throw new EmptyQueueException("Queue is empty."); }
		// remove the front element (head)
		return list.remove(0);
	}

	/**
	 * Returns a bool whether this queue is empty.
	 * 
	 * @return true if queue is empty.
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Removes all elements from the queue.
	 */
	@Override
	public void dequeueAll() {
		list.clear();
	}

	/**
	 * Returns the front element without removing it.
	 * 
	 * @return front element
	 * @throws EmptyQueueException if queue is empty
	 */
	@Override
	public E peek() throws EmptyQueueException {
		if (isEmpty()) { throw new EmptyQueueException(); }
		return list.get(0);
	}

	/**
	 * Returns the number of elements in the queue.
	 */
	@Override
	public int size() {
		return list.size();
	}


	/**
	 * Returns whether this queue is full
	 * (but always returns false, since the DLL is dynamic)
	 * @return false
	 */
	@Override
	public boolean isFull() {
		return false;
	}

	/**
	 * Checks if the queue contains a given element.
	 * 
	 * @param toFind - the element to be found
	 * @return true if found, false if not
	 * @throws NullPoitnerException if toFind is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		return list.contains(toFind);
	}

	/**
	 * Iterator for MyQueue, from MyDLL.
	 */
	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	/**
	 * Searches for an element and returns its 1-based position.
	 * 
	 * @param toFind - item to search for
	 * @return position of the item, -1 if not found
	 */
	@Override
	public int search(E toFind) {
		// If item is null, return -1 (can't find)
		if (toFind == null) { return -1;}
		
		// iterate through the DLL until item is found (or not)
		for (int i = 0; i < list.size(); i++) {
			if (toFind.equals(list.get(i))) {
				// if item found, return the 1-based position
				return (i + 1);
			}
		}
		
		return -1;
	}

	/**
	 * Convert queue to a typed array.
	 * @param array - array to be converted into a typed array
	 * @throws NullPointerException if the array is null
	 */
	@Override
	public E[] toArray(E[] array) throws NullPointerException {
		return list.toArray(array);
	}

	/**
	 * Converts queue to an Object[].
	 */
	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	/**
	 * Compare this queue with another queue to see if they're the same.
	 * 
	 * @param that - another QueueADT object
	 * @return true if equal, false if not
	 */
	@Override
	public boolean equals(QueueADT<E> that) {
		// If either 'that' is null, or the size of this QueueADT and 'that' QueueADT aren't equal, 
		// return false (because that means they are different)
		if (that == null || this.size() != that.size()) { return false; }
		
		// iterate between both to determine any differences
		Iterator<E> it1 = this.iterator();
		Iterator<E> it2 = that.iterator();
		// For each element in both lists, compare elements and if one of them don't match up, return false (they're different)
		while (it1.hasNext() && it2.hasNext()) {
			E e1 = it1.next();
			E e2 = it2.next();
			if (!e1.equals(e2)) { return false; }
			
		}
		// otherwise, they're the same
		return true;
	}
	

}
