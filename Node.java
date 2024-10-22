
public class Node<T> {

    private T element;
    private Node<T> nextNode;

    /**
     * Initialize a new node with the given element
     */
    public Node(T element) {
        // this.element = element;
        // nextNode = null;
        this(element, null);
    }

    /* Initialize a new node with the given element and next node. */
    public Node(T element, Node<T> nextNode) {
        this.element = element;
        this.nextNode = nextNode;
    }

    /* 
     PUT JAVADOCS
     */
    public T getElement() {
        return element;
    }

    /* 
     PUT JAVADOCS
     */
    public void setElement(T element) {
        this.element = element;
    }
    /* 
     PUT JAVADOCS
     */
    public Node<T> getNextNode() {
        return nextNode;
    }
    /* 
     PUT JAVADOCS
     */
    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

}
