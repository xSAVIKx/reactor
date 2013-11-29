package ua.com.globallogic.basecamp.sergiichuk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.com.globallogic.basecamp.sergiichuk.binaryTree.BinaryTreeTest;
import ua.com.globallogic.basecamp.sergiichuk.singleLinkedList.SingleLinkedListIteratorTest;
import ua.com.globallogic.basecamp.sergiichuk.singleLinkedList.SingleLinkedListTest;

@RunWith(Suite.class)
@SuiteClasses({ BinaryTreeTest.class , SingleLinkedListTest.class, SingleLinkedListIteratorTest.class})
public class AllTests {
    
}
