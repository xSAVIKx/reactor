package ua.com.globallogic.basecamp.sergiichuk.queue.ArrayBasedList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayBasedList<E> implements List<E> {
    private int size;
    private E[] data;

    private static final int AMOUNT = 10;

    private class Iter implements Iterator<E> {
	protected int currentPos;
	protected int lastPos = -1;

	@Override
	public boolean hasNext() {
	    return currentPos != ArrayBasedList.this.size;
	}

	@Override
	public E next() {
	    int tmpPos = currentPos;
	    if (tmpPos >= ArrayBasedList.this.size)
		throw new NoSuchElementException();
	    E[] data = ArrayBasedList.this.data;
	    lastPos = tmpPos;
	    return data[currentPos++];
	}

	@Override
	public void remove() {
	    if (lastPos < 0)
		throw new IllegalStateException();
	    ArrayBasedList.this.remove(lastPos);
	    currentPos = lastPos;
	    lastPos = -1;
	}

    }

    /**
     * Implementation of ListIterator
     * 
     * @author Iurii_Sergiichuk
     * 
     */
    private class ListIter extends Iter implements ListIterator<E> {

	private ListIter(int pos) {
	    currentPos = pos;
	}

	@Override
	public void add(E e) {
	    ArrayBasedList.this.add(currentPos, e);
	    currentPos++;
	    lastPos = -1;
	}

	@Override
	public boolean hasPrevious() {
	    return currentPos != 0;
	}

	@Override
	public int nextIndex() {
	    return currentPos;
	}

	@Override
	public E previous() {
	    int tmpPos = currentPos - 1;
	    if (tmpPos < 0)
		throw new NoSuchElementException();
	    E[] data = ArrayBasedList.this.data;
	    lastPos = tmpPos;
	    return data[currentPos--];
	}

	@Override
	public int previousIndex() {
	    return currentPos - 1;
	}

	@Override
	public void set(E e) {
	    if (lastPos < 0)
		throw new IllegalStateException();
	    ArrayBasedList.this.set(lastPos, e);
	}

    }

    @SuppressWarnings("unchecked")
    public ArrayBasedList() {
	data = (E[]) new Object[AMOUNT];
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

    @Override
    public boolean add(E e) {
	if (isFull())
	    increase();
	data[size++] = e;
	return true;
    }

    /**
     * check data is full or not
     * 
     * @return true if data is full
     */
    private boolean isFull() {
	return data.length == size;
    }

    /**
     * Increase data mass capacity
     */
    private void increase() {
	data = Arrays.copyOf(data, size + AMOUNT);
    }

    /**
     * Increase data mass capacity by @param amount
     * 
     * @param amount
     *            of capacity to be increased
     */
    private void increase(int amount) {
	if (amount < 1)
	    throw new IllegalArgumentException(String.format(
		    "amount cannot be zero or less, amount=%d", amount));
	data = Arrays.copyOf(data, size + amount);
    }

    @Override
    public void add(int index, E element) {
	checkIndexBounds(index);
	if (isFull())
	    increase();
	E[] arr2 = Arrays.copyOfRange(data, index, size - index);
	data[index] = element;
	System.arraycopy(arr2, 0, data, index + 1, arr2.length);
	size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(Collection<? extends E> c) {
	E[] tmp = (E[]) c.toArray();
	int newArrAmount = tmp.length;
	if (data.length - size < newArrAmount)
	    increase(newArrAmount);
	System.arraycopy(tmp, 0, data, size, newArrAmount);
	size += newArrAmount;
	return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
	checkIndexBounds(index);
	E[] tmp = (E[]) c.toArray();
	int newArrAmount = tmp.length;
	if (index < size)
	    System.arraycopy(data, index, data, index + newArrAmount, size
		    - index);
	System.arraycopy(tmp, 0, data, index, newArrAmount);
	size += newArrAmount;
	return true;
    }

    @Override
    public void clear() {
	Arrays.fill(data, null);
	size = 0;
    }

    @Override
    public boolean contains(Object o) {
	return indexOf(o) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
	for (Object o : c) {
	    if (!contains(o))
		return false;
	}
	return true;
    }

    @Override
    public E get(int index) {
	checkIndexBounds(index);
	return data[index];
    }

    @Override
    public int indexOf(Object o) {
	if (o == null) {
	    for (int i = 0; i < size; i++) {
		if (data[i] == null)
		    return i;
	    }
	} else {
	    for (int i = 0; i < size; i++) {
		if (o.equals(data[i]))
		    return i;
	    }
	}
	return -1;
    }

    @Override
    public boolean isEmpty() {
	return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
	return new Iter();
    }

    @Override
    public int lastIndexOf(Object o) {
	if (o == null) {
	    for (int i = size - 1; i > 0; i--) {
		if (data[i] == null)
		    return i;
	    }
	} else {
	    for (int i = size - 1; i > 0; i--) {
		if (o.equals(data[i]))
		    return i;
	    }
	}
	return -1;
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

    /**
     * Actually remove element from data
     * 
     * @param index
     *            of needed element
     */
    private void rm(int index) {
	if (index == 0) {
	    data = Arrays.copyOfRange(data, 1, data.length);
	    size--;
	} else {
	    System.arraycopy(data, index, data, index - 1, size);
	    size--;
	}
    }

    @Override
    public E remove(int index) {
	checkIndexBounds(index);
	E tmp = data[index];
	rm(index);
	return tmp;
    }

    @Override
    public boolean remove(Object o) {
	boolean modified = false;
	if (o == null) {
	    for (int i = 0; i < size; i++) {
		if (data[i] == null) {
		    modified = true;
		    rm(i);
		    break;
		}
	    }
	} else {
	    for (int i = 0; i < size; i++) {
		if (data[i] != null)
		    if (o.equals(data[i])) {
			modified = true;
			rm(i);
			break;
		    }
	    }
	}
	return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
	boolean modified = false;
	for (Object element : c) {
	    for (int i = 0; i < size; i++) {
		if (element.equals(data[i])) {
		    remove(i);
		    modified = true;
		}
	    }
	}
	return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
	boolean modified = false;
	for (int i = size - 1; i >= 0; i--)
	    if (!c.contains(data[i])) {
		remove(i);
		modified = true;
	    }
	return modified;
    }

    @Override
    public E set(int index, E element) {
	checkIndexBounds(index);
	E unit = data[index];
	data[index] = element;
	return unit;
    }

    @Override
    public int size() {
	return size;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
	if (fromIndex == toIndex || size == 0)
	    Collections.emptyList();
	else if (fromIndex < 0 || toIndex > size || fromIndex > toIndex
		|| fromIndex > size)
	    throw new IndexOutOfBoundsException();
	List<E> list = new ArrayBasedList<E>();
	for (int i = fromIndex; i < toIndex; i++)
	    list.add(data[i]);
	return list;
    }

    @Override
    public Object[] toArray() {
	return Arrays.copyOf(data, size);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
	if (a.length < size)
	    return (T[]) Arrays.copyOf(data, size, a.getClass());
	System.arraycopy(data, 0, a, 0, size);
	if (a.length > size)
	    a[size] = null;
	return a;
    }

}
