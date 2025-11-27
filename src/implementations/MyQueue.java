package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.QueueADT;

public class MyQueue<E> implements QueueADT<E> {
	
	private final int DEFAULT_CAPACITY = 10;
	
	private E[] elements;
	private int front = 0;
	private int rear = 0;
	private int count = 0;
	private int capacity;
	
	/**
	 * Constructor method for MyQueue (given a size)
	 * 
	 * Create a new QueueADT, initializing with a 
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public MyQueue(int capacity) {
		this.capacity = capacity;
		this.elements = (E[]) new Object[this.capacity];
		this.front = 0;
		this.rear = -1;
		this.count = 0;
	}
	
	/**
	 * Constructor method for MyQueue (not given a size)
	 * 
	 * Create a new QueueADT, initializing with a 
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public MyQueue() {
		this.capacity = DEFAULT_CAPACITY;
		this.elements = (E[]) new Object[this.capacity];
		this.front = 0;
		this.rear = -1;
		this.count = 0;
	}

	/**
	 * 
	 */
	@Override
	public void enqueue(E object) {
		
		
	}

	/**
	 * 
	 */
	@Override
	public E dequeue() {
		
		return null;
	}

	/**
	 * 
	 */
	@Override
	public boolean isEmpty() {
		
		return false;
	}

	/**
	 * 
	 */
	@Override
	public E dequeueAll() {
		
		return null;
	}

	/**
	 * 
	 */
	@Override
	public E peek() {
		
		return null;
	}

	/**
	 * 
	 */
	@Override
	public int size() {
		
		return 0;
	}


	/**
	 * Returns a boolean whether this queue is full or not.
	 */
	@Override
	public boolean isFull() {
		
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean contains(E item) {
		
		return false;
	}

	/**
	 * Iterator over the queue from front to rear (head to tail).
	 * Utilizes the custom QueueIterator class.
	 */
	@Override
	public Iterator<E> iterator() {
		
		return new QueueIterator();
	}
	
	/**
	 * A custom Iterator for QueueADT's.
	 * Extends from an Iterator object.
	 */
	private class QueueIterator implements Iterator<E> {
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E next() throws NoSuchElementException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	/**
	 * 
	 */
	@Override
	public int search(E item) {
		
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public E[] toArray(E[] array) {
		
		return null;
	}

	/**
	 * 
	 */
	@Override
	public E[] toArray() {
		
		return null;
	}

}
