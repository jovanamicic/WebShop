package com.app.dto;

public class ProductCategoruFullDTO {
	
	private int id;
	private String name;
	private ProductCategoryDTO category;
	private int maxDiscount;
	
	public ProductCategoruFullDTO(){}

	public ProductCategoruFullDTO(int id, String name, ProductCategoryDTO category, int maxDiscount) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.maxDiscount = maxDiscount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategoryDTO getCategory() {
		return category;
	}

	public void setCategory(ProductCategoryDTO category) {
		this.category = category;
	}

	public int getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	
}
