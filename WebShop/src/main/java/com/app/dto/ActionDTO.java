package com.app.dto;

import java.util.Date;
import java.util.List;

public class ActionDTO {
	
	private String name;
	private int discount;
	private String from;
	private String to;
	private List<ProductCategoryDTO> categories;
	
	public ActionDTO(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<ProductCategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategoryDTO> categories) {
		this.categories = categories;
	}
	
}
