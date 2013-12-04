package ua.com.globallogic.basecamp.sergiichuk.queue;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ListBasedQueueTest {
    ListBasedQueue<String> stringQueue;
    ListBasedQueue<Integer> intQueue;

    final String oneString = "one";
    final Integer oneInt = 1;
    final String twoString = "two";
    final Integer twoInt = 2;

    @Before
    public void setUp() throws Exception {
	stringQueue = new ListBasedQueue<>();
	intQueue = new ListBasedQueue<>();
    }

    @Test
    public void testRemoveTrue() {
	stringQueue.add(oneString);
	intQueue.add(oneInt);
	assertEquals(oneString, stringQueue.remove());
	assertEquals(oneInt, intQueue.remove());
	assertEquals(0, stringQueue.size());
	assertEquals(0, intQueue.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveNoSuchElementException() {
	stringQueue.remove();
    }

    @Test
    public void testPollTrue() {
	stringQueue.add(oneString);
	intQueue.add(oneInt);
	assertEquals(oneString, stringQueue.remove());
	assertEquals(oneInt, intQueue.remove());
	assertEquals(0, stringQueue.size());
	assertEquals(0, intQueue.size());
    }

    @Test
    public void testPollNullReturned() {
	assertNull(intQueue.poll());
	assertNull(stringQueue.poll());
    }

    @Test
    public void testElementTrue() {
	stringQueue.add(oneString);
	intQueue.add(oneInt);
	assertEquals(oneString, stringQueue.element());
	assertEquals(oneInt, intQueue.element());
    }

    @Test(expected = NoSuchElementException.class)
    public void testElementNoSuchElementException() {
	stringQueue.element();
    }

    @Test
    public void testPeekTrue() {
	stringQueue.add(oneString);
	intQueue.add(oneInt);
	assertEquals(oneString, stringQueue.peek());
	assertEquals(oneInt, intQueue.peek());
    }

    @Test
    public void testPeekNullReturned() {
	assertNull(intQueue.peek());
	assertNull(stringQueue.peek());
    }

}
