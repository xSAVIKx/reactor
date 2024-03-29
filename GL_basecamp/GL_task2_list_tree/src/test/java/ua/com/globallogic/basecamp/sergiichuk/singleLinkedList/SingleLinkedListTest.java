package ua.com.globallogic.basecamp.sergiichuk.singleLinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SingleLinkedListTest {
    private SingleLinkedList<String> singleLinkedList;

    @Test
    public void testSizeOne() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	singleLinkedList.add(oneString);
	assertEquals(1, singleLinkedList.size());
    }

    @Test
    public void testContainsTrue() {
	singleLinkedList = new SingleLinkedList<String>();
	final String hello = "hello";
	singleLinkedList.add(hello);
	assertTrue(singleLinkedList.contains(hello));
    }

    @Test
    public void testContainsFalse() {
	singleLinkedList = new SingleLinkedList<String>();
	final String hello = "hello";
	assertFalse(singleLinkedList.contains(hello));
    }

    @Test
    public void testAddT() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	singleLinkedList.add(oneString);
	assertEquals(1, singleLinkedList.size());
	assertEquals(oneString, singleLinkedList.get(0));
    }

    @Test
    public void testRemoveObjectTrue() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	singleLinkedList.add(oneString);
	assertTrue(singleLinkedList.remove(oneString));
	assertEquals(0, singleLinkedList.size());
    }

    @Test
    public void testRemoveObjectFalse() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	assertFalse(singleLinkedList.remove(oneString));
	assertEquals(0, singleLinkedList.size());
    }

    @Test
    public void testClear() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	singleLinkedList.add(oneString);
	singleLinkedList.add(oneString);
	singleLinkedList.add(oneString);
	singleLinkedList.clear();
	assertEquals(0, singleLinkedList.size());
    }

    @Test
    public void testAddRemoveAddRemoveAdd() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	final String hello = "hello";
	singleLinkedList.add(oneString);
	singleLinkedList.remove(oneString);
	assertEquals(0, singleLinkedList.size());
	singleLinkedList.add(hello);
	assertEquals(1, singleLinkedList.size());
	singleLinkedList.add(oneString);
	assertEquals(2, singleLinkedList.size());
	assertEquals(hello, singleLinkedList.get(0));
    }

    @Test
    public void testIndexOf() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	final String nullString = null;
	singleLinkedList.add(oneString);
	singleLinkedList.add(nullString);
	singleLinkedList.add(nullString);
	singleLinkedList.add(oneString);
	assertEquals(4, singleLinkedList.size());
	assertEquals(1, singleLinkedList.indexOf(nullString));
    }

    @Test
    public void testLastIndexOf() {
	singleLinkedList = new SingleLinkedList<String>();
	final String oneString = "one";
	final String nullString = null;
	singleLinkedList.add(oneString);
	singleLinkedList.add(nullString);
	singleLinkedList.add(nullString);
	singleLinkedList.add(oneString);
	assertEquals(2, singleLinkedList.lastIndexOf(nullString));
    }
}
