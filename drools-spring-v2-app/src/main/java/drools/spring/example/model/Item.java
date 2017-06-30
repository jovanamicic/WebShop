package drools.spring.example.model;

import java.io.Serializable;
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
@Table(name = "item")
public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "bill", referencedColumnName = "bill_id", nullable = true)
	private Bill bill;
	
	@Column(name = "num", unique = false, nullable = false)
	private int num;
	
	@ManyToOne
	@JoinColumn(name = "product", referencedColumnName = "product_id", nullable = true)
	private Product product;

	@Column(name = "unit_price", unique = false, nullable = false)
	private double unitPrice;
	
	@Column(name = "quantity", unique = false, nullable = false)
	private int quantity;
	
	@Column(name = "original_price", unique = false, nullable = false)
	private double originalPrice;
	
	@Column(name = "discount", unique = false, nullable = false)
	private int discount;
	
	@Column(name = "final_price", unique = false, nullable = false)
	private double finalPrice;
	
	@OneToMany(mappedBy="item")
	Set<DiscountItem> discountsItems = new HashSet<DiscountItem>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Set<DiscountItem> getDiscountsItems() {
		return discountsItems;
	}

	public void setDiscountsItems(Set<DiscountItem> discountsItems) {
		this.discountsItems = discountsItems;
	}
	
	
	
}
