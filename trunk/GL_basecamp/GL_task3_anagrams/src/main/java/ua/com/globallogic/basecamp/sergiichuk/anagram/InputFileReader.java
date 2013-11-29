package ua.com.globallogic.basecamp.sergiichuk.anagram;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class InputFileReader implements Closeable {

    private final int linesInOneBlock;
    private Scanner scanner;

    public InputFileReader(File fileToRead, int linesInOneBlock) {
	this.linesInOneBlock = linesInOneBlock;
	try {
	    scanner = new Scanner(fileToRead);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public boolean hasNextLines() {
	return scanner.hasNextLine();
    }

    /**
     * Returns one String object with at most linesInOneBlock read lines
     * 
     * @return Returns one String object with at most linesInOneBlock read lines
     */
    public String readLines() {
	StringBuilder sb = new StringBuilder();
	int readedLinesAmount = 0;
	while (scanner.hasNextLine() && readedLinesAmount < linesInOneBlock) {
	    String readedLine = scanner.nextLine();
	    readedLinesAmount++;
	    sb.append(readedLine).append(' ');
	}
	return sb.toString();
    }

    public void close() throws IOException {
	if (scanner != null)
	    scanner.close();
    }
}
