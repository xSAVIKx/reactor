package ua.com.globallogic.basecamp.sergiichuk.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import ua.com.globallogic.basecamp.sergiichuk.queue.ArrayBasedList.ArrayBasedList;

public class ArrayBasedQueue<E> implements Queue<E> {
    private ArrayBasedList<E> elementsArray;

    public ArrayBasedQueue() {
	elementsArray = new ArrayBasedList<>();
    }

    @Override
    public int size() {
	return elementsArray.size();
    }

    @Override
    public boolean isEmpty() {
	return elementsArray.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
	return elementsArray.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
	return elementsArray.iterator();
    }

    @Override
    public Object[] toArray() {
	return elementsArray.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
	return elementsArray.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
	return elementsArray.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
	return elementsArray.containsAll(elementsArray);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
	return elementsArray.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
	return elementsArray.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
	return elementsArray.retainAll(c);
    }

    @Override
    public void clear() {
	elementsArray.clear();
    }

    @Override
    public boolean add(E e) {
	return elementsArray.add(e);
    }

    @Override
    public boolean offer(E e) {
	return elementsArray.add(e);
    }

    @Override
    public E remove() {
	if (elementsArray.isEmpty())
	    throw new NoSuchElementException();
	return elementsArray.remove(0);
    }

    @Override
    public E poll() {
	if (elementsArray.isEmpty())
	    return null;
	return elementsArray.remove(0);
    }

    @Override
    public E element() {
	if (elementsArray.isEmpty())
	    throw new NoSuchElementException();
	return elementsArray.get(0);
    }

    @Override
    public E peek() {
	if (elementsArray.isEmpty())
	    return null;
	return elementsArray.get(0);
    }

}
