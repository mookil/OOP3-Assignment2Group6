package utilities;

/**
 * StackADT.java
 * 
 * @author Mikael Ly
 * @version 1.0
 * 
 * <p>
 * A linear ADT that represents a "stack" type.
 * A type of list that allows insertions and removals to be performed
 * only at the front of the list.
 * Enforces last-in-first-out (LIFO) behavior on the list.
 * Think of it as a "stack" of plates.
 * You can't get to the bottom without taking the top plates off first.
 * 
 * <p>
 * Within this data type, it is possible to:
 * 
 * <p>"Push" a element to the top of the stack.
 * If the stack is empty, push the element as the first element in the stack.
 * The element must be a valid, appropriate data-type for this stack.
 * 
 * <p>"Pop" the top-most element from the top of the stack.
 * If there are no elements in the stack, return {@code null}.
 * 
 * <p>Check if the list is "Empty".
 * Return {@code true} if the list is empty.
 * Return {@code false} if the list is empty.
 * 
 * <p>"Peek" at the top-most element on the stack.
 * If there are no elements in the stack, 
 * return {@code null}.
 * 
 * 
 * @param V - the data "value" that this list is associated with
 */
public interface StackADT<E> {
	
	/**
	 * Insert an object to the top of the stack.
	 * If the stack is empty, push the element as the first element in the stack.
	 * 
	 * @param E element - The element to be pushed to the front of the stack.
	 * 
	 * @throws UnsupportedOperationException if {@code push} is not supported by this stack
	 * @throws ClassCastException if the "element" is an inappropriate type for this stack
	 * @throws IllegalArgumentException if some property of the element prevents it from being stored in this stack
	 */
	void push(E element);
	
	/**
	 * Remove the top-most object from the top of the stack.
	 * 
	 * @return the object that was removed off the top of the stack.
	 * 			{@code null} if there are no elements to remove.
	 * 
	 * @throws UnsupportedOperationException if {@code pop} is not supported by this stack
	 * @throws ClassCastException if the "element" is an inappropriate type for this stack
	 */
	E pop();
	
	/**
	 * Return a bool if the stack is empty. 
	 * {@code true} if empty, {@code false} if there are still elements.
	 * 
	 * @return {@code true} if stack is empty,
	 * 			{@code false} if stack still has elements.
	 * 
	 */
	 boolean isEmpty();
	
	/**
	 * Retrieve the top-most element without removing it from the stack.
	 * 
	 * @return the element on the top of the stack.
	 * 			{@code null} if there are no elements to remove.
	 * 
	 * @throws ClassCastException if the "element" is an inappropriate type for this stack
	 */
	E peek();
	
	int size();
	
	void clear();
	
	boolean contains(E element);
	
	Iterator<E> iterator();
	
	int search(E element);
	
	E[] toArray(E[] array);
	
	E[] toArray();
	
	boolean stackOverflow();
	
	

}
