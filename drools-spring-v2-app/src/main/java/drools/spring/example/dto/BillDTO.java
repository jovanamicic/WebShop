package drools.spring.example.dto;

import java.util.List;

public class BillDTO {
	
	private int id;
	private double finalPrice;
	private double originalPrice;
	private int discount;
	private int spentPoints;
	private int gainedPoints;
	private List<DiscountItemDTO> discountItems;
	private List<DiscountBillDTO> discountBill;
	
	public BillDTO(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getSpentPoints() {
		return spentPoints;
	}

	public void setSpentPoints(int spendPoints) {
		this.spentPoints = spendPoints;
	}

	public int getGainedPoints() {
		return gainedPoints;
	}

	public void setGainedPoints(int gainedPoints) {
		this.gainedPoints = gainedPoints;
	}

	public List<DiscountItemDTO> getDiscountItems() {
		return discountItems;
	}

	public void setDiscountItems(List<DiscountItemDTO> discountItems) {
		this.discountItems = discountItems;
	}

	public List<DiscountBillDTO> getDiscountBill() {
		return discountBill;
	}

	public void setDiscountBill(List<DiscountBillDTO> discountBill) {
		this.discountBill = discountBill;
	}
	
}
