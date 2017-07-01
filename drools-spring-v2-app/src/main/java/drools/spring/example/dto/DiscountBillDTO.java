package drools.spring.example.dto;

public class DiscountBillDTO {

	private int discount;
	
	public DiscountBillDTO(){}

	public DiscountBillDTO(int discount) {
		super();
		this.discount = discount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}
