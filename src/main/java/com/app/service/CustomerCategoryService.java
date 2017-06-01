package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CustomerCategory;
import com.app.repository.CustomerCategoryRepository;

@Service
public class CustomerCategoryService {
	
	@Autowired
	private CustomerCategoryRepository repo;

	public CustomerCategory findByName(String name) {
		return repo.findByName(name);
	}
	
	

}
