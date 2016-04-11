package com.websitescraper.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.websitescraper.businessobjects.Product;
import com.websitescraper.businessobjects.Website;

public class WebsiteScraper {

	private Website website;
	private static final Logger LOG = Logger.getLogger(WebsiteScraper.class.getName());

	public WebsiteScraper(Website website) {
		super();
		this.website = website;
	}

	/**
	 * Starts the scraping process, and produces JSON output.
	 * 
	 * @return String containing the JSON code.
	 */
	@SuppressWarnings("unchecked")
	public String scrapeWebsite() {
		JSONObject json = new JSONObject();
		JSONArray results = new JSONArray();
		json.put("results", results);
		// Product's total unit price.
		float total = 0.0f;

		Document document = website.getDocument();

		if (document == null) {
			// In case site is not reachable return empty JSON document
			return "{}";
		}

		try {
			Element productList = document.select("ul.productLister").first();
			if (productList == null) {
				// No products, return empty JSON document
				return "{}";
			}

			Elements elements = productList.getElementsByTag("li");
			for (Element element : elements) {
				Element productElement = element.select("div.productInfo").first();
				Element linkel = productElement.getElementsByTag("a").first();
				String productPage = linkel.attr("href");
				Website website = new Website(productPage);
				ProductPageScraper prodcutPageScrapper = new ProductPageScraper(website);
				Product productData = prodcutPageScrapper.getProductData();

				// Add Product object to the JSAON array.
				results.add(productData.toJSON());
				total += productData.getUnitPrice();
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		}

		// Set the total price /unit for all products.
		json.put("total", total);

		return json.toJSONString();
	}

	public Website getwebsite() {
		return website;
	}

	public void setWebsite(Website argwebsite) {
		website = argwebsite;
	}

}
