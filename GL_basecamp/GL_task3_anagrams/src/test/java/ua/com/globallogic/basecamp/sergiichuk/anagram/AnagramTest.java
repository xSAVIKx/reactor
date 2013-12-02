package ua.com.globallogic.basecamp.sergiichuk.anagram;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AnagramTest {
    private final String POOL = "pool";
    private final String LOOP = "loOp";
    private final String POLO = "polo";
    private final String LETO = "лето";
    private final String TELO = "тело";
    private final String ONE_TWO_THREE = "123";
    private final String THREE_TWO_ONE = "321";
    private final String ONE_POOL = "1pool";
    private final String ONE_POLO = "polo1";
    private final String[] POOL_POLO_ARRAY = { POOL, POLO };
    private int anagramSize;
    private AnagramSet anagramSet;

    @Before
    public void setUp() throws Exception {
	anagramSet = new AnagramSet(POOL);
	anagramSize = 1;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgumentException() {
	anagramSet = new AnagramSet(null);
    }

    @Test
    public void testAddAnagramWordPlusNumber() {
	anagramSet = new AnagramSet(ONE_POOL);
	anagramSet.addAnagram(ONE_POLO);
	assertEquals(2, anagramSet.size());
	assertTrue(anagramSet.contains(ONE_POLO));
	assertTrue(anagramSet.contains(ONE_POOL));
    }

    @Test
    public void testAddAnagramNumbers() {
	anagramSet = new AnagramSet(ONE_TWO_THREE);
	anagramSet.addAnagram(THREE_TWO_ONE);
	assertEquals(2, anagramSet.size());
	assertTrue(anagramSet.contains(ONE_TWO_THREE));
	assertTrue(anagramSet.contains(THREE_TWO_ONE));
    }

    @Test
    public void testAddAnagramRussian() {
	anagramSet = new AnagramSet(LETO);
	anagramSet.addAnagram(TELO);
	assertEquals(2, anagramSet.size());
	assertTrue(anagramSet.contains(LETO));
	assertTrue(anagramSet.contains(TELO));
    }

    @Test
    public void testAddAnagramTrue() {
	assertTrue(anagramSet.addAnagram(LOOP));
	anagramSize++;
	assertEquals(anagramSize, anagramSet.size());
	assertTrue(anagramSet.contains(LOOP));
	assertTrue(anagramSet.addAnagram(POLO));
	anagramSize++;
	assertEquals(anagramSize, anagramSet.size());
	assertTrue(anagramSet.contains(POLO));
    }

    @Test
    public void testAddAnagramEmpty() {
	assertFalse(anagramSet.addAnagram(""));
    }

    @Test
    public void testAddAnagramNull() {
	assertFalse(anagramSet.addAnagram(null));

    }

    @Test
    public void testAddAnagramOneSymbol() {
	assertFalse(anagramSet.addAnagram("1"));
    }

    public void testAddAnagramNotAnagram() {
	assertFalse(anagramSet.addAnagram("word"));
    }

    @Test
    public void testAddAnagramAlreadyContain() {
	assertFalse(anagramSet.addAnagram(POOL));
    }

    @Test
    public void testGetAnagrams() {
	anagramSet.addAnagram(POLO);
	for (String arrayElement : POOL_POLO_ARRAY) {
	    boolean hasElement = false;
	    for (String anagramSetElement : anagramSet.getAnagrams()) {
		if (arrayElement.equals(anagramSetElement))
		    hasElement = true;
	    }
	    assertTrue(hasElement);
	}
    }
}
