import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/*
 * Double-linked node-based implementation of IndexedUnsortedList
 * supporting both a basic Iterator and ListIterator
 * @author 
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount;

    public IUDoubleLinkedList(){
        head = tail = null;
        size= 0;
        modCount = 0;
    }


    @Override
    public void addToFront(T element) {
        Node<T> newNode = new Node<T>(element);
        newNode.setNextNode(head);
        if(isEmpty()){
            tail = newNode;
        } else {
            head.setPreviousNode(newNode);
        }
        head = newNode;
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToRear'");
    }

    @Override
    public void add(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void addAfter(T element, T target) {
        Node<T> targetNode = head;
        while(targetNode != null && !targetNode.getElement().equals(target)){
            targetNode = targetNode.getNextNode();
        }
        if (targetNode == null) {
            throw new NoSuchElementException();
        }
        Node<T> newNode = new Node<T>(element);
        newNode.setNextNode(targetNode.getNextNode());
        newNode.setPreviousNode(targetNode);
        if(newNode.getNextNode() != null){
            newNode.getNextNode().setPreviousNode(newNode);
        } else {
            tail = newNode;
        }
        modCount++;
        size++;
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public T removeFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
    }

    @Override
    public T removeLast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLast'");
    }

    @Override
    public T remove(T element) {
        if (isEmpty() || !contains(element)) {
            throw new NoSuchElementException();
        } 
        Node<T> targetNode = head;
        while (targetNode != null && !targetNode.getElement().equals(element)){
            targetNode = targetNode.getNextNode();
        }
        targetNode.getNextNode().setNextNode(targetNode.getPreviousNode());


        size--;
        modCount++;
        return targetNode.getElement();
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = head;
        for(int i = 0; i < index; i++){
            currentNode = currentNode.getNextNode();
        }
        if (index == 0) {
            head = currentNode.getNextNode();
        } else {
            currentNode.getPreviousNode().setNextNode(currentNode.getNextNode());
        }
        if (currentNode == tail) {
            tail = currentNode.getPreviousNode(); // or tail.getPreviousNode()
        } else {
            currentNode.getNextNode().setPreviousNode(currentNode.getPreviousNode());
        }
        size--;
        modCount++;
        return currentNode.getElement();

    }

    @Override
    public void set(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
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
    
}
