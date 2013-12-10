package ua.com.globallogic.basecamp.sergiichuk.urlRwManager;

import java.io.IOException;

import ua.com.globallogic.basecamp.sergiichuk.urlRwManager.exception.WrongUrlException;

public interface RWManager {
    /**
     * Tries to read page with given URL content
     * 
     * @param url
     *            url of page
     * @return page content
     * @throws WrongUrlException
     *             if URL cannot be proceeded
     * @throws IOException
     *             if reading error occurred
     */
    public String readPage(String url) throws WrongUrlException, IOException;
    
    public void writeUrlContentToFile(String url, String pageContent);
}
