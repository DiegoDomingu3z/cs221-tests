
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * Single linked node based implementation of indexedUnsortedList
 * with a basic Iterator inlcuding remove()
 * 
 */
public class IUSingleLinkedList<T> implements  IndexedUnsortedList<T> {
    private Node<T> head; // technically the only required instance var, but..
    private Node<T> tail;
    private int size;
    private int modCount;

    /* Initializing new empty list */
    public IUSingleLinkedList(){
        this.head = this.tail = null; // cool cascading assignment
        this.size = this.modCount = 0;
    }

    @Override
    public void addToFront(T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addToRear(T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addAfter(T element, T target) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T removeFirst() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T removeLast() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T remove(T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void set(int index, T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int indexOf(T element) {
        Node<T> currentNode = head;
        int currentIndex = 0;
        /* Seach while we still haven't found the node or we have not reached the end of the linked list */
        while (currentNode != null && !currentNode.getElement().equals(element)) { 
            currentNode = currentNode.getNextNode();
            currentIndex++;
        }
        if (currentNode == null) {
            currentIndex = -1;
        }
        return currentIndex;

    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(T element : this){
            str.append(element.toString());
            str.append(", ");
        }
        if (size() > 0){
            str.delete(str.length() - 2, str.length());
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
