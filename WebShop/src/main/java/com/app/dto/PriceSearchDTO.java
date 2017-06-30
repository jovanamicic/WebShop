package com.app.dto;

public class PriceSearchDTO {
	
	private String minPrice;
	private String maxPrice;
	
	public PriceSearchDTO(){}
	
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	

}
