package ua.com.globallogic.basecamp.sergiichuk.urlRwManager;

import java.io.IOException;

import ua.com.globallogic.basecamp.sergiichuk.linksParser.link.Link;
import ua.com.globallogic.basecamp.sergiichuk.urlRwManager.exception.WrongUrlException;

public interface RWManager {
    /**
     * Tries to read page with given Link content
     * 
     * @param Link
     *            link of page
     * @return page content
     * @throws WrongUrlException
     *             if URL cannot be proceeded
     * @throws IOException
     *             if reading error occurred
     */
    public Link readPage(String url) throws WrongUrlException, IOException;

    /**
     * Write given Link to file
     * 
     * @param link
     *            link to write
     * @throws IOException
     */
    public void writeLink(Link link) throws IOException;
}
