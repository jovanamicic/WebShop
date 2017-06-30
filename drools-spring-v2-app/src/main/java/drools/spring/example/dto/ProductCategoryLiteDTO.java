package drools.spring.example.dto;

public class ProductCategoryLiteDTO {
	
	private String name;
	private String maxDiscount;
	private int category;
	
	public ProductCategoryLiteDTO(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(String maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

}
