package ua.com.globallogic.basecamp.sergiichuk.anagram;

import org.apache.log4j.Logger;

import java.io.File;

public class Main {
    private final static int readLinesAmount = 200;
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(Main.class);

    private static void usage() {
	if (logger.isDebugEnabled()) {
	    logger.debug("usage() - start");
	}

	System.out
		.println("usage: anagram inputFileName outputFileName\n inputFileName should not be equals to outputFileName");

	if (logger.isDebugEnabled()) {
	    logger.debug("usage() - end");
	}
    }

    public static void main(String[] args) {
	if (logger.isDebugEnabled()) {
	    logger.debug("main(String[] args=" + args + ") - start");
	}

	if (args.length != 2 || args[0].equalsIgnoreCase(args[1])) {
	    usage();

	    if (logger.isDebugEnabled()) {
		logger.debug("main(String[] args=" + args + ") - end");
	    }
	    return;
	}
	File input = new File(args[0]);
	if (!input.exists() || input.isDirectory()) {
	    logger.error(
		    "main() - "
			    + String.format("No such file '%s' was found.",
				    args[0]), null);

	    if (logger.isDebugEnabled()) {
		logger.debug("main(String[] args=" + args + ") - end");
	    }
	    return;
	}
	File output = new File(args[1]);
	Runner setRunner = new SetRunner(readLinesAmount);
	setRunner.run(input, output);

	if (logger.isDebugEnabled()) {
	    logger.debug("main(String[] args=" + args + ") - end");
	}
    }

}
