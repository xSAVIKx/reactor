package ua.com.globallogic.basecamp.sergiichuk.linksParser.link;

import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class Link {
    private String link;
    private List<byte[]> pageContent;
    private URL url;
    private URI uri;
    private String mimeType;
    private String fileNameToWrite;
    private Charset charset;

    public Link(String link) {
	this.link = link;
    }

    public String getLink() {
	return link;
    }

    public void setLink(String link) {
	this.link = link;
    }

    public String getPageContentAsString() {
	StringBuilder sb = new StringBuilder();
	for (byte[] element : pageContent) {
	    if (charset != null)
		sb.append(new String(element, charset));
	    else
		sb.append(new String(element));
	}
	return sb.toString();
    }

    public List<byte[]> getPageContent() {
	return pageContent;
    }

    public void setPageContent(List<byte[]> pageContent) {
	this.pageContent = pageContent;
    }

    public URL getUrl() {
	return url;
    }

    public void setUrl(URL url) {
	this.url = url;
    }

    public URI getUri() {
	return uri;
    }

    public void setUri(URI uri) {
	this.uri = uri;
    }

    public String getMimeType() {
	return mimeType;
    }

    public void setMimeType(String mimeType) {
	this.mimeType = mimeType;
    }

    public String getFileNameToWrite() {
	return fileNameToWrite;
    }

    public void setFileNameToWrite(String fileNameToWrite) {
	this.fileNameToWrite = fileNameToWrite;
    }

    public Charset getCharset() {
	return charset;
    }

    public void setCharset(Charset charset) {
	this.charset = charset;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Link [link=");
	builder.append(link);
	builder.append(", url=");
	builder.append(url);
	builder.append(", uri=");
	builder.append(uri);
	builder.append(", mimeType=");
	builder.append(mimeType);
	builder.append(", fileNameToWrite=");
	builder.append(fileNameToWrite);
	builder.append(", charset=");
	builder.append(charset);
	builder.append("]");
	return builder.toString();
    }

}
