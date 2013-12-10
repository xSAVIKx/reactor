package ua.com.globallogic.basecamp.sergiichuk;

import java.io.IOException;
import java.util.Set;

import ua.com.globallogic.basecamp.sergiichuk.linksParser.HtmlLinkParser;
import ua.com.globallogic.basecamp.sergiichuk.urlRwManager.PageRWManager;
import ua.com.globallogic.basecamp.sergiichuk.urlRwManager.RWManager;
import ua.com.globallogic.basecamp.sergiichuk.urlRwManager.exception.WrongUrlException;

public class Demo {
    private static String URL = "http://www.w3schools.com/html/tryit.asp?filename=tryhtml_link";

    /**
     * @param args
     */
    public static void main(String[] args) {
	HtmlLinkParser htmlParser = new HtmlLinkParser();
	RWManager pageReader = new PageRWManager();
	String pageContent = "";
	try {
	    pageContent = pageReader.readPage(URL);
	} catch (WrongUrlException | IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	Set<String> links = htmlParser.getLinks(pageContent,
		"http://www.w3schools.com");
	for (String element : links)
	    System.out.println(element);
    }

}
