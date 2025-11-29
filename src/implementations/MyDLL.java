package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

/**
 * MyDLL.java
 *
 * Doubly Linked List implementation of the ListADT.
 * Uses MyDLLNode to store all items.
 * 
 * @author Anchal
 *
 */
public class MyDLL<E> implements ListADT<E> {

	/**
	 * Head of the DLL.
	 */
    private MyDLLNode<E> head;
    /**
     * Tail of the DLL.
     */
    private MyDLLNode<E> tail;
    /**
     * Number of items in the list.
     */
    private int size;

    /**
     * Constructor
     * Initializes an empty DLL.
     */
    public MyDLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * @return number of items in list.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Clears the list.
     */
    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Adds an element at a specific index.
     * 
     * @param index - index to add at
     * @param toAdd - element to add
     * @return true if added
     * @throws NullPointerException if toAdd is null
     * @throws IndexOutOfBoundsException if index is below 0 or above list size
     */
    @Override
    public boolean add(int index, E toAdd)
            throws NullPointerException, IndexOutOfBoundsException {

        if (toAdd == null)
            throw new NullPointerException("Cannot add null.");

        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException("Invalid index.");

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        // empty list
        if (size == 0) {
            head = tail = newNode;
        }
        // add at head
        else if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        // add at tail
        else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        // add in middle
        else {
            MyDLLNode<E> curr = getNode(index);
            MyDLLNode<E> prev = curr.prev;

            prev.next = newNode;
            newNode.prev = prev;

            newNode.next = curr;
            curr.prev = newNode;
        }

        size++;
        return true;
    }

    /**
     * Adds to the end of the list.
     * 
     * @param toAdd - element to add
     * @return true if added
     */
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Cannot add null.");
        return add(size, toAdd);
    }

    /**
     * Adds all items from another list.
     * 
     * @returns boolean if successful
     * @param toAdd - the list to add items from
     * @throws NullPointerException if toAdd is null
     */
    @Override
    public boolean addAll(ListADT<? extends E> toAdd)
            throws NullPointerException {

        if (toAdd == null)
            throw new NullPointerException("List is null.");

//        for (int i = 0; i < toAdd.size(); i++)
//            add(toAdd.get(i));
        // utilize the iterator and while the toAdd array still has items, add them to this list
        Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
        	this.add(it.next());
        }

        return true;
    }

    /**
     * Gets element at index.
     * 
     * @param index to get element at
     * @return element at the index
     * @throws IndexOutOfBoundsException if index is below 0 or above list size
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        return getNode(index).data;
    }

    /**
     * Removes element at index.
     * 
     * @param index to remove at
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index.");

        MyDLLNode<E> curr = getNode(index);
        E removed = curr.data;

        if (size == 1) {              // only node
            head = tail = null;
        } else if (curr == head) {    // remove head
            head = head.next;
            head.prev = null;
        } else if (curr == tail) {    // remove tail
            tail = tail.prev;
            tail.next = null;
        } else {                      // remove middle
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }

        size--;
        return removed;
    }

    /**
     * Remove an element by value.
     */
    @Override
    public E remove(E toRemove) throws NullPointerException {

        if (toRemove == null)
            throw new NullPointerException("Null cannot be removed.");

        MyDLLNode<E> curr = head;

        while (curr != null) {
            if (curr.data.equals(toRemove))
                return removeNode(curr);
            curr = curr.next;
        }

        return null;
    }

    /**
     * Helper for remove(E toRemove).
     * @param curr - current node
     * @return node that was removed
     */
    private E removeNode(MyDLLNode<E> curr) {
        E removed = curr.data;

        if (curr == head && curr == tail) {
            head = tail = null;
        } else if (curr == head) {
            head = head.next;
            head.prev = null;
        } else if (curr == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }

        size--;
        return removed;
    }

    /**
     * Sets a value at a specific index.
     * 
     * @param index - index to set value at
     * @param toChange - new element to change to
     * @throws NullPointerException if toChange is null.
     * @throws IndexOutOfBoundsException if index is below 0 or above list size.
     */
    @Override
    public E set(int index, E toChange)
            throws NullPointerException, IndexOutOfBoundsException {

        if (toChange == null)
            throw new NullPointerException("Cannot set null.");

        MyDLLNode<E> node = getNode(index);
        E old = node.data;
        node.data = toChange;

        return old;
    }

    /**
     * @return true if empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if element exists.
     * 
     * @param toFind - element to find
     * @return true if found, false if not
     * @throws NullPointerException if toFind is null.
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {

        if (toFind == null)
            throw new NullPointerException("Cannot search for null.");

        MyDLLNode<E> curr = head;
        while (curr != null) {
            if (curr.data.equals(toFind))
                return true;
            curr = curr.next;
        }

        return false;
    }

    /**
     * Convert to array (generic version)
     * 
     * @param array to store array at
     * @return new converted array
     * @throws NullPointerException if toHold is empty.
     */
    @SuppressWarnings("unchecked")
	@Override
    public E[] toArray(E[] toHold) throws NullPointerException {

        if (toHold == null)
            throw new NullPointerException();

        if (toHold.length < size) {
            toHold = (E[]) java.lang.reflect.Array
                    .newInstance(toHold.getClass().getComponentType(), size);
        }

        MyDLLNode<E> curr = head;
        int i = 0;

        while (curr != null) {
            toHold[i++] = curr.data;
            curr = curr.next;
        }

        return toHold;
    }

    /**
     * Converts to an Object[].
     * @return array converted to Object[]
     */
    @Override
    public Object[] toArray() {

        Object[] arr = new Object[size];
        MyDLLNode<E> curr = head;

        int i = 0;
        while (curr != null) {
            arr[i++] = curr.data;
            curr = curr.next;
        }

        return arr;
    }

    /**
     * Iterator for DLL.
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            MyDLLNode<E> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public E next() {
                if (curr == null)
                    throw new NoSuchElementException();
                E data = curr.data;
                curr = curr.next;
                return data;
            }
        };
    }

    /** Helper: get node at index 
     * 
     * @param index - index to get element at
     * @return DLLNode at index, null if not found
     * @throws IndexOutOfBoundsException if index is below 0 or above list size
     * */
    private MyDLLNode<E> getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        MyDLLNode<E> curr = head;
        for (int i = 0; i < index; i++)
            curr = curr.next;

        return curr;
    }
}
