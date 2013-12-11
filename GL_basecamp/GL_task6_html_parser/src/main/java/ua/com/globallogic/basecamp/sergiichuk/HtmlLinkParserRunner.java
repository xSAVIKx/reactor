package ua.com.globallogic.basecamp.sergiichuk;

import java.io.IOException;
import java.util.Set;

import org.apache.log4j.Logger;

import ua.com.globallogic.basecamp.sergiichuk.linksParser.HtmlLinkParser;
import ua.com.globallogic.basecamp.sergiichuk.linksParser.link.Link;
import ua.com.globallogic.basecamp.sergiichuk.propertiesManager.PropertiesManager;
import ua.com.globallogic.basecamp.sergiichuk.propertiesManager.Property;
import ua.com.globallogic.basecamp.sergiichuk.urlRwManager.PageRWManager;
import ua.com.globallogic.basecamp.sergiichuk.urlRwManager.RWManager;
import ua.com.globallogic.basecamp.sergiichuk.urlRwManager.exception.WrongUrlException;

public class HtmlLinkParserRunner implements Runner {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
	    .getLogger(HtmlLinkParserRunner.class);

    private String URL;

    public HtmlLinkParserRunner(String URL) {
	this.URL = URL;
    }

    @Override
    public void run() {
	if (logger.isDebugEnabled()) {
	    logger.debug("run() - start");
	}

	HtmlLinkParser htmlParser = new HtmlLinkParser();
	RWManager rwManager = new PageRWManager();
	Link link = null;
	try {
	    link = rwManager.readPage(URL);
	} catch (WrongUrlException e) {
	    logger.error("run()", e);

	    System.err.println("Possible java-script or wrong URL: "
		    + e.getLocalizedMessage());

	    if (logger.isDebugEnabled()) {
		logger.debug("run() - end");
	    }
	    return;
	} catch (IOException e) {
	    logger.error("run()", e);

	    System.err.println("Writing error");
	}
	try {
	    rwManager.writeLink(link);
	} catch (IOException e) {
	    logger.error("run()", e);

	    System.err.println("Writing error");
	}
	PropertiesManager propManager = new PropertiesManager();
	String loopAmount = propManager.getProperties().getProperty(
		Property.NESTING_LEVEL.getName());
	int loops = Integer.valueOf(loopAmount);
	makeLoopRun(htmlParser, rwManager, link, loops);

	if (logger.isDebugEnabled()) {
	    logger.debug("run() - end");
	}
    }

    private void makeLoopRun(HtmlLinkParser linkParser, RWManager rwManager,
	    Link initialLink, int loopAmount) {
	for (int i = 0; i < loopAmount; i++) {
	    Set<String> links = linkParser.getLinks(
		    initialLink.getPageContentAsString(),
		    linkParser.getRootElementFromLink(initialLink.getLink()));

	    for (String element : links) {
		try {
		    Link link = rwManager.readPage(element);
		    rwManager.writeLink(link);
		    makeLoopRun(linkParser, rwManager, link, i);
		} catch (WrongUrlException e) {
		    logger.error("run()", e);

		    System.err.println("Possible java-script or wrong URL: "
			    + e.getLocalizedMessage());
		} catch (IOException e) {
		    logger.error("run()", e);

		    System.err.println("Writing error");
		}
	    }
	}
    }

}
