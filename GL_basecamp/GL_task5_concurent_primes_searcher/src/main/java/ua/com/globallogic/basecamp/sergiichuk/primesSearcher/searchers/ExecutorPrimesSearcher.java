package ua.com.globallogic.basecamp.sergiichuk.primesSearcher.searchers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ua.com.globallogic.basecamp.sergiichuk.primesSearcher.AbstractConcurentPrimesSearcher;

public class ExecutorPrimesSearcher extends AbstractConcurentPrimesSearcher {

    public ExecutorPrimesSearcher(int threadsAmount, long fromRange,
	    long toRange) {
	super(threadsAmount, fromRange, toRange);
    }

    public ExecutorPrimesSearcher(int threadsAmount, long toRange) {
	super(threadsAmount, toRange);
    }

    @Override
    public List<Long> searchPrimes() {
	setBoundsArray();
	primesList = new ArrayList<>();
	ExecutorService executor = Executors
		.newFixedThreadPool(boundsArray.length);
	for (int i = 0; i < boundsArray.length; i++) {
	    executor.execute(new SearchThread(boundsArray[i][0],
		    boundsArray[i][1]));
	}
	executor.shutdown();
	while (!executor.isTerminated())
	    ;
	return primesList;
    }
}
