package ua.com.globallogic.basecamp.sergiichuk.primesSearcher.searchers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ua.com.globallogic.basecamp.sergiichuk.primesSearcher.ConcurentPrimesSearcher;

public class MultithreadPrimesSearcherTest {
    private ConcurentPrimesSearcher multiThreadSearcher;
    private List<Long> primes;

    @Before
    public void setUp() throws Exception {
	multiThreadSearcher = new MultithreadPrimesSearcher(2, 10);
	primes = new ArrayList<>();
	primes.add(2L);
	primes.add(3L);
	primes.add(5L);
	primes.add(7L);
    }

    @Test
    public void testSearchPrimes() {
	assertEquals(primes, multiThreadSearcher.searchPrimes());
	assertArrayEquals(primes.toArray(), multiThreadSearcher.searchPrimes()
		.toArray());
    }
}
