package ua.com.globallogic.basecamp.sergiichuk.linksParser;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlLinkParser {

    public final static String WELL_FORMED_LINKS_REGEXP = "[\\\"\\'](https?\\:\\/\\/)([\\w\\.]+)\\.([a-z]{2,6})([\\p{ASCII}&&[^\\\"\\'\\>\\<]])+?[\\\"\\']";
    public final static String RELATIVE_LINKS_REGEXP = "[\\\"\\']\\/([\\p{ASCII}&&[^\\\"\\'\\>\\<]])+?[\\\"\\']";
    public final static String ROOT_ELEMENT_LINK_REGEXP = "(https?\\:\\/\\/)([\\w\\.]+)\\.([a-z]{2,6})\\/?";
    public final static String LINK_TO_FILENAME_REGEXP = "[\\\\\\?\\/\\:\\*\\|\\\"\\<\\>]";
    public final static String REMOVE_ARGUMENT_LINK_REGEXP = "[^\\?]+";
    public final static String GET_EXTENSION_FROM_MIME_TYPE_REGEXP = "\\/.+";
    private final static String QUOTES_REGEXP = "[\\\"\\']";

    private final static int MINIMUM_ROOT_ELEMENT_LENGTH = 12;

    private final static Pattern wellFormedLinksPattern = Pattern.compile(
	    WELL_FORMED_LINKS_REGEXP, Pattern.CASE_INSENSITIVE);

    private final static Pattern relativeLinksPattern = Pattern.compile(
	    RELATIVE_LINKS_REGEXP, Pattern.CASE_INSENSITIVE);

    private final static Pattern rootElementLinkPattern = Pattern.compile(
	    ROOT_ELEMENT_LINK_REGEXP, Pattern.CASE_INSENSITIVE);

    private final static Pattern removeArgumentFromLinkPattern = Pattern
	    .compile(REMOVE_ARGUMENT_LINK_REGEXP, Pattern.CASE_INSENSITIVE);
    private final static Pattern getExtensionFromMimeType = Pattern.compile(
	    GET_EXTENSION_FROM_MIME_TYPE_REGEXP, Pattern.CASE_INSENSITIVE);

    /**
     * Parse given text to match HTML well formed links pattern and return set
     * of matched links
     * 
     * @param text
     *            text to parse
     * @throws IllegalArgumentException
     *             if given text is null or empty
     * @return set with matched links
     */
    public Set<String> getWellFormedLinks(String text) {
	if (text == null || text.isEmpty())
	    throw new IllegalArgumentException(
		    "Text argument cannot be null or empty.");
	Matcher wellFormedLinksMatcher = wellFormedLinksPattern.matcher(text);

	Set<String> linksSet = new LinkedHashSet<String>();
	while (wellFormedLinksMatcher.find()) {
	    linksSet.add(wellFormedLinksMatcher.group().replaceAll(
		    QUOTES_REGEXP, ""));
	}
	return linksSet;
    }

    /**
     * Parse given text to match HTML relative links pattern and add rootElement
     * to found relative links
     * 
     * @param text
     *            text to parse
     * @param rootElement
     *            root link element. For example http://google.com is root for
     *            /relative/link/index.html
     * @throws IllegalArgumentException
     *             if given text or rootElement is null or empty, or rootElement
     *             does not match root link pattern
     * @return set with matched links
     */
    public Set<String> getRelativeLinks(String text, String rootElement) {
	if (text == null || text.isEmpty())
	    throw new IllegalArgumentException(
		    "Text argument cannot be null or empty.");
	if (rootElement == null
		|| rootElement.length() < MINIMUM_ROOT_ELEMENT_LENGTH
		|| !Pattern.matches(ROOT_ELEMENT_LINK_REGEXP,
			rootElement.toLowerCase()))
	    throw new IllegalArgumentException(
		    "Root element cannot be null or empty and must be a valid root link.");
	Set<String> linksSet = new LinkedHashSet<String>();
	Matcher relativeLinksMatcher = relativeLinksPattern.matcher(text);
	if (rootElement.endsWith("/"))
	    rootElement = rootElement.substring(0, rootElement.length() - 1);
	while (relativeLinksMatcher.find()) {
	    linksSet.add(rootElement
		    + relativeLinksMatcher.group()
			    .replaceAll(QUOTES_REGEXP, ""));
	}
	return linksSet;
    }

    /**
     * Parse given text to match HTML well formed and relative links pattern and
     * add rootElement to relative links
     * 
     * @param text
     *            text to parse
     * @param rootElement
     *            root link element. For example http://google.com is root for
     *            /relative/link/index.html
     * @throws IllegalArgumentException
     *             if given text or rootElement is null or empty, or rootElement
     *             does not match root link pattern
     * @return set with matched links
     */
    public Set<String> getLinks(String text, String rootElement) {
	Set<String> linksSet = new LinkedHashSet<String>();
	linksSet.addAll(getWellFormedLinks(text));
	linksSet.addAll(getRelativeLinks(text, rootElement));
	return linksSet;
    }

    /**
     * Parse given text to match HTML well formed links pattern and return list
     * of matched links
     * 
     * @param text
     *            text to parse
     * @throws IllegalArgumentException
     *             if given text is null or empty
     * @return list with matched links
     */
    public static Set<String> getWellFormedLinksListStatic(String text) {
	return (new HtmlLinkParser()).getWellFormedLinks(text);
    }

    /**
     * Parse given text to match HTML relative links pattern and add rootElement
     * to found relative links
     * 
     * @param text
     *            text to parse
     * @param rootElement
     *            root link element. For example http://google.com is root for
     *            /relative/link/index.html
     * @throws IllegalArgumentException
     *             if given text or rootElement is null or empty, or rootElement
     *             does not match root link pattern
     * @return list with matched links
     */
    public static Set<String> getRelativeLinksListStatic(String text,
	    String rootElement) {
	return (new HtmlLinkParser()).getRelativeLinks(text, rootElement);
    }

    /**
     * Parse given text to match HTML well formed and relative links pattern and
     * add rootElement to relative links
     * 
     * @param text
     *            text to parse
     * @param rootElement
     *            root link element. For example http://google.com is root for
     *            /relative/link/index.html
     * @throws IllegalArgumentException
     *             if given text or rootElement is null or empty, or rootElement
     *             does not match root link pattern
     * @return list with matched links
     */
    public static Set<String> getLinksListStatic(String text, String rootElement) {
	return (new HtmlLinkParser()).getLinks(text, rootElement);
    }

    /**
     * Return root element from given link
     * 
     * @param link
     *            link to get root element
     * @return root element from given link or null, if there link is not valid
     */
    public String getRootElementFromLink(String link) {
	if (link == null || link.isEmpty())
	    throw new IllegalArgumentException("Link cannot be null or empty");
	Matcher rootElementFromLinkMatcher = rootElementLinkPattern
		.matcher(link);
	if (rootElementFromLinkMatcher.find())
	    return rootElementFromLinkMatcher.group();
	return null;
    }

    public static String getFileNameFromLink(String link) {
	if (link == null || link.isEmpty())
	    throw new IllegalArgumentException("Link cannot be null or empty");
	return link.replaceAll(LINK_TO_FILENAME_REGEXP, "_").trim();
    }

    public static String removeArgumentsFromLink(String link) {
	if (link == null || link.isEmpty())
	    throw new IllegalArgumentException("Link cannot be null or empty");
	Matcher removeArgumentsMatcher = removeArgumentFromLinkPattern
		.matcher(link);
	if (removeArgumentsMatcher.find())
	    return removeArgumentsMatcher.group();
	return link;
    }

    /**
     * Return extension from valid mime type with leading '/' symbol
     * 
     * @param mimeType
     *            valid mime type
     * @return extension, if can be matcher, otherwise - empty("")
     */
    public static String getExtensionFromMimeType(String mimeType) {
	if (mimeType == null || mimeType.isEmpty())
	    throw new IllegalArgumentException(
		    "Mime type cannot be null or empty");
	Matcher getExtensionFromMimeTypeMatcher = getExtensionFromMimeType
		.matcher(mimeType);
	if (getExtensionFromMimeTypeMatcher.find())
	    return getExtensionFromMimeTypeMatcher.group();
	return "";
    }
}
