package ua.com.globallogic.basecamp.sergiichuk.singleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author SAVIK
 * 
 * @param <T>
 */
public class SingleLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SingleLinkedList() {
    }

    public T getFirst() {
	return head.currentData;
    }

    public int size() {
	return size;
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public boolean contains(Object o) {
	return indexOf(o) != -1;
    }

    public Iterator<T> iterator() {
	return new SingleLinkedListIterator();
    }

    private void linkLast(T e) {
	if (head != null) {
	    final Node<T> last = tail;
	    final Node<T> newLastNode = new Node<T>(e);
	    tail = newLastNode;
	    last.next = tail;
	} else {
	    head = new Node<T>(e);
	    tail = head;
	}
	size++;
    }

    public void add(T e) {
	linkLast(e);
    }

    private void unlink(Node<T> elementToUnlink, Node<T> previousElement) {
	if (previousElement != null) {
	    previousElement.next = elementToUnlink.next;
	    elementToUnlink.currentData = null;
	    elementToUnlink.next = null;
	    elementToUnlink = null;
	    size--;
	    return;
	}
	Node<T> nextHead = head.next;
	head.currentData = null;
	head.next = null;
	head = nextHead;
	size--;
    }

    public boolean remove(Object o) {
	if (size == 0) {
	    return false;
	}
	if (o == null) {
	    Node<T> element = head;
	    Node<T> previousElement = null;
	    do {
		if (element.currentData == null) {
		    unlink(element, previousElement);
		    return true;
		}
		previousElement = element;
		element = element.next;
	    } while (element != null);
	} else {
	    Node<T> element = head;
	    Node<T> previousElement = null;
	    do {
		if (o.equals(element.currentData)) {
		    unlink(element, previousElement);
		    return true;
		}
		previousElement = element;
		element = element.next;
	    } while (element != null);
	}
	return false;
    }

    public void clear() {
	for (Node<T> current = head; current != null;) {
	    Node<T> next = current.next;
	    current.currentData = null;
	    current.next = null;
	    current = next;
	}
	head = tail = null;
	size = 0;
    }

    public T get(int index) {
	checkIndexBounds(index);
	return getNodeByIndex(index).currentData;
    }

    private Node<T> getNodeByIndex(int index) {
	Node<T> neededNode = head;
	for (int i = 0; i < index; i++) {
	    neededNode = neededNode.next;
	}
	return neededNode;
    }

    private void checkIndexBounds(int index) {
	if (!isInBounds(index)) {
	    throw new IndexOutOfBoundsException(String.format(
		    "Index is out of bounds, index=%d", index));
	}
    }

    private boolean isInBounds(int index) {
	return (index < size && index >= 0);
    }

    public int indexOf(Object o) {
	int index = 0;
	if (o == null) {
	    for (Node<T> current = head; current != null; index++) {
		if (current.currentData == null)
		    return index;
		current = current.next;
	    }
	} else {
	    for (Node<T> current = head; current != null; index++) {
		if (o.equals(current.currentData))
		    return index;
		current = current.next;
	    }
	}
	return -1;
    }

    public int lastIndexOf(Object o) {
	int index = -1;
	int currentPosition = 0;
	if (o == null) {
	    for (Node<T> current = head; current != null; currentPosition++) {
		if (current.currentData == null)
		    index = currentPosition;
		current = current.next;
	    }
	} else {
	    for (Node<T> current = head; current != null; currentPosition++) {
		if (o.equals(current.currentData))
		    index = currentPosition;
		current = current.next;
	    }
	}
	return index;
    }

    private class SingleLinkedListIterator implements Iterator<T> {
	protected Node<T> nextNode;
	protected Node<T> nodeBeforeLastReturnedNode = null;
	protected Node<T> lastReturnedNode = null;;
	protected int currentPos;

	SingleLinkedListIterator() {
	    nextNode = head;
	}

	public boolean hasNext() {
	    return currentPos < SingleLinkedList.this.size;
	}

	public T next() {
	    if (!hasNext())
		throw new NoSuchElementException();
	    if (lastReturnedNode != null)
		nodeBeforeLastReturnedNode = lastReturnedNode;
	    lastReturnedNode = nextNode;
	    nextNode = nextNode.next;
	    currentPos++;
	    return lastReturnedNode.currentData;
	}

	public void remove() {
	    if (lastReturnedNode == null)
		throw new IllegalStateException();
	    unlink(lastReturnedNode, nodeBeforeLastReturnedNode);
	    lastReturnedNode = null;
	    currentPos--;
	}

    }

    @SuppressWarnings("hiding")
    private class Node<T> {
	private Node<T> next;
	private T currentData;

	public Node(T data) {
	    this.currentData = data;
	}
    }
}
