package com.websitescraper.service;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.websitescraper.businessobjects.Website;

public class WebsiteScraperTest {

	WebsiteScraper scraperForPositiveTestCases;
	WebsiteScraper scraperForNegativeTestCases;

	public WebsiteScraperTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		Website website = new Website(
				"http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
		scraperForPositiveTestCases = new WebsiteScraper(website);
		Website inCorrectWebsite = new Website("https://www.google.com");
		scraperForNegativeTestCases = new WebsiteScraper(inCorrectWebsite);
	}

	@After
	public void tearDown() {
		scraperForPositiveTestCases = null;
		scraperForNegativeTestCases = null;
	}

	@Test
	public void testScrapeWebsiteReturnsResults() {
		String json = scraperForPositiveTestCases.scrapeWebsite();
		assertTrue(json.contains("total") && json.contains("results"));
	}


	@Test
	public void testScrapeReturnsProductDescription() {
		String json = scraperForPositiveTestCases.scrapeWebsite();
		assertTrue(json.contains("Apricots"));
	}
	
	@Test
	public void testScrapeReturnsNoProductsForNonSainsburySites() {
		String json = scraperForNegativeTestCases.scrapeWebsite();
		assertTrue(json.contentEquals("{}"));
	}

}
