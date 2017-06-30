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
@Table(name = "discount_bill")
public class DiscountBill implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_bill_id", unique = true, nullable = false)
	private int id;

	@ManyToOne
	@JoinColumn(name = "bill", referencedColumnName = "bill_id", nullable = true)
	private Bill bill;

	@Column(name = "discount", unique = false, nullable = false)
	private int discount;

	@Column(name = "basic", unique = false, nullable = false)
	private boolean isBasic;

	public DiscountBill() {
	}

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
		return "DiscountItem [id=" + id + ", bill=" + bill + ", discount=" + discount + ", isBasic=" + isBasic + "]";
	}
}
