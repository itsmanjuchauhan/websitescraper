package com.websitescraper.businessobjects;

import org.json.simple.JSONObject;

/*
 *
 * This class holds the product details
 */
public class Product {
	
	private String title;
    private float size;
    private float unitPrice;
    private String description;
	
    public Product() {
		super();
		title = "";
		size = 0.0f;
		unitPrice = 0.0f;
		description = "";
	}

	public Product(String title, float size, float unitPrice, String description) {
        this.title = title;
        this.size = size;
        this.unitPrice = unitPrice;
        this.description = description;
    }
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
        JSONObject ret = new JSONObject();
        ret.put("title", title);
        ret.put("size", size);
        ret.put("unit_price", unitPrice);
        ret.put("description", description);
        
        return ret;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
