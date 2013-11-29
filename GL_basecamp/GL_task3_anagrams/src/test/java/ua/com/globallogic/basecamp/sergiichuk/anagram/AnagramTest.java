package ua.com.globallogic.basecamp.sergiichuk.anagram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AnagramTest {
    private final String POOL = "pool";
    private final String LOOP = "loOp";
    private final String POLO = "polo";
    private int anagramSize;
    private AnagramSet anagramSet;

    @Before
    public void setUp() throws Exception {
	anagramSet = new AnagramSet(POOL);
	anagramSize = 1;
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
    public void testAddAnagramFalse() {
	assertFalse(anagramSet.addAnagram(POOL));
    }

}
