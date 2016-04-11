package com.websitescraper.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.websitescraper.businessobjects.Product;
import com.websitescraper.businessobjects.Website;

public class ProductPageScraperTest {

	ProductPageScraper scraperForPositiveTestCases;
	ProductPageScraper scraperForNegativeTestCases;

	public ProductPageScraperTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		Website correctWebsite = new Website(
				"http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-apricot-ripe---ready-320g.html");
		scraperForPositiveTestCases = new ProductPageScraper(correctWebsite);
		Website inCorrectWebsite = new Website("https://www.google.com");
		scraperForNegativeTestCases = new ProductPageScraper(inCorrectWebsite);
	}

	@After
	public void tearDown() {
		scraperForPositiveTestCases = null;
		scraperForNegativeTestCases = null;
	}

	@Test
	public void testGetProductDataReturnsNull() {
		Product product = scraperForNegativeTestCases.getProductData();
		assertNull(product);
	}

	@Test
	public void testGetProductDataReturnsObject() {
		Product product = scraperForPositiveTestCases.getProductData();
		assertNotNull(product);
		assertNotNull(product.getTitle());
		assertTrue(product.getSize() > 0);
		assertTrue(product.getUnitPrice() == 3.50f);
		assertNotNull(product.getDescription());
		assertTrue(product.getDescription().length() > 0);
	}

}
