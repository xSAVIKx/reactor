package ua.com.globallogic.basecamp.sergiichuk.anagram;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class OutputFileWriter implements Closeable {
    private final static String CHARSET = "UTF-8";
    private PrintWriter outputWriter;

    public OutputFileWriter(File outputFile) {
	try {
	    outputWriter = new PrintWriter(outputFile, CHARSET);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
    }

    public void write(List<AnagramSet> anagramsList) {
	for (AnagramSet element : anagramsList) {
	    outputWriter.println(element);
	}
    }

    @Override
    public void close() throws IOException {
	if (outputWriter != null)
	    outputWriter.close();
    }

}
