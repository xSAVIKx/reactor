package ua.com.globallogic.basecamp.sergiichuk.anagram;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AnagramSet {
    /**
     * set of anagrams
     */
    private Set<String> anagrams;
    /**
     * Sorted char array of first(and actually every) anagram in current anagram
     * set.
     */
    private char[] firstAnagramChars;

    public AnagramSet(String word) {
	anagrams = new HashSet<String>();
	anagrams.add(word.toLowerCase());
	firstAnagramChars = word.toLowerCase().toCharArray();
	Arrays.sort(firstAnagramChars);
    }

    /**
     * Tries to add to anagram to current anagram set
     * 
     * @param word
     *            word that is tried to be added
     * @return true if word is an anagram and current anagram set doesn't
     *         contains such word.
     */
    public boolean addAnagram(String word) {
	if (word == null)
	    return false;
	String lowerCaseWord = word.toLowerCase();
	return !(lowerCaseWord.isEmpty() || lowerCaseWord.length() < 1 || anagrams
		.contains(lowerCaseWord))
		&& isAnagram(lowerCaseWord)
		&& anagrams.add(lowerCaseWord);
    }

    /**
     * Returns the number of elements in this anagram set
     * 
     * @return the number of elements in this anagram set
     */
    public int size() {
	return anagrams.size();
    }

    /**
     * Returns array of strings with anagrams
     * 
     * @return array of strings with anagrams
     */
    public String[] getAnagrams() {
	return anagrams.toArray(new String[anagrams.size()]);
    }

    /**
     * Check if word is an anagram. Actually, if word length and any anagram in
     * this anagram set are not equals, 'word' is not an anagram. If sorted
     * chars of 'word' are not equals to sorted chars of first(actually every)
     * anagram in current anagram set, word is not an anagram.
     * 
     * @param word
     *            to be checked
     * @return true if word is anagram to current anagram set
     */
    private boolean isAnagram(String word) {
	if (word.length() != firstAnagramChars.length)
	    return false;
	char[] wordChars = word.toCharArray();
	Arrays.sort(wordChars);
	return Arrays.equals(wordChars, firstAnagramChars);
    }

    /**
     * Check if word is present in current anagram set
     * 
     * @param word
     *            word to checked
     * @return true if current anagram set already have such word
     */
    public boolean contains(String word) {
	return anagrams.contains(word.toLowerCase());
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	Iterator<String> anagramElementIterator = anagrams.iterator();
	do {
	    sb.append(anagramElementIterator.next());
	    if (anagramElementIterator.hasNext())
		sb.append(", ");
	} while (anagramElementIterator.hasNext());
	return sb.toString();
    }

}
