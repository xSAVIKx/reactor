package ua.com.globallogic.basecamp.sergiichuk.primesSearcher;

import java.util.List;

public interface ConcurentPrimesSearcher {
    /**
     * This method search primes and return List<Long>, containing that primes
     * 
     * @return primes List
     */
    public List<Long> searchPrimes();

    /**
     * Set range to find primes
     * 
     * @param fromRange
     *            number to start finding from
     * @param toRange
     *            last number to check
     * @throws IllegalArgumentException
     *             if fromRange < 0 or fromRange>=toRange
     */
    public void setRange(long fromRange, long toRange);

    /**
     * Return threads amount
     * 
     * @return threads amount
     */
    public int getThreadsAmount();

    /**
     * Set threads amount
     * 
     * @param threadsAmount
     *            number of threads
     * @throws IllegalArgumentException
     *             if threadsAmount < 0
     */
    public void setThreadsAmount(int threadsAmount);

    /**
     * Return from-range
     * 
     * @return from-range
     */
    public long getFromRange();

    /**
     * Return to-range
     * 
     * @return to-range
     */
    public long getToRange();
}
