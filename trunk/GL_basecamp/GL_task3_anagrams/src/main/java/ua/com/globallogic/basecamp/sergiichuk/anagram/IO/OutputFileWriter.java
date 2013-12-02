package ua.com.globallogic.basecamp.sergiichuk.anagram.IO;

import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import ua.com.globallogic.basecamp.sergiichuk.anagram.AnagramSet;

public class OutputFileWriter implements Closeable {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
	    .getLogger(OutputFileWriter.class);

    private final static String CHARSET = "UTF-8";
    private PrintWriter outputWriter;

    /**
     * Construct OutputFileWriter object
     * 
     * @param outputFile
     *            file to write info
     * @throws IllegalArgumentException
     *             if outputFile is null
     */
    public OutputFileWriter(File outputFile) {
	if (logger.isDebugEnabled()) {
	    logger.debug("OutputFileWriter(File outputFile=" + outputFile
		    + ") - start");
	}
	if (outputFile == null)
	    throw new IllegalArgumentException("outputFile cannot be null");

	try {
	    outputWriter = new PrintWriter(outputFile, CHARSET);
	} catch (UnsupportedEncodingException ignore) {
	    logger.warn("OutputFileWriter(File) - exception ignored", ignore);
	} catch (FileNotFoundException ignore) {
	    logger.warn("OutputFileWriter(File) - exception ignored", ignore);
	}

	if (logger.isDebugEnabled()) {
	    logger.debug("OutputFileWriter(File outputFile=" + outputFile
		    + ") - end");
	}
    }

    public void write(List<AnagramSet> anagramsList) {
	if (logger.isDebugEnabled()) {
	    logger.debug("write - start");
	}

	for (AnagramSet element : anagramsList) {
	    outputWriter.println(element);
	}

	if (logger.isDebugEnabled()) {
	    logger.debug("write - end");
	}
    }

    @Override
    public void close() throws IOException {
	if (outputWriter != null)
	    outputWriter.close();
    }

}
