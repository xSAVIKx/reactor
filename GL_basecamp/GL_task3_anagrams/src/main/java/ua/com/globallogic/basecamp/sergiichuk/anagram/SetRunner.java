package ua.com.globallogic.basecamp.sergiichuk.anagram;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
//	long timeBefore = System.currentTimeMillis();
//	System.out.println("SetRunner run. time="+timeBefore);
	List<AnagramSet> anagramsList = new ArrayList<>();
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
	    // delete me
//	    System.out.println("Set runner readed all. Time="+(System.currentTimeMillis()-timeBefore));
	    for (String element : wordsOfInputFileSet) {
		if (element == null || element.isEmpty())
		    continue;
		boolean elementAdded = false;
		for (AnagramSet anagrams : anagramsList) {
		    if (!elementAdded & anagrams.addAnagram(element)) {
			elementAdded = true;
			break;
		    }
		}
		if (!elementAdded) {
		    anagramsList.add(new AnagramSet(element));
		}
	    }
//	    System.out.println("Set runner get all anagrams. Time="+(System.currentTimeMillis()-timeBefore));
	    for (int i = 0; i < anagramsList.size(); i++) {
		if (anagramsList.get(i).size() == 1) {
		    anagramsList.remove(i);
		    i--;
		}
	    }
	    outputFileWriter.write(anagramsList);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
