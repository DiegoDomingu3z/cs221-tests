
import java.util.ConcurrentModificationException;
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
    private Node<T> tail; // makes addToRear 0(1), so no brainer
    private int size; // makes size 0(1), so no brainer                      
    private int modCount;

    /* Initializing new empty list */
    public IUSingleLinkedList(){
        this.head = this.tail = null; // cool cascading assignment
        this.size = this.modCount = 0;
    }

    @Override
    public void addToFront(T element) {
        Node<T> newNode = new Node<>(element);
        head = newNode;
        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.setNextNode(head);
            head = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNextNode(newNode);
        }
        tail = newNode;
        size++;
        modCount++;
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        Node<T> currentNode = head;
        while (currentNode != null && !currentNode.getElement().equals(target)) {
            currentNode = currentNode.getNextNode();
        }

        if (currentNode == null) {
            throw new NoSuchElementException();
        }

        Node<T> newNode = new Node<>(element, currentNode.getNextNode());
        currentNode.setNextNode(newNode);
        if (currentNode == tail) {
            tail = newNode;
        }

        size++;
        modCount++;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addToFront(element);
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            Node<T> newNode = new Node<>(element);
            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
            if (newNode.getNextNode() == null) {
                tail = newNode;
            }
            size++;
            modCount++;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T element = head.getElement();
        head = head.getNextNode();
        size--;
        modCount++;

        if (size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T element = tail.getElement();

        if (head == tail) {
            head = tail = null;
        } else {
            Node<T> currentNode = head;
            while (currentNode.getNextNode() != tail) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(null);
            tail = currentNode;
        }

        size--;
        modCount++;
        return element;
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T retVal;
      
        if (head.getElement().equals(element)) {
            retVal = head.getElement();
            head = head.getNextNode();
            if(head == null){ // head was the only node
                tail = null; // there are no nodes
            }

        } else {
            Node<T> currentNode = head;
            while (currentNode.getNextNode() != null && !currentNode.getNextNode().getElement().equals(element)) {
                currentNode = currentNode.getNextNode();
            }
            if (currentNode == tail) {
                throw new NoSuchElementException();
            }
            retVal = currentNode.getNextNode().getElement();
            currentNode.setNextNode(currentNode.getNextNode().getNextNode());
            if (currentNode.getNextNode() == null) {
                tail = currentNode;
            }
        }
            size--;
            modCount++;
            return retVal;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFirst();
        }

        Node<T> currentNode = head;
        Node<T> previousNode = null;
        for (int i = 0; i < index; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }

        previousNode.setNextNode(currentNode.getNextNode());
        if (currentNode == tail) {
            tail = previousNode;
        }

        size--;
        modCount++;
        return currentNode.getElement();
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }

        currentNode.setElement(element);
        modCount++;
    }

    @Override
    public T get(int index) {
        Node<T> currentNode = head;
        int currentIndex = 0;
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        
        while (currentIndex < index) {
            currentNode = currentNode.getNextNode();
            currentIndex++;
        }
        return currentNode.getElement();
    }

    @Override
    public int indexOf(T element) {
        Node<T> currentNode = head;
        int currentIndex = 0;
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
        return (indexOf(target) != -1);
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
        return new SLLIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        throw new UnsupportedOperationException();
    }

    /* Iterator for IUSingleLinkedList */
    private class SLLIterator implements Iterator<T> {
        private Node<T> nextNode;
        private boolean canRemove;
        private int iterModCount;
        /* Initialize Iterator before first element */
        public SLLIterator(){
            nextNode = head;
            canRemove = false;
            iterModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (iterModCount != modCount){
                throw new ConcurrentModificationException();
            }
            return nextNode != null;
        }
        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T retVal = nextNode.getElement();
            nextNode = nextNode.getNextNode();
            canRemove = true;
            return retVal;
        }

        @Override
        public void remove(){
            if (iterModCount != modCount) { // Iterator's existential crisis
                throw new ConcurrentModificationException();
            }
            if(!canRemove){ // is this method call valid
                throw new IllegalStateException();
            }
            canRemove = false;
            if (head.getNextNode() == nextNode) {
                head = nextNode;
                if (head == null){
                    tail = null;
                }
            } else {

                Node<T> prevPrevNode = head;
                while(prevPrevNode.getNextNode().getNextNode() != nextNode){
                    prevPrevNode = prevPrevNode.getNextNode();
                }
                prevPrevNode.setNextNode(nextNode);
                if(nextNode == null){
                    tail = prevPrevNode;
                }
            }
            modCount++;
            iterModCount++;
            size--;
        
        }
        
    }
    
}
