
public class DoubleLinkedNode<T> {

    private T element;
    private Node<T> nextNode;
    private Node<T> previousNode;
    /**
     * Initialize a new node with the given element
     */
    public DoubleLinkedNode(T element) {
        this.element = element;
        nextNode = null;
        previousNode = null;
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
