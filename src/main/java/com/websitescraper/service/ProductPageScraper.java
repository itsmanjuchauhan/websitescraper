package com.websitescraper.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.websitescraper.businessobjects.Product;
import com.websitescraper.businessobjects.Website;

public class ProductPageScraper {

	public Website website;
	private static final Logger LOG = Logger.getLogger(ProductPageScraper.class.getName());

	public ProductPageScraper(Website website) {
		super();
		this.website = website;
	}

	/**
	 * Connect to the product page and get scrape title, size, unit price and
	 * set return a Product object having these values set.
	 * 
	 * @param website
	 *            A String object holding a page address.
	 * @return Product object, in case of any problem in scraping elements which
	 *         are not scraped will contain default value.
	 */
	public Product getProductData() {

		Product product = new Product();

		try {
			Document doc = website.getDocument();
			Element el = doc.select("div.productTitleDescriptionContainer").first();
			if (el == null) {
				return null;
			}
			if (el != null) {
				// get product title
				Element titleElement = el.getElementsByTag("h1").first();
				product.setTitle(titleElement.text());

				// size of the product in kb
				product.setSize(doc.toString().length() / 1024);
			}

			// get price per unit
			el = doc.select("p.pricePerUnit").first();
			if (el != null) {
				String ptxt = el.text();
				ptxt = ptxt.replace("/unit", "");
				ptxt = ptxt.replace("£", "");
				product.setUnitPrice(Float.parseFloat(ptxt));
			}

			// get description.
			el = doc.select("div.productText").first();
			if (el != null) {
				product.setDescription(el.text());
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}

		return product;
	}
}