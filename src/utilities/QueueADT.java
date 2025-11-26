package utilities;

/**
 * QueueADT.java
 *
 * @author Chris Hanlon
 * @version 1.0, 2025-10-22
 *  
 * <p>
 * The <code>QueueADT</code> interface is designed to be used as a basis for 
 * the Queue data structure that will be developed in the CPRG304 E2025 class
 * at SAIT. 
 * </p>
 * 
 * This is an interface that describes a list data type where insertions can only
 * be made at the end of the list and removals can only be done at the beginning
 * of the list.
 * 
 * @param E The elements that the list holds.
 */

public interface QueueADT<E>{

	/**
	 * Inserts an Element to the end of the queue.
	 * 
	 * <p><b>Precondition: </b>A queue has been created.</p>
	 * <p><b>Postcondition: </b>The Element is added to the end of the queue.</p>
	 * 
	 * @param object The Element you are trying to insert into the queue.
	 * @throws IllegalArgumentException The element you are trying to insert is not
	 * compatible with the queue.
	 */
	public void enqueue(E object);
	
	/**
	 * Removes the first Element in the queue and returns that Element.
	 * 
	 * 
	 * @return the first Element in the queue
	 * @throws NullPointerException if there are no elements in the queue.
	 */
	public E dequeue();
	
	public E dequeueAll();
	
	public E peek();
	
	public int size();
	
	public E[] toArray(E[] array);
	
	E[] toArray();
	
	public boolean isFull();
	
	public boolean contains(E item);
	
	public int search(E item);
	
	
	
	/**
	 * If the queue is null then it returns a boolean value of True
	 * 
	 * @return A boolean value depending on if the queue is null or not
	 */
	public boolean isEmpty();
	
	public Iterator<E> iterator();
}
