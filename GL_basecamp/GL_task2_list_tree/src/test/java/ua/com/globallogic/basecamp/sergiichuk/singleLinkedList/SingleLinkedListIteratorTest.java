package ua.com.globallogic.basecamp.sergiichuk.singleLinkedList;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class SingleLinkedListIteratorTest {
    private List<String> singleLinkedList;
    private Iterator<String> singleLinkedListIterator;

    @Test
    public void testIteratorHasNextTrue() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	singleLinkedList.add(oneString);
	singleLinkedListIterator = singleLinkedList.iterator();

	assertTrue(singleLinkedListIterator.hasNext());
    }

    @Test
    public void testIteratorHasNextFalse() {
	singleLinkedList = new SingleLinkedList<String>();
	singleLinkedListIterator = singleLinkedList.iterator();

	assertFalse(singleLinkedListIterator.hasNext());
    }

    @Test
    public void testIteratorNextOneString() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	singleLinkedList.add(oneString);
	singleLinkedListIterator = singleLinkedList.iterator();

	assertEquals(oneString, singleLinkedListIterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoSuchElementException() {
	singleLinkedList = new SingleLinkedList<String>();
	singleLinkedListIterator = singleLinkedList.iterator();

	singleLinkedListIterator.next();
    }

    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveIllegalStateException() {
	singleLinkedList = new SingleLinkedList<String>();
	singleLinkedListIterator = singleLinkedList.iterator();

	singleLinkedListIterator.remove();
    }

    @Test
    public void testIteratorHasRemoveOneString() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	singleLinkedList.add(oneString);
	singleLinkedListIterator = singleLinkedList.iterator();
	singleLinkedListIterator.next();

	singleLinkedListIterator.remove();
	assertEquals(0, singleLinkedList.size());
    }

}
