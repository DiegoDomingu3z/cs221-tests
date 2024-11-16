
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

        Node<T> newNode = new Node<T>(element);
        newNode.setNextNode(head);
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<T>(element);
        if (isEmpty()) { // or size==0
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
        Node<T> newNode = new Node<T>(element);
        newNode.setNextNode(currentNode.getNextNode());
        currentNode.setNextNode(newNode);
        if (newNode.getNextNode() == null) {
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
        } else if (index == size) {
            addToRear(element);
        } else {
            Node<T> curNode = head;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.getNextNode();
            }
            Node<T> newNode = new Node<T>(element);
            newNode.setNextNode(curNode.getNextNode());
            curNode.setNextNode(newNode);
            size++;
            modCount++;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T retVal = head.getElement();
        head = head.getNextNode();
        if (head == null) {
            tail = null;
        }
        size--;
        modCount++;
        return retVal;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T retVal;
        if (size == 1) {
            retVal = head.getElement();
            head = null;
            tail = null;
        } else {
            Node<T> curNode = head;
            while (curNode.getNextNode() != tail) {
                curNode = curNode.getNextNode();
            }
            retVal = tail.getElement();
            curNode.setNextNode(null);
            tail = curNode;
        }
        size--;
        modCount++;
        return retVal;
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T retVal;
        if (element.equals(head.getElement())) {
            retVal = head.getElement();
            head = head.getNextNode();
            if (head == null) {
                tail = null;
            }
        } else {
            Node<T> currentNode = head;
            while (currentNode.getNextNode() != null && !currentNode.getNextNode().getElement().equals(element)) {
                currentNode = currentNode.getNextNode();
            }
            if (currentNode.getNextNode() == null) {
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
        T retVal;
        if (index == 0) {
            retVal = removeFirst();
        } else {
            Node<T> curNode = head;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.getNextNode();
            }
            retVal = curNode.getNextNode().getElement();
            curNode.setNextNode(curNode.getNextNode().getNextNode());
            if (curNode.getNextNode() == null) {
                tail = curNode;
            }
            size--;
            modCount++;
        }
        return retVal;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> curNode = head;

        for (int i = 0; i < index; i++) {
            curNode = curNode.getNextNode();
        }

        curNode.setElement(element);

        modCount++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> curNode = head;
        for (int i = 0; i < index; i++) {
            curNode = curNode.getNextNode();
        }
        return curNode.getElement();
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
        return indexOf(target) != -1;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (T element : this) {
            sb.append(element.toString());
            sb.append(", ");
        }
        if (size() > 0) {
            sb.delete(sb.length() - 2, sb.length()); 
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new SLLIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    /**
     * Iterator class to use with IUSLL to keep encapsulation. Do not need to create
     * <T>
     */
    private class SLLIterator implements Iterator<T> {
        private Node<T> iterNextNode;
        private Node<T> lastReturnedNode;
        private boolean canRemove;
        private int iterModCount;

        public SLLIterator() {
            iterNextNode = head;
            lastReturnedNode = null;
            canRemove = false;
            iterModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return iterNextNode != null;
        }

        @Override
        public T next() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturnedNode = iterNextNode;
            iterNextNode = iterNextNode.getNextNode();
            canRemove = true;
            return lastReturnedNode.getElement();
        }

        @Override
        public void remove() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!canRemove) {
                throw new IllegalStateException();
            }
            if (lastReturnedNode == head) {
                head = iterNextNode;
                if (head == null) {
                    tail = null;
                }
            } else {
                Node<T> prevNode = head;
                while (prevNode.getNextNode() != lastReturnedNode) {
                    prevNode = prevNode.getNextNode();
                }
                prevNode.setNextNode(iterNextNode);
                if (iterNextNode == null) {
                    tail = prevNode;
                }
            }
            lastReturnedNode = null;
            canRemove = false;
            size--;
            modCount++;
            iterModCount++;
        }

    }

}