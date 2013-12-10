package ua.com.globallogic.basecamp.sergiichuk.linksParser;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class HtmlLinkParserTest {
    private HtmlLinkParser htmlLinkParser;
    private String httpsLinkRootElement = "https://www.evernote.com/";
    private String httpsLink = "https://www.evernote.com/shard/s201/sh/751d6ccf-8864-4609-8e88-a234a6ee85fd/000554222e869af7f0dedd7094d18241";
    private String httpLink = "http://www.evernote.com/shard/s201/sh/751d6ccf-8864-4609-8e88-a234a6ee85fd/000554222e869af7f0dedd7094d18241/thm/note/751d6ccf-8864-4609-8e88-a234a6ee85fd";
    private String textToParseWellFormed = "<meta property=\"og:url\" content=\""
	    + httpsLink + "\"/>" + "content=\"" + httpLink + "\"/>";
    private String rootElementForRelLinks = "http://google.com";
    private String relIconLink = "/favicon.ico";
    private String relCssLink = "/redesign/global/css/sharedNote.css";
    private String textToParseRelative = "<link rel=\"Shortcut Icon\" href=\""
	    + relIconLink + "\" type=\"image/x-icon\" />"
	    + "<link rel=\"stylesheet\" href=\"" + relCssLink + "\" />";
    private Set<String> wellFormedLinks;
    private Set<String> relativeLinks;
    private Set<String> links;

    @Before
    public void setUp() throws Exception {
	htmlLinkParser = new HtmlLinkParser();

	relativeLinks = new LinkedHashSet<>();
	relativeLinks.add(rootElementForRelLinks + relIconLink);
	relativeLinks.add(rootElementForRelLinks + relCssLink);
	wellFormedLinks = new LinkedHashSet<>();
	wellFormedLinks.add(httpsLink);
	wellFormedLinks.add(httpLink);
	links = new LinkedHashSet<>();
	links.add(httpsLink);
	links.add(httpLink);
	links.add(rootElementForRelLinks + relIconLink);
	links.add(rootElementForRelLinks + relCssLink);
    }

    @Test
    public void testGetRootElementFromLink() {
	assertEquals(httpsLinkRootElement,
		htmlLinkParser.getRootElementFromLink(httpsLink));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRootElementFromLinkIllegalArgumentExceptionNull() {
	htmlLinkParser.getRootElementFromLink(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRootElementFromLinkIllegalArgumentExceptionEmpty() {
	htmlLinkParser.getRootElementFromLink("");
    }

    @Test
    public void testGetRootElementNotValidLink() {
	assertNull(htmlLinkParser.getRootElementFromLink(relCssLink));
    }

    @Test
    public void testGetLinks() {
	assertArrayEquals(
		links.toArray(),
		htmlLinkParser.getLinks(
			textToParseWellFormed + textToParseRelative,
			rootElementForRelLinks).toArray());
    }

    @Test
    public void testGetWellFormedLinks() {
	assertEquals(wellFormedLinks,
		htmlLinkParser.getWellFormedLinks(textToParseWellFormed));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWellFormedLinksIllegalArgumentExceptionNull() {
	htmlLinkParser.getWellFormedLinks(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWellFormedLinksIllegalArgumentExceptionEmpty() {
	htmlLinkParser.getWellFormedLinks("");
    }

    @Test
    public void testGetRelativeLinks() {
	assertEquals(relativeLinks, htmlLinkParser.getRelativeLinks(
		textToParseRelative, rootElementForRelLinks));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRelativeLinksIllegalArgumentExceptionNullText() {
	htmlLinkParser.getRelativeLinks(null, rootElementForRelLinks);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRelativeLinksIllegalArgumentExceptionEmptyText() {
	htmlLinkParser.getRelativeLinks(null, rootElementForRelLinks);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRelativeLinksIllegalArgumentExceptionRootElementNotValid() {
	htmlLinkParser.getRelativeLinks(textToParseRelative,
		1 + rootElementForRelLinks);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRelativeLinksIllegalArgumentExceptionRootElementNull() {
	htmlLinkParser.getRelativeLinks(textToParseRelative, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRelativeLinksIllegalArgumentExceptionRootElementNotEnoughLength() {
	htmlLinkParser.getRelativeLinks(textToParseRelative, "1234567890-");
    }

}
