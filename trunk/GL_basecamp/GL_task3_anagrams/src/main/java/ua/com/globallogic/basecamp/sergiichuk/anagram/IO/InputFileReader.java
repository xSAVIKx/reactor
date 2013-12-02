package ua.com.globallogic.basecamp.sergiichuk.anagram.IO;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class InputFileReader implements Closeable {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
	    .getLogger(InputFileReader.class);

    private final int linesInOneBlock;
    private final static String CHARSET = "UTF-8";
    private Scanner scanner;

    /**
     * Constructs InputFileReader object
     * 
     * @param fileToRead
     *            file to read info from
     * @param linesInOneBlock
     *            lines amount to read in one action
     * @throws IllegalArgumentException
     *             if parameters are null or 0, or file doens't exist, or file
     *             is a directory
     */
    public InputFileReader(File fileToRead, int linesInOneBlock) {
	if (logger.isDebugEnabled()) {
	    logger.debug("InputFileReader(File fileToRead=" + fileToRead
		    + ", int linesInOneBlock=" + linesInOneBlock + ") - start");
	}
	if (fileToRead == null || linesInOneBlock < 1 || !fileToRead.exists()
		|| fileToRead.isDirectory())
	    throw new IllegalArgumentException(
		    String.format(
			    "Arguments should not be null(0), file should exists and be not directory. FileToRead="
				    + fileToRead + ", lineInOneBlock=%d",
			    linesInOneBlock));

	this.linesInOneBlock = linesInOneBlock;

	try {
	    scanner = new Scanner(fileToRead, CHARSET);
	} catch (FileNotFoundException ignore) {
	    logger.warn("InputFileReader(File, int) - exception ignored",
		    ignore);
	}

	if (logger.isDebugEnabled()) {
	    logger.debug("InputFileReader(File fileToRead=" + fileToRead
		    + ", int linesInOneBlock=" + linesInOneBlock + ") - end");
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
	if (logger.isDebugEnabled()) {
	    logger.debug("readLines() - start");
	}

	StringBuilder sb = new StringBuilder();
	int readedLinesAmount = 0;
	while (scanner.hasNextLine() && readedLinesAmount < linesInOneBlock) {
	    String readedLine = scanner.nextLine();
	    readedLinesAmount++;
	    sb.append(readedLine).append(' ');
	}
	if (logger.isTraceEnabled()) {
	    logger.trace("readLines() - int readedLinesAmount="
		    + readedLinesAmount);
	}
	return sb.toString();
    }

    public void close() throws IOException {
	if (scanner != null)
	    scanner.close();
    }
}
