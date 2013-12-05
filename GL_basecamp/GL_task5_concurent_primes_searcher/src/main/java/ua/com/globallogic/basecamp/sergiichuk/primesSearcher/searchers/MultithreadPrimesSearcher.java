package ua.com.globallogic.basecamp.sergiichuk.primesSearcher.searchers;

import java.util.ArrayList;
import java.util.List;

import ua.com.globallogic.basecamp.sergiichuk.primesSearcher.AbstractConcurentPrimesSearcher;

public class MultithreadPrimesSearcher extends AbstractConcurentPrimesSearcher {
    private Thread[] threads;

    public MultithreadPrimesSearcher(int threadsAmount, long fromRange,
	    long toRange) {
	super(threadsAmount, fromRange, toRange);
	threads = new Thread[threadsAmount];
    }

    public MultithreadPrimesSearcher(int threadsAmount, long toRange) {
	super(threadsAmount, toRange);
	threads = new Thread[threadsAmount];
    }

    @Override
    public List<Long> searchPrimes() {
	setBoundsArray();
	primesList = new ArrayList<>();
	for (int i = 0; i < boundsArray.length; i++) {
	    threads[i] = new SearchThread(boundsArray[i][0], boundsArray[i][1]);
	    threads[i].start();
	}
	for (int i = 0; i < boundsArray.length; i++)
	    try {
		threads[i].join();
	    } catch (InterruptedException ignored) {
	    }
	return primesList;
    }

}
