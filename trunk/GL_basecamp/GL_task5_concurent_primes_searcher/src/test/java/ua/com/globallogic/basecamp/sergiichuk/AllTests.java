package ua.com.globallogic.basecamp.sergiichuk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.com.globallogic.basecamp.sergiichuk.primesSearcher.AbstractConcurentPrimesSearcherTest;
import ua.com.globallogic.basecamp.sergiichuk.primesSearcher.searchers.ExecutorPrimesSearcherTest;
import ua.com.globallogic.basecamp.sergiichuk.primesSearcher.searchers.MultithreadPrimesSearcherTest;

@RunWith(Suite.class)
@SuiteClasses({ AbstractConcurentPrimesSearcherTest.class,
	ExecutorPrimesSearcherTest.class, MultithreadPrimesSearcherTest.class })
public class AllTests {

}
