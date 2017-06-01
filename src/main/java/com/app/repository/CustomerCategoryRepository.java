package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.CustomerCategory;

public interface CustomerCategoryRepository  extends JpaRepository<CustomerCategory, Integer>{
	
	CustomerCategory findByName(String name);

}
