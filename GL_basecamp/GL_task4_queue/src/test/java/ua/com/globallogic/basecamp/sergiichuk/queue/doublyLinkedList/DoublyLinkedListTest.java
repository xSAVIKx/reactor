package ua.com.globallogic.basecamp.sergiichuk.queue.doublyLinkedList;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTest {
    private DoublyLinkedList<String> stringList;
    private DoublyLinkedList<Integer> intList;

    final String oneString = "one";
    final Integer oneInt = 1;
    final String twoString = "two";
    final Integer twoInt = 2;
    final String threeString = "three";
    final Integer threeInt = 3;

    @Before
    public void setUp() throws Exception {
	stringList = new DoublyLinkedList<>();
	intList = new DoublyLinkedList<>();
    }

    @Test
    public void testAddAllCollectionOfQextendsE() {

	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(0, twoString);
	intList.add(0, twoInt);
	List<String> stringTestList = new ArrayList<>(1);
	stringTestList.add(oneString);
	List<Integer> intTestList = new ArrayList<>(1);
	intTestList.add(1);
	assertTrue(stringList.addAll(stringTestList));
	assertTrue(intList.addAll(intTestList));
	assertEquals(3, stringList.size());
	assertEquals(3, intList.size());
    }

    @Test
    public void testAddAllIntCollectionOfQextendsE() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(0, twoString);
	intList.add(0, twoInt);
	List<String> stringTestList = new ArrayList<>(1);
	stringTestList.add(oneString);
	List<Integer> intTestList = new ArrayList<>(1);
	intTestList.add(1);
	assertTrue(stringList.addAll(1, stringTestList));
	assertTrue(intList.addAll(1, intTestList));
	assertEquals(3, stringList.size());
	assertEquals(3, intList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllIntCollectionOfQextendsEIndexOutOfBoundsException() {
	List<String> stringTestList = new ArrayList<>(1);
	stringTestList.add(oneString);
	stringList.addAll(5, stringTestList);
    }

    @Test
    public void testAddE() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(twoString);
	intList.add(twoInt);
	assertEquals(2, stringList.size());
	assertEquals(2, intList.size());
	assertEquals(oneString, stringList.get(0));
	assertEquals(oneInt, intList.get(0));
	assertEquals(twoString, stringList.get(1));
	assertEquals(twoInt, intList.get(1));
    }

    @Test
    public void testAddIntE() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(0, twoString);
	intList.add(0, twoInt);
	stringList.add(2, threeString);
	intList.add(2, threeInt);
	assertEquals(3, stringList.size());
	assertEquals(3, intList.size());
	assertEquals(oneString, stringList.get(1));
	assertEquals(oneInt, intList.get(1));
	assertEquals(twoString, stringList.get(0));
	assertEquals(twoInt, intList.get(0));
    }

    @Test
    public void testClear() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(0, twoString);
	intList.add(0, twoInt);
	stringList.clear();
	intList.clear();
	assertEquals(0, stringList.size());
	assertEquals(0, intList.size());
    }

    @Test
    public void testContains() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(0, twoString);
	intList.add(0, twoInt);
	assertTrue(stringList.contains(twoString));
	assertTrue(intList.contains(twoInt));
    }

    @Test
    public void testContainsAllTrue() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(0, twoString);
	intList.add(0, twoInt);
	List<String> stringTestList = new ArrayList<>(1);
	stringTestList.add(oneString);
	stringTestList.add(twoString);
	List<Integer> intTestList = new ArrayList<>(1);
	intTestList.add(oneInt);
	intTestList.add(twoInt);
	assertTrue(stringList.containsAll(stringTestList));
	assertTrue(intList.containsAll(intTestList));
    }

    @Test
    public void testContainsAllFalse() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(0, twoString);
	intList.add(0, twoInt);
	List<String> stringTestList = new ArrayList<>(1);
	stringTestList.add(oneString);
	stringTestList.add(twoString + 1);
	List<Integer> intTestList = new ArrayList<>(1);
	intTestList.add(oneInt);
	intTestList.add(twoInt + 1);
	assertFalse(stringList.containsAll(stringTestList));
	assertFalse(intList.containsAll(intTestList));
    }

    @Test
    public void testGet() {
	stringList.add(oneString);
	intList.add(oneInt);
	assertEquals(oneString, stringList.get(0));
	assertEquals(oneInt, intList.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsException() {
	stringList.add(oneString);
	intList.add(oneInt);
	assertEquals(oneString, stringList.get(3));
	assertEquals(oneInt, intList.get(2));
    }

    @Test
    public void testIndexOf() {
	stringList.add(oneString);
	intList.add(oneInt);
	assertEquals(0, stringList.indexOf(oneString));
	assertEquals(0, intList.indexOf(oneInt));
	assertEquals(-1, stringList.indexOf(""));
	assertEquals(-1, intList.indexOf(3));
    }

    @Test
    public void testIsEmptyFalse() {
	stringList.add(oneString);
	assertFalse(stringList.isEmpty());
	intList.add(oneInt);
	assertFalse(intList.isEmpty());
    }

    @Test
    public void testIsEmptyTrue() {
	assertTrue(stringList.isEmpty());
	assertTrue(intList.isEmpty());
    }

    @Test
    public void testLastIndexOf() {
	stringList.add(oneString);
	intList.add(oneInt);
	assertEquals(0, stringList.indexOf(oneString));
	assertEquals(0, intList.indexOf(oneInt));
	assertEquals(-1, stringList.indexOf(""));
	assertEquals(-1, intList.indexOf(3));
    }

    @Test
    public void testRemoveAllTrue() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(0, twoString);
	intList.add(0, twoInt);
	List<String> stringTestList = new ArrayList<>(1);
	stringTestList.add(oneString);
	List<Integer> intTestList = new ArrayList<>(1);
	intTestList.add(1);
	assertTrue(stringList.removeAll(stringTestList));
	assertTrue(intList.removeAll(intTestList));
	assertEquals(1, stringList.size());
	assertEquals(1, intList.size());

    }

    @Test
    public void testRemoveAllFalse() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(0, twoString);
	intList.add(0, twoInt);
	List<String> stringTestList = new ArrayList<>(1);
	stringTestList.add("three");
	List<Integer> intTestList = new ArrayList<>(1);
	intTestList.add(3);
	assertFalse(stringList.removeAll(stringTestList));
	assertFalse(intList.removeAll(intTestList));
	assertEquals(2, stringList.size());
	assertEquals(2, intList.size());

    }

    @Test
    public void testRemoveIntTrue() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(null);
	intList.add(null);

	assertEquals(oneString, stringList.remove(0));
	assertEquals(oneInt, intList.remove(0));
	assertNull(stringList.remove(0));
	assertNull(intList.remove(0));
	assertEquals(0, stringList.size());
	assertEquals(0, intList.size());
    }

    @Test
    public void testRemoveTrue() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(null);
	intList.add(null);

	assertTrue(stringList.remove(oneString));
	assertTrue(intList.remove(oneInt));
	assertTrue(stringList.remove(null));
	assertTrue(intList.remove(null));
	assertEquals(0, stringList.size());
	assertEquals(0, intList.size());
    }

    @Test
    public void testRemoveFalse() {
	assertFalse(stringList.remove(""));
	assertFalse(intList.remove(new Integer(1)));
	stringList.add("1");
	intList.add(1);
	assertFalse(stringList.remove(""));
	assertFalse(intList.remove(new Integer(2)));
    }

    @Test
    public void testRetainAll() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(0, twoString);
	intList.add(0, twoInt);
	List<String> stringTestList = new ArrayList<>(1);
	stringTestList.add(oneString);
	List<Integer> intTestList = new ArrayList<>(1);
	intTestList.add(oneInt);
	assertTrue(stringList.retainAll(stringTestList));
	assertTrue(intList.retainAll(intTestList));
	assertEquals(1, stringList.size());
	assertEquals(1, intList.size());
	assertEquals(oneString, stringList.get(0));
	assertEquals(oneInt, intList.get(0));
    }

    @Test
    public void testSet() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(null);
	intList.add(null);
	final String actualString = stringList.set(1, oneString);
	final Integer actualInt = intList.set(1, oneInt);

	assertNull(actualString);
	assertNull(actualInt);
	assertEquals(oneString, stringList.get(1));
	assertEquals(oneInt, intList.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexOutOfBoundsException() {
	stringList.set(3, "");
    }

    @Test
    public void testSize() {
	assertEquals(0, stringList.size());
	stringList.add(oneString);
	assertEquals(1, stringList.size());
    }

    @Test
    public void testSubList() {
	stringList.add(oneString);
	intList.add(oneInt);
	stringList.add(twoString);
	intList.add(twoInt);
	stringList.add(threeString);
	intList.add(threeInt);
	List<String> stringTestList = new ArrayList<>(1);
	stringTestList.add(oneString);
	List<Integer> intTestList = new ArrayList<>(1);
	intTestList.add(oneInt);
	assertEquals(stringTestList, stringList.subList(0, 1));
	assertEquals(intTestList, intList.subList(0, 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListIndexOutOfBoundsException() {
	stringList.add("");
	stringList.subList(1, 0);
    }

    public void testSubListEmptyList() {
	assertEquals(Collections.emptyList(), stringList.subList(0, 0));
    }

    @Test
    public void testToArray() {
	final Object[] stringTestArray = { oneString };
	final Object[] intTestArray = { oneInt };
	stringList.add(oneString);
	intList.add(oneInt);
	assertArrayEquals(stringTestArray, stringList.toArray());
	assertArrayEquals(intTestArray, intList.toArray());
    }

    @Test
    public void testToArrayTArray() {
	final String[] stringTestArray = { oneString };
	final Integer[] intTestArray = { oneInt };
	stringList.add(oneString);
	intList.add(oneInt);
	assertArrayEquals(stringTestArray, stringList.toArray(stringTestArray));
	assertArrayEquals(intTestArray, intList.toArray(intTestArray));
    }

}
