package ua.com.globallogic.basecamp.sergiichuk.queue.doublyLinkedList;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E> {
    private class Iter implements Iterator<E> {
	protected Node<E> nextNode;
	protected Node<E> lastReturnedNode = null;;
	protected int currentPos;

	private Iter() {
	    nextNode = head;
	}

	@Override
	public boolean hasNext() {
	    return currentPos < DoublyLinkedList.this.size;
	}

	@Override
	public E next() {
	    if (!hasNext())
		throw new NoSuchElementException();
	    lastReturnedNode = nextNode;
	    nextNode = nextNode.next;
	    currentPos++;
	    return lastReturnedNode.currentData;
	}

	@Override
	public void remove() {
	    if (lastReturnedNode == null)
		throw new IllegalStateException();
	    unlink(lastReturnedNode);
	    lastReturnedNode = null;
	    currentPos--;

	}

    }
    private class ListIter extends Iter implements ListIterator<E> {

	private ListIter(int position) {
	    nextNode = getNodeByIndex(position);
	    currentPos = position;
	}

	@Override
	public void add(E e) {
	    lastReturnedNode = null;
	    if (nextNode == null) {
		linkLast(e);
	    } else {
		linkBefore(e, nextNode);
	    }
	    currentPos++;
	}

	@Override
	public boolean hasPrevious() {
	    return currentPos > 0;
	}

	@Override
	public int nextIndex() {
	    return currentPos;
	}

	@Override
	public E previous() {
	    if (!hasPrevious())
		throw new NoSuchElementException();
	    lastReturnedNode = nextNode;
	    nextNode = nextNode.previous;
	    currentPos--;
	    return lastReturnedNode.currentData;
	}

	@Override
	public int previousIndex() {
	    return currentPos - 1;
	}

	@Override
	public void set(E e) {
	    if (lastReturnedNode == null)
		throw new IllegalStateException();
	    lastReturnedNode.currentData = e;
	}

    }
    @SuppressWarnings("hiding")
    private class Node<E> {
	private Node<E> next;
	private Node<E> previous;
	private E currentData;

	public Node(E data) {
	    this.currentData = data;
	}

	public Node(E data, Node<E> prev) {
	    this(data);
	    this.previous = prev;
	}

	public Node(Node<E> prev, E data, Node<E> next) {
	    this(data, prev);
	    this.next = next;
	}
    }

    private Node<E> head;

    private Node<E> tail;

    private int size;

    @Override
    public boolean add(E e) {
	linkLast(e);
	return true;
    }

    @Override
    public void add(int index, E element) {
	checkIndexBounds(index);
	if (index == size)
	    linkLast(element);
	else
	    linkBefore(element, getNodeByIndex(index));
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
	for (E element : c) {
	    linkLast(element);
	}
	return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
	checkIndexBounds(index);
	Node<E> nodeAfterWhatCollectionWillBeAdded = getNodeByIndex(index);
	for (E element : c) {
	    linkBefore(element, nodeAfterWhatCollectionWillBeAdded);
	    nodeAfterWhatCollectionWillBeAdded = nodeAfterWhatCollectionWillBeAdded.next;
	}
	return true;
    }

    private void checkIndexBounds(int index) {
	if (!isInBounds(index)) {
	    throw new IndexOutOfBoundsException(String.format(
		    "Index is out of bounds, index=%d", index));
	}
    }

    @Override
    public void clear() {
	for (Node<E> current = head; current != null;) {
	    Node<E> next = current.next;
	    current.currentData = null;
	    current.next = null;
	    current = next;
	}
	head = tail = null;
	size = 0;
    }

    @Override
    public boolean contains(Object o) {
	return indexOf(o) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
	for (Object element : c) {
	    if (!contains(element))
		return false;
	}
	return true;
    }

    @Override
    public E get(int index) {
	checkIndexBounds(index);
	return getNodeByIndex(index).currentData;
    }

    private Node<E> getNodeByIndex(int index) {
	Node<E> neededNode = null;
	if (index < (size >> 1)) {
	    neededNode = head;
	    for (int i = 0; i < index; i++) {
		neededNode = neededNode.next;
	    }
	} else {
	    neededNode = tail;
	    for (int i = size - 1; i > index; i--) {
		neededNode = neededNode.previous;
	    }
	}
	return neededNode;
    }

    @Override
    public int indexOf(Object o) {
	int index = 0;
	if (o == null) {
	    for (Node<E> current = head; current != null; index++) {
		if (current.currentData == null)
		    return index;
		current = current.next;
	    }
	} else {
	    for (Node<E> current = head; current != null; index++) {
		if (o.equals(current.currentData))
		    return index;
		current = current.next;
	    }
	}
	return -1;
    }

    @Override
    public boolean isEmpty() {
	return size == 0;
    }

    private boolean isInBounds(int index) {
	return (index <= size && index >= 0);
    }

    @Override
    public Iterator<E> iterator() {
	return new Iter();
    }

    @Override
    public int lastIndexOf(Object o) {
	int index = size - 1;
	if (o == null) {
	    for (Node<E> current = tail; current != null; index--) {
		if (current.currentData == null)
		    return index;
		current = current.previous;
	    }
	} else {
	    for (Node<E> current = tail; current != null; index--) {
		if (o.equals(current.currentData))
		    return index;
		current = current.previous;
	    }
	}
	return -1;
    }

    /**
     * Link new Node with data=e before Node nextElement
     * 
     * @param e
     *            data to be attached with new Node
     * @param nextNode
     *            next to new Node element
     */
    private void linkBefore(E e, Node<E> nextNode) {
	final Node<E> previousNode = nextNode.previous;
	final Node<E> currentNode = new Node<E>(previousNode, e, nextNode);
	nextNode.previous = currentNode;
	if (previousNode == null) {
	    head = currentNode;
	} else {
	    previousNode.next = currentNode;
	}
	size++;
    }

    private void linkLast(E e) {
	if (head != null) {
	    final Node<E> last = tail;
	    final Node<E> newLastNode = new Node<E>(e, last);
	    tail = newLastNode;
	    last.next = tail;
	} else {
	    head = new Node<E>(e);
	    tail = head;
	}
	size++;
    }

    @Override
    public ListIterator<E> listIterator() {
	return new ListIter(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
	checkIndexBounds(index);
	return new ListIter(index);
    }

    @Override
    public E remove(int index) {
	checkIndexBounds(index);
	Node<E> nodeToRemove = getNodeByIndex(index);
	E returnedElement = nodeToRemove.currentData;
	unlink(nodeToRemove);
	return returnedElement;
    }

    @Override
    public boolean remove(Object o) {
	if (size == 0) {
	    return false;
	}
	if (o == null) {
	    Node<E> element = head;
	    do {
		if (element.currentData == null) {
		    unlink(element);
		    return true;
		}
		element = element.next;
	    } while (element != null);
	} else {
	    Node<E> element = head;
	    do {
		if (o.equals(element.currentData)) {
		    unlink(element);
		    return true;
		}
		element = element.next;
	    } while (element != null);
	}
	return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
	boolean isModified = false;
	for (Object o : c) {
	    for (Node<E> current = head; current != null; current = current.next) {
		if (o.equals(current.currentData)) {
		    unlink(current);
		    isModified = true;
		}
	    }
	}
	return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
	boolean isModified = false;
	for (Node<E> current = head; current != null; current = current.next) {
	    if (!c.contains(current.currentData)) {
		unlink(current);
		isModified = true;
	    }
	}
	return isModified;
    }

    @Override
    public E set(int index, E element) {
	checkIndexBounds(index);
	Node<E> nodeToSet = getNodeByIndex(index);
	E returnedElement = nodeToSet.currentData;
	nodeToSet.currentData = element;
	return returnedElement;
    }

    @Override
    public int size() {
	return size;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
	if (fromIndex == toIndex || size == 0)
	    return Collections.emptyList();
	if (fromIndex < 0 || toIndex > size || fromIndex > toIndex
		|| fromIndex > size)
	    throw new IndexOutOfBoundsException();

	List<E> list = new DoublyLinkedList<E>();
	for (Node<E> fromNode = getNodeByIndex(fromIndex); fromNode.currentData != getNodeByIndex(toIndex).currentData; fromNode = fromNode.next) {
	    list.add(fromNode.currentData);
	}
	return list;
    }

    @Override
    public Object[] toArray() {
	if (size == 0)
	    return new Object[0];
	Object[] array = new Object[size];
	int i = 0;
	for (Object element : this) {
	    array[i++] = element;
	}
	return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
	if (a.length < size) {
	    a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
	}
	int i = 0;
	for (Object element : this) {
	    a[i++] = (T) element;
	}
	if (a.length > size) {
	    Arrays.fill(a, size, a.length, null);
	}
	return Arrays.copyOf(a, size);
    }

    private E unlink(Node<E> nodeToRemove) {
	final Node<E> previous = nodeToRemove.previous;
	final Node<E> next = nodeToRemove.next;
	final E data = nodeToRemove.currentData;
	if (previous == null) {
	    head = next;
	} else {
	    previous.next = next;
	    nodeToRemove.previous = null;
	}
	if (next == null) {
	    tail = next;
	} else {
	    next.previous = previous;
	    nodeToRemove.next = null;
	}
	nodeToRemove.currentData = null;
	size--;
	return data;
    }

}
