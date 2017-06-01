package com.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "product_id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	
	@Column(name = "price", unique = false, nullable = false)
	private double price;
	
	@Column(name = "stock", unique = false, nullable = false)
	private int stock;
	
	@Column(name = "creationDate", unique = false, nullable = false)
	private Date creationDate;
	
	@Column(name = "refill", unique = false, nullable = false)
	private boolean refill;
	
	@Column(name = "deleted", unique = false, nullable = false)
	private boolean deleted;
	
	@Column(name = "minimumInStock", unique = false, nullable = false)
	private int minimumInStock;

	@ManyToOne
	@JoinColumn(name="productCategory", referencedColumnName = "product_category_id", nullable = true)
	private ProductCategory productCategory;
	
	public Product(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isRefill() {
		return refill;
	}

	public void setRefill(boolean refill) {
		this.refill = refill;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getMinimumInStock() {
		return minimumInStock;
	}

	public void setMinimumInStock(int minimumInStock) {
		this.minimumInStock = minimumInStock;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}
