package ua.com.globallogic.basecamp.sergiichuk.urlRwManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import ua.com.globallogic.basecamp.sergiichuk.urlRwManager.exception.WrongUrlException;

public class PageRWManager implements RWManager {
    HttpClient client;
    HttpUriRequest request;
    HttpResponse response;

    public PageRWManager() {
	client = HttpClientBuilder.create().build();
    }

    @Override
    public String readPage(String url) throws WrongUrlException, IOException {
	String readedPage = "";
	URL pageUrl = null;
	try {
	    pageUrl = new URL(url);
	    request = new HttpGet(pageUrl.toURI());
	    response = client.execute(request);
	    StringBuilder sb = new StringBuilder();
	    try (BufferedReader bf = new BufferedReader(new InputStreamReader(
		    response.getEntity().getContent()));) {
		int read = 0;
		char[] cbuf = new char[1024];
		while ((read = bf.read(cbuf)) != -1) {
		    sb.append(cbuf, 0, read);
		}
	    }
	    readedPage = sb.toString();
	} catch (MalformedURLException | URISyntaxException
		| ClientProtocolException e) {
	    throw new WrongUrlException("URL is not valid", e);
	}
	return readedPage;
    }

    @Override
    public void writeUrlContentToFile(String url, String pageContent) {
	// TODO Auto-generated method stub
    }
}
