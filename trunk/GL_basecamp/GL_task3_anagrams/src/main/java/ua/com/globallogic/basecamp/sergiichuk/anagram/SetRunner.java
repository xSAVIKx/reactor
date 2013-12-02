package ua.com.globallogic.basecamp.sergiichuk.anagram;

import org.apache.log4j.Logger;

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
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(SetRunner.class);

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
	if (logger.isDebugEnabled()) {
	    logger.debug("run(File input=" + input + ", File output=" + output
		    + ") - start");
	}

	List<AnagramSet> anagramsList = new LinkedList<>();
	Set<String> wordsOfInputFileSet = new HashSet<>();
	try (InputFileReader inputFileReader = new InputFileReader(input,
		linesInOneBlock);
		OutputFileWriter outputFileWriter = new OutputFileWriter(output);) {

	    while (inputFileReader.hasNextLines()) {
		String textBlock = inputFileReader.readLines();
		String[] textBlockWords = textBlock.split(REGEXP);
		for (String element : textBlockWords) {
		    if (element.length() > 1)
			wordsOfInputFileSet.add(element);
		}
	    }
	    if (logger.isTraceEnabled()) {
		logger.trace("run - Set<String> wordsOfInputFileSet->size()="
			+ wordsOfInputFileSet.size());
	    }
	    OUT: for (String element : wordsOfInputFileSet) {
		for (AnagramSet anagrams : anagramsList) {
		    if (anagrams.addAnagram(element)) {
			continue OUT;
		    }
		}
		anagramsList.add(new AnagramSet(element));
	    }
	    if (logger.isTraceEnabled()) {
		logger.trace("run() - List<AnagramSet> anagramsList.size()="
			+ anagramsList.size());
	    }
	    List<AnagramSet> correctAnagramSetList = new LinkedList<>();
	    for (AnagramSet element : anagramsList) {
		if (element.size() > 1) {
		    correctAnagramSetList.add(element);
		}
	    }
	    if (logger.isTraceEnabled()) {
		logger.trace("run - List<AnagramSet> correctAnagramSetList.size()="
			+ correctAnagramSetList.size());
	    }
	    outputFileWriter.write(correctAnagramSetList);

	} catch (IOException e) {
	    logger.error("run(File, File)", e);
	}

	if (logger.isDebugEnabled()) {
	    logger.debug("run(File input=" + input + ", File output=" + output
		    + ") - end");
	}
    }
}
