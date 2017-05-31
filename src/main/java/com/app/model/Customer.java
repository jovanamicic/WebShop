package com.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@DiscriminatorValue("CUSTOMER")
public class Customer extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "address", unique = false, nullable = true)
	private String address;
	
	@Column(name = "points", unique = false, nullable = true)
	private int points;
	
	public Customer(){
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}


}
