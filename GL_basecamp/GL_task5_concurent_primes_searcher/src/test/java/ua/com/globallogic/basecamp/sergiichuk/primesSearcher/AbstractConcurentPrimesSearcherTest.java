package ua.com.globallogic.basecamp.sergiichuk.primesSearcher;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AbstractConcurentPrimesSearcherTest {
    private AbstractConcurentPrimesSearcher abstractSearcher;

    @Before
    public void setUp() throws Exception {
	abstractSearcher = new AbstractConcurentPrimesSearcher(2, 0, 10) {
	    @Override
	    public List<Long> searchPrimes() {
		// TODO Auto-generated method stub
		return null;
	    }
	};
    }

    @Test
    public void testCheckThreadsAmountBoundsTrue() {
	abstractSearcher.checkThreadsAmountBounds(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckThreadsAmountBoundsIllegalArgumentException() {
	abstractSearcher.checkThreadsAmountBounds(0);
    }

    @Test
    public void testCheckRangeBoundsTrue() {
	abstractSearcher.checkRangeBounds(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckRangeBoundsIllegalArgumentExceptionFromRangeUnderZero() {
	abstractSearcher.checkRangeBounds(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckRangeBoundsIllegalArgumentExceptionFromRangeLargerThanToRange() {
	abstractSearcher.checkRangeBounds(2, 1);
    }

}
