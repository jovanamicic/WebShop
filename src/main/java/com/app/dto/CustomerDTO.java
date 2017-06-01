package com.app.dto;

import java.util.Date;

public class CustomerDTO {
	
	private String name;
	private String surname;
	private String username;
	private Date regDate;
	private String address;
	private int points;
	private String ccategory;
	
	public CustomerDTO(){}

	public CustomerDTO(String name, String surname, String username, Date regDate, String address, int points,
			String ccategory) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.regDate = regDate;
		this.address = address;
		this.points = points;
		this.ccategory = ccategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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

	public String getCcategory() {
		return ccategory;
	}

	public void setCcategory(String ccategory) {
		this.ccategory = ccategory;
	}
}
