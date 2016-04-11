package com.websitescraper.console;

import java.io.InputStreamReader;
import java.util.Scanner;

import com.websitescraper.businessobjects.Website;
import com.websitescraper.service.WebsiteScraper;

/*
 * 
 * This is the console class to run this scrapper.
 */

public class Console {
	public static final void main(String[] args) {
		System.out.println("Please enter your input: ");
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		String url = scanner.nextLine();
		
		Website website = new Website(url);
		WebsiteScraper scraper = new WebsiteScraper(website);
		System.out.println(scraper.scrapeWebsite());
	}
}
