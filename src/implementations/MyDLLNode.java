package implementations;

/**
 * Node used in the MyDLL class.
 * Stores data along with references to previous and next nodes.
 * 
 * @author Anchal
 */
public class MyDLLNode<E> {

	/**
	 * Data stored within this node.
	 */
    E data;
    /**
     * Next node
     */
    MyDLLNode<E> next;
    /**
     * Previous Node
     */
    MyDLLNode<E> prev;

    /**
     * Constructor
     * @param data
     */
    public MyDLLNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
