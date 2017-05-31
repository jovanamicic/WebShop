package com.app.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@DiscriminatorValue("MANAGER")
public class Manager extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Manager(){
		super();
	}

}
