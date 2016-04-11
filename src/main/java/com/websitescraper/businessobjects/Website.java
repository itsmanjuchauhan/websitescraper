package com.websitescraper.businessobjects;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/*
 * 
 * This class holds the web-site URL and JSOUP document.
 */
public class Website {

	private String url;
	private Document document;
	private static final Logger LOG = Logger.getLogger(Website.class.getName());

	public Website(String url) {
		super();
		this.url = url;
		setDocument();
	}

	public void setDocument() {

		try {
			Connection connection = Jsoup.connect(url.toString());
			document = connection.get();
		} catch (MalformedURLException mue) {
			LOG.log(Level.SEVERE, null, mue);
			document = null;
		} catch (IOException iox) {
			LOG.log(Level.SEVERE, null, iox);
			document = null;
		} catch (Exception e) {
			LOG.log(Level.SEVERE, null, e);
			document = null;
		}

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Document getDocument() {
		return document;
	}

}
