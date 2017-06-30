package drools.spring.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "action")
public class Action implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "action_id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	
	@Column(name = "fromDate", unique = false, nullable = false)
	private Date fromDate;
	
	@Column(name = "toDate", unique = false, nullable = false)
	private Date toDate;
	
	@Column(name = "discount", unique = false, nullable = false)
	private int discount;

	@ManyToMany(cascade=CascadeType.ALL,  fetch = FetchType.LAZY)  
    @JoinTable(name="action_pcategory", joinColumns=@JoinColumn(name="action_id"), inverseJoinColumns=@JoinColumn(name="product_category_id"))  
	private Set<ProductCategory> categories  = new HashSet<>();
	
	public Action(){}

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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Set<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<ProductCategory> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", name=" + name + ", fromDate=" + fromDate + ", toDate=" + toDate + ", discount="
				+ discount + ", categories=" + categories + "]";
	}
	
}
