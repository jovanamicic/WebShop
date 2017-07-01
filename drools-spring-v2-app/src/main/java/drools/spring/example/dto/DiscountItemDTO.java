package drools.spring.example.dto;

public class DiscountItemDTO {
	
	private String item;
	private int discount;
	
	public DiscountItemDTO(){}

	public DiscountItemDTO(String item, int discount) {
		super();
		this.item = item;
		this.discount = discount;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}
