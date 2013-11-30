package ua.com.globallogic.basecamp.sergiichuk.anagram;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleRunner  implements Runner{
    private int linesInOneBlock;
    private final String REGEXP = "[\\d\\s\\p{Punct}]";

    public SimpleRunner() {
	linesInOneBlock = 20;
    }

    public SimpleRunner(int linesInOneBlockToRead) {
	linesInOneBlock = linesInOneBlockToRead;
    }

    public void run(File input, File output) {
//	long timeBefore = System.currentTimeMillis();
	List<AnagramSet> anagramsList = new ArrayList<>();
	try (InputFileReader inputFileReader = new InputFileReader(input,
		linesInOneBlock);
		OutputFileWriter outputFileWriter = new OutputFileWriter(output)) {
	    while (inputFileReader.hasNextLines()) {
		String textBlock = inputFileReader.readLines();
		String[] textBlockWords = textBlock.split(REGEXP);
		
		for (String element : textBlockWords) {
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
	    }
//	    System.out.println("Simple runner readed all. Time="+(System.currentTimeMillis() - timeBefore));
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