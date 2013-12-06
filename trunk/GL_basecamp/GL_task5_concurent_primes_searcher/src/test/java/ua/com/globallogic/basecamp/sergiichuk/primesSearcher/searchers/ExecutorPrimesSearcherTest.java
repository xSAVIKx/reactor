package ua.com.globallogic.basecamp.sergiichuk.primesSearcher.searchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ua.com.globallogic.basecamp.sergiichuk.primesSearcher.ConcurentPrimesSearcher;

public class ExecutorPrimesSearcherTest {
    private ConcurentPrimesSearcher executorSearcher;
    private List<Long> primes;

    @Before
    public void setUp() throws Exception {
	executorSearcher = new ExecutorPrimesSearcher(2, 20);
	primes = new ArrayList<>();
	primes.add(2L);
	primes.add(3L);
	primes.add(5L);
	primes.add(7L);
	primes.add(11L);
	primes.add(13L);
	primes.add(17L);
	primes.add(19L);
    }

    @Test
    public void testSearchPrimes() {
	assertEquals(primes, executorSearcher.searchPrimes());
	assertArrayEquals(primes.toArray(), executorSearcher.searchPrimes()
		.toArray());
    }

}
