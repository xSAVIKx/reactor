package ua.com.globallogic.basecamp.sergiichuk.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import ua.com.globallogic.basecamp.sergiichuk.queue.doublyLinkedList.DoublyLinkedList;

public class ListBasedQueue<E> implements Queue<E> {
    private DoublyLinkedList<E> elementsList;

    public ListBasedQueue() {
	elementsList = new DoublyLinkedList<>();
    }

    @Override
    public int size() {
	return elementsList.size();
    }

    @Override
    public boolean isEmpty() {
	return elementsList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
	return elementsList.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
	return elementsList.iterator();
    }

    @Override
    public Object[] toArray() {
	return elementsList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
	return elementsList.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
	return elementsList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
	return elementsList.containsAll(elementsList);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
	return elementsList.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
	return elementsList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
	return elementsList.retainAll(c);
    }

    @Override
    public void clear() {
	elementsList.clear();
    }

    @Override
    public boolean add(E e) {
	return elementsList.add(e);
    }

    @Override
    public boolean offer(E e) {
	return elementsList.add(e);
    }

    @Override
    public E remove() {
	if (elementsList.isEmpty())
	    throw new NoSuchElementException();
	return elementsList.remove(0);
    }

    @Override
    public E poll() {
	if (elementsList.isEmpty())
	    return null;
	return elementsList.remove(0);
    }

    @Override
    public E element() {
	if (elementsList.isEmpty())
	    throw new NoSuchElementException();
	return elementsList.get(0);
    }

    @Override
    public E peek() {
	if (elementsList.isEmpty())
	    return null;
	return elementsList.get(0);
    }

}
