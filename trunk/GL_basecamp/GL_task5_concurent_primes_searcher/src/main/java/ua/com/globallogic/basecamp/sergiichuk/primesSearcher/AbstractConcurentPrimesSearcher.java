package ua.com.globallogic.basecamp.sergiichuk.primesSearcher;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public abstract class AbstractConcurentPrimesSearcher implements
	ConcurentPrimesSearcher {
    protected volatile List<Long> primesList;
    protected int threadsAmount;
    protected long fromRange;
    protected long toRange;
    protected long[][] boundsArray;

    public AbstractConcurentPrimesSearcher(int threadsAmount, long toRange) {
	this.threadsAmount = threadsAmount;
	setRange(0, toRange);
    }

    public AbstractConcurentPrimesSearcher(int threadsAmount, long fromRange,
	    long toRange) {
	this.threadsAmount = threadsAmount;
	setRange(fromRange, toRange);
    }

    public abstract List<Long> searchPrimes();

    /**
     * This method divides the range into subbands for each thread
     */
    protected void setBoundsArray() {
	final long numbersToCheckAmount = toRange - fromRange;
	long averageAmountPerThread = numbersToCheckAmount / threadsAmount;
	if (numbersToCheckAmount < threadsAmount) {
	    boundsArray = new long[(int) numbersToCheckAmount][2];
	    for (int i = 0; i < boundsArray.length; i++) {
		boundsArray[i][0] = i;
		boundsArray[i][1] = i;
	    }
	} else {
	    boundsArray = new long[threadsAmount][2];
	    for (int i = 0; i < boundsArray.length; i++) {
		boundsArray[i][0] = averageAmountPerThread * i;
		boundsArray[i][1] = averageAmountPerThread * i
			+ averageAmountPerThread - 1;
	    }
	    boundsArray[0][0] = fromRange;
	    boundsArray[boundsArray.length - 1][1] = toRange;
	}
    }

    /**
     * Check if threads amount is equal or bigger than 1
     * 
     * @param threadsAmount
     *            amount to check
     * @return true, if threads amount is equal or bigger than 1
     */
    private boolean checkThreadsAmount(int threadsAmount) {
	return threadsAmount > 0;
    }

    protected void checkThreadsAmountBounds(int threadsAmount) {
	if (!checkThreadsAmount(threadsAmount))
	    throw new IllegalArgumentException(String.format(
		    "threadsAmount=%d", threadsAmount));
    }

    /**
     * Check if range to search primes is normal
     * 
     * @param fromRange
     *            from range
     * @param toRange
     *            to range
     * @return true, if range to search primes is normal
     */
    private boolean checkRange(long fromRange, long toRange) {
	if (fromRange < 0 || fromRange >= toRange)
	    return false;
	return true;
    }

    protected void checkRangeBounds(long fromRange, long toRange) {
	if (!checkRange(fromRange, toRange))
	    throw new IllegalArgumentException(String.format(
		    "fromRange=%d, toRange=%d", fromRange, toRange));

    }

    /**
     * Adds found by thread primes to list global primes list
     * 
     * @param primesList
     *            primes list of one thread to be added to global primes list
     * @return true, if thread primes were added to global primes list
     */
    private synchronized boolean addPrimesToList(List<Long> primesList) {
	return this.primesList.addAll(primesList);
    }

    protected class SearchThread extends Thread {
	/**
	 * Logger for this class
	 */
	private final Logger logger = Logger.getLogger(SearchThread.class);

	private long fromRange;
	private long toRange;

	private List<Long> primesList;

	public SearchThread(long fromRange, long toRange) {
	    if (logger.isDebugEnabled()) {
		logger.debug("SearchThread(int, int) - start. ThreadName=" + this.getName()); //$NON-NLS-1$
	    }

	    logger.debug("fromRange=" + fromRange);

	    this.fromRange = fromRange;

	    logger.debug("toRange=" + toRange);
	    this.toRange = toRange;

	    primesList = new ArrayList<Long>();

	    if (logger.isDebugEnabled()) {
		logger.debug("SearchThread(int, int) - end. ThreadName=" + this.getName()); //$NON-NLS-1$
	    }
	}

	@Override
	public void run() {
	    if (logger.isDebugEnabled()) {
		logger.debug("run() - start. ThreadName=" + this.getName()); //$NON-NLS-1$
	    }

	    this.searchPrimes();

	    AbstractConcurentPrimesSearcher.this.addPrimesToList(primesList);

	    if (logger.isDebugEnabled()) {
		logger.debug("run() - end. ThreadName=" + this.getName()); //$NON-NLS-1$
	    }
	}

	private void searchPrimes() {
	    if (logger.isDebugEnabled()) {
		logger.debug("searchPrimes() - start. ThreadName=" + this.getName()); //$NON-NLS-1$
	    }

	    for (long i = fromRange; i < toRange; i++) {
		if (isPrime(i))
		    primesList.add(i);
	    }

	    if (logger.isDebugEnabled()) {
		logger.debug("searchPrimes() - end. ThreadName=" + this.getName()); //$NON-NLS-1$
	    }
	}

	private boolean isPrime(final long number) {
	    if (number == 0 || number == 1)
		return false;
	    if (number == 2 || number == 3 || number == 5)
		return true;
	    if (number % 2 == 0 || number % 3 == 0 || number % 5 == 0)
		return false;
	    long bound = (long) Math.sqrt(number);
	    long i = 7;
	    long j = 11;
	    while ((j <= bound) && (number % i) > 0 && (number % j) > 0) {
		i += 6;
		j += 6;
	    }
	    if (j <= bound || i <= bound && number % i == 0)
		return false;
	    return true;
	}
    }

    public void setRange(long fromRange, long toRange) {
	checkRangeBounds(fromRange, toRange);
	this.fromRange = fromRange;
	this.toRange = toRange;
    }

    public int getThreadsAmount() {
	return threadsAmount;
    }

    public void setThreadsAmount(int threadsAmount) {
	checkThreadsAmountBounds(threadsAmount);
	this.threadsAmount = threadsAmount;
    }

    public long getFromRange() {
	return fromRange;
    }

    public long getToRange() {
	return toRange;
    }
}
