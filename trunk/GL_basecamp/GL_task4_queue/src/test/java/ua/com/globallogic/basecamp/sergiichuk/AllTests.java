package ua.com.globallogic.basecamp.sergiichuk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.com.globallogic.basecamp.sergiichuk.queue.ArrayBasedQueueTest;
import ua.com.globallogic.basecamp.sergiichuk.queue.ListBasedQueueTest;
import ua.com.globallogic.basecamp.sergiichuk.queue.ArrayBasedList.ArrayBasedListTest;
import ua.com.globallogic.basecamp.sergiichuk.queue.doublyLinkedList.DoublyLinkedListTest;

@RunWith(Suite.class)
@SuiteClasses({ ArrayBasedQueueTest.class, ListBasedQueueTest.class,
	ArrayBasedListTest.class, DoublyLinkedListTest.class })
public class AllTests {

}
