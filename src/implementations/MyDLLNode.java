package implementations;

/**
 * Node used in the MyDLL class.
 * Stores data along with references to previous and next nodes.
 */
public class MyDLLNode<E> {

    E data;
    MyDLLNode<E> next;
    MyDLLNode<E> prev;

    public MyDLLNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
