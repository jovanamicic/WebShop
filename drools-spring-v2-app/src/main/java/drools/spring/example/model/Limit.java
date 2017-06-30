package drools.spring.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "limit_range")
public class Limit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "limit_id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "minimum", unique = false, nullable = false)
	private double minimum;
	
	@Column(name = "maximum", unique = false, nullable = false)
	private double maximum;
	
	@Column(name = "discount", unique = false, nullable = false)
	private int discount;
	
	public Limit(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

	public double getMaximum() {
		return maximum;
	}

	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Limit [id=" + id + ", minimum=" + minimum + ", maximum=" + maximum + ", discount=" + discount + "]";
	}
	
}
