package ua.com.globallogic.basecamp.sergiichuk.urlRwManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import ua.com.globallogic.basecamp.sergiichuk.linksParser.HtmlLinkParser;
import ua.com.globallogic.basecamp.sergiichuk.linksParser.link.Link;
import ua.com.globallogic.basecamp.sergiichuk.propertiesManager.PropertiesManager;
import ua.com.globallogic.basecamp.sergiichuk.propertiesManager.Property;
import ua.com.globallogic.basecamp.sergiichuk.urlRwManager.exception.WrongUrlException;

public class PageRWManager implements RWManager {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(PageRWManager.class);

    private HttpClient client;
    private HttpUriRequest request;
    private HttpResponse response;
    private Properties properties;

    public PageRWManager() {
	client = HttpClientBuilder.create().build();
	properties = (new PropertiesManager()).getProperties();
    }

    @Override
    public Link readPage(String url) throws WrongUrlException, IOException {
	if (logger.isDebugEnabled()) {
	    logger.debug("readPage(String url=" + url + ") - start"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	Link readedPage = new Link(url);
	try {
	    URL pageUrl = new URL(url);
	    readedPage.setUrl(pageUrl);
	    readedPage.setUri(pageUrl.toURI());
	    request = new HttpGet(readedPage.getUri());
	    response = client.execute(request);
	    List<byte[]> pageContent = new ArrayList<>();
	    // write page content to byte list using buffered IS
	    try (BufferedInputStream bf = new BufferedInputStream(response
		    .getEntity().getContent());) {
		int read = 0;
		byte[] cbuf = new byte[1024];
		while ((read = bf.read(cbuf)) != -1) {
		    byte[] tmp = Arrays.copyOf(cbuf, read);
		    pageContent.add(tmp);
		}
	    }
	    readedPage.setPageContent(pageContent);
	    // get content type and charset
	    HttpEntity entity = response.getEntity();
	    ContentType contType = null;
	    if (entity != null) {
		contType = ContentType.get(entity);
		if (contType != null) {
		    readedPage.setMimeType(contType.getMimeType());
		    readedPage.setCharset(contType.getCharset());
		}
	    }
	    // get mimeType and make extension for current link
	    String mimeType = readedPage.getMimeType();
	    String extension = "";
	    if (mimeType != null && !mimeType.isEmpty())
		extension = HtmlLinkParser.getExtensionFromMimeType(mimeType)
			.replace('/', '.');
	    // remove agrs from link (args contain special symbols, so they
	    // could not be in fileName)
	    String fileName = HtmlLinkParser.removeArgumentsFromLink(readedPage
		    .getLink());
	    if (extension.isEmpty()) {
		extension = ".html";
	    }
	    // create filename for current link
	    String fileNameToWrite = HtmlLinkParser
		    .getFileNameFromLink(fileName) + extension;
	    readedPage.setFileNameToWrite(fileNameToWrite);

	} catch (MalformedURLException | URISyntaxException
		| ClientProtocolException e) {
	    logger.error("readPage(String)", e); //$NON-NLS-1$

	    throw new WrongUrlException("URL is not valid", e);
	}

	if (logger.isDebugEnabled()) {
	    logger.debug("readPage(String url=" + url + ") - end - return value=" + readedPage); //$NON-NLS-1$ //$NON-NLS-2$
	}
	return readedPage;
    }

    @Override
    public void writeLink(Link link) throws IOException {
	if (logger.isDebugEnabled()) {
	    logger.debug("writeUrlContentToFile(Link link=" + link + ") - start"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	String folderToWrite = properties.getProperty(Property.RESULT_FOLDER
		.getName());
	File folderToWriteFile = new File(folderToWrite);
	if (!folderToWriteFile.isDirectory())
	    if (!folderToWriteFile.mkdir())
		throw new IOException();
	try (BufferedOutputStream bos = new BufferedOutputStream(
		new FileOutputStream(new File(folderToWrite,
			link.getFileNameToWrite())));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
	    for (byte[] element : link.getPageContent()) {
		baos.write(element, 0, element.length);
	    }
	    baos.writeTo(bos);

	}

	if (logger.isDebugEnabled()) {
	    logger.debug("writeUrlContentToFile(Link link=" + link + ") - end"); //$NON-NLS-1$ //$NON-NLS-2$
	}
    }
}
