package ua.com.globallogic.basecamp.sergiichuk.anagram;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ua.com.globallogic.basecamp.sergiichuk.anagram.IO.InputFileReader;
import ua.com.globallogic.basecamp.sergiichuk.anagram.IO.OutputFileWriter;

/**
 * Parse text from input file and put ready anagrams into output file
 * 
 * @author SAVIK
 * 
 */
public class SetRunner implements Runner {
    private int linesInOneBlock;
    private final String REGEXP = "[\\d\\s\\p{Punct}]";

    public SetRunner() {
	linesInOneBlock = 20;
    }

    public SetRunner(int linesInOneBlockToRead) {
	linesInOneBlock = linesInOneBlockToRead;
    }

    @Override
    public void run(File input, File output) {
	List<AnagramSet> anagramsList = new LinkedList<>();
	Set<String> wordsOfInputFileSet = new HashSet<>();
	try (InputFileReader inputFileReader = new InputFileReader(input,
		linesInOneBlock);
		OutputFileWriter outputFileWriter = new OutputFileWriter(output)) {
	    while (inputFileReader.hasNextLines()) {
		String textBlock = inputFileReader.readLines();
		String[] textBlockWords = textBlock.split(REGEXP);
		for (String element : textBlockWords) {
		    wordsOfInputFileSet.add(element);
		}
	    }
	    OUT: for (String element : wordsOfInputFileSet) {
		for (AnagramSet anagrams : anagramsList) {
		    if (anagrams.addAnagram(element)) {
			continue OUT;
		    }
		}
		anagramsList.add(new AnagramSet(element));
	    }
	    List<AnagramSet> correctAnagramSetList = new LinkedList<>();
	    for (AnagramSet element : anagramsList) {
		if (element.size() > 1) {
		    correctAnagramSetList.add(element);
		}
	    }
	    outputFileWriter.write(correctAnagramSetList);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
