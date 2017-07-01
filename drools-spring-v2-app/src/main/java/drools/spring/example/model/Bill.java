package drools.spring.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "date", unique = false, nullable = false)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "customer", referencedColumnName = "username", nullable = false)
	private Customer customer;
	
	@Column(name = "state", unique = false, nullable = false)
	private String state;
	
	@Column(name = "original_total_price", unique = false, nullable = true)
	private double originalTotalPrice;
	
	@Column(name = "final_price", unique = false, nullable = true)
	private double finalPrice;
	
	@Column(name = "discount", unique = false, nullable = true)
	private int discount;
	
	@Column(name = "coupons_spent", unique = false, nullable = true)
	private double couponsSpent;

	@Column(name = "coupons_gained", unique = false, nullable = true)
	private double couponsGained;
	
	@OneToMany(mappedBy="bill")
	Set<DiscountBill> discountsBill = new HashSet<DiscountBill>();
	
	@OneToMany(mappedBy="bill")
	Set<Item> items = new HashSet<Item>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getOriginalTotalPrice() {
		return originalTotalPrice;
	}

	public void setOriginalTotalPrice(double originalTotalPrice) {
		this.originalTotalPrice = originalTotalPrice;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getCouponsSpent() {
		return couponsSpent;
	}

	public void setCouponsSpent(double couponsSpent) {
		this.couponsSpent = couponsSpent;
	}

	public double getCouponsGained() {
		return couponsGained;
	}

	public void setCouponsGained(double couponsGained) {
		this.couponsGained = couponsGained;
	}

	public Set<DiscountBill> getDiscountsBill() {
		return discountsBill;
	}

	public void setDiscountsBill(Set<DiscountBill> discountsBill) {
		this.discountsBill = discountsBill;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", date=" + date + ", customer=" + customer + ", state=" + state
				+ ", originalTotalPrice=" + originalTotalPrice + ", finalPrice=" + finalPrice + ", discount=" + discount
				+ ", couponsSpent=" + couponsSpent + ", couponsGained=" + couponsGained + ", discountsBill="
				+ discountsBill + ", items=" + items + "]";
	}
	
}
