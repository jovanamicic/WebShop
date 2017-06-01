package com.app.model;

import java.io.Serializable;
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
@Table(name = "ccategory")
public class CustomerCategory  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ccategory_id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	
	@ManyToMany(cascade=CascadeType.ALL,  fetch = FetchType.LAZY)  
    @JoinTable(name="category_limit", joinColumns=@JoinColumn(name="ccategory_id"), inverseJoinColumns=@JoinColumn(name="limit_id"))  
	private Set<Limit> limits  = new HashSet<>();
	
	public CustomerCategory(){}

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

	public Set<Limit> getLimits() {
		return limits;
	}

	public void setLimits(Set<Limit> limits) {
		this.limits = limits;
	}

}
