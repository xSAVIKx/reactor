package ua.com.globallogic.basecamp.sergiichuk;

import org.apache.log4j.Logger;

public class Main {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(Main.class);

    private static void usage() {
	System.out.println("java -jar jarFileName.jar URL");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	if (logger.isDebugEnabled()) {
	    logger.debug("main(String[] args=" + args + ") - start");
	}

	if (args.length != 1) {
	    usage();

	    if (logger.isDebugEnabled()) {
		logger.debug("main(String[] args=" + args + ") - end");
	    }
	    return;
	}
	Runner runner = new HtmlLinkParserRunner(args[0]);
	runner.run();

	if (logger.isDebugEnabled()) {
	    logger.debug("main(String[] args=" + args + ") - end");
	}
    }

}
