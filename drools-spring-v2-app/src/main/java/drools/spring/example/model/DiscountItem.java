package drools.spring.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "discount_item")
public class DiscountItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_item_id", unique = true, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "bill", referencedColumnName = "bill_id", nullable = true)
	private Bill bill;
	
	@ManyToOne
	@JoinColumn(name = "item", referencedColumnName = "item_id", nullable = true)
	private Item item;
	
	@Column(name = "discount", unique = false, nullable = false)
	private int discount;

	@Column(name = "basic", unique = false, nullable = false)
	private boolean isBasic;
	
	public DiscountItem(){}

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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public boolean isBasic() {
		return isBasic;
	}

	public void setBasic(boolean isBasic) {
		this.isBasic = isBasic;
	}

	@Override
	public String toString() {
		return "DiscountItem [id=" + id + ", bill=" + bill.getId() + ", item=" + item.getProduct().getName() + ", discount=" + discount + ", isBasic="
				+ isBasic + "]";
	}

}
