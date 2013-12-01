package ua.com.globallogic.basecamp.sergiichuk.binaryTree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BinaryTreeTest {
    private Tree<Integer> testTree;
    private final Integer oneInteger = 1;

    @Test
    public void testAdd() {
	testTree = new BinaryTree<Integer>();
	testTree.add(oneInteger);
	assertEquals(1, testTree.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddIllegalArgumentException() {
	testTree = new BinaryTree<Integer>();
	testTree.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveIllegalArgumentException() {
	testTree = new BinaryTree<Integer>(oneInteger);
	testTree.remove(null);
    }

    @Test
    public void testRemoveOnlyRootElementIsPresent() {
	testTree = new BinaryTree<Integer>(oneInteger);
	assertEquals(oneInteger, testTree.remove(oneInteger));
    }

    @Test
    public void testRemoveElementWithOneLeftLeafIsPresent() {
	testTree = new BinaryTree<Integer>(2);
	testTree.add(oneInteger);
	assertEquals(oneInteger, testTree.remove(oneInteger));
    }

    @Test
    public void testRemoveElementWithOneLeftLeafWithChild() {
	testTree = new BinaryTree<Integer>(2);
	testTree.add(oneInteger);
	testTree.add(0);
	assertEquals(oneInteger, testTree.remove(oneInteger));
	assertEquals(2, testTree.size());
    }

    @Test
    public void testRemoveElementWithOneLeftLeafWithTwoChilds() {
	testTree = new BinaryTree<Integer>(3);
	testTree.add(oneInteger);
	testTree.add(0);
	testTree.add(2);
	assertEquals(oneInteger, testTree.remove(oneInteger));
	assertEquals(3, testTree.size());
    }

    @Test
    public void testRemoveNoElementToRemove_getNull() {
	testTree = new BinaryTree<Integer>(3);
	assertNull(testTree.remove(oneInteger));
	assertEquals(1, testTree.size());
    }

    @Test
    public void testRemoveElementWithTwoLeafWhichHaveTwoChildsWithChildren() {
	final Integer FOUR = 4;
	testTree = new BinaryTree<Integer>(FOUR);
	testTree.add(3);
	testTree.add(8);
	testTree.add(6);
	testTree.add(5);
	testTree.add(7);
	testTree.add(10);
	testTree.add(9);
	testTree.add(11);
	testTree.add(6);
	testTree.add(8);
	testTree.add(2);
	testTree.add(3);
	int size = testTree.size();
	assertEquals(FOUR, testTree.remove(FOUR));
	assertEquals(size - 1, testTree.size());
    }

    @Test
    public void testClear() {
	testTree = new BinaryTree<Integer>();
	testTree.add(3);
	testTree.add(8);
	testTree.add(6);
	testTree.clear();
	assertEquals(0, testTree.size());
	assertNull(testTree.getRoot());
    }

    @Test
    public void testFind() {
	List<Integer> parentList = new ArrayList<Integer>();
	final Integer SIX = 6;
	final Integer THREE = 3;
	final Integer TWO = 2;
	parentList.add(THREE);
	parentList.add(SIX);
	testTree = new BinaryTree<Integer>();
	testTree.add(SIX);
	testTree.add(THREE);
	testTree.add(8);
	testTree.add(TWO);
	testTree.add(THREE);

	assertArrayEquals(parentList.toArray(), testTree.find(TWO).toArray());
    }

    @Test
    public void testContainsTrue() {
	final Integer ONE = 1;
	testTree = new BinaryTree<Integer>();
	testTree.add(8);
	testTree.add(8);
	testTree.add(ONE);
	assertTrue(testTree.contains(ONE));
    }

    @Test
    public void testContainsFalse() {
	final Integer ONE = 1;
	testTree = new BinaryTree<Integer>();
	testTree.add(8);
	testTree.add(8);
	assertFalse(testTree.contains(ONE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsIllegalArgumentException() {
	testTree = new BinaryTree<Integer>(oneInteger);
	testTree.contains(null);
    }

}
