package ua.com.globallogic.basecamp.sergiichuk;

import java.io.IOException;
import java.util.List;

import ua.com.globallogic.basecamp.sergiichuk.primesSearcher.ConcurentPrimesSearcher;
import ua.com.globallogic.basecamp.sergiichuk.primesSearcher.searchers.ExecutorPrimesSearcher;
import ua.com.globallogic.basecamp.sergiichuk.primesSearcher.searchers.MultithreadPrimesSearcher;

public class Demo {
    private final static int threadsAmount = 4;
    private final static long fromRange = 0;
    private final static long toRange = 1_000_000;
    private static ConcurentPrimesSearcher executorSearcher = new ExecutorPrimesSearcher(
	    threadsAmount, fromRange, toRange);
    private static ConcurentPrimesSearcher multithreadSearcher = new MultithreadPrimesSearcher(
	    threadsAmount, fromRange, toRange);

    public static void main(String[] args) throws IOException {
	long timeBefore = System.currentTimeMillis();
	List<Long> executorPrimes = executorSearcher.searchPrimes();
	long timeExecutor = System.currentTimeMillis() - timeBefore;
	timeBefore = System.currentTimeMillis();
	System.out.println("executor done, primes amount="
		+ executorPrimes.size());
	List<Long> multithreadPrimes = multithreadSearcher.searchPrimes();
	long timeMultithread = System.currentTimeMillis() - timeBefore;
	System.out.println("multithread done, primes amount="
		+ multithreadPrimes.size());
	System.out
		.println(String
			.format("Executor working time=\t\t%d ms\nMultithread working time=\t%d ms",
				timeExecutor, timeMultithread));
    }
}
