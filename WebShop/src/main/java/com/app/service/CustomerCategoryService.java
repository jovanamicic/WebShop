package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CategoryDTO;
import com.app.model.CustomerCategory;
import com.app.model.ProductCategory;
import com.app.repository.CustomerCategoryRepository;

@Service
public class CustomerCategoryService {
	
	@Autowired
	private CustomerCategoryRepository repo;

	public CustomerCategory findByName(String name) {
		return repo.findByName(name);
	}

	public List<CustomerCategory> findAll() {
		return repo.findAll();
	}

	public CustomerCategory findById(int id) {
		return repo.findById(id);
	}

	public CustomerCategory save(CustomerCategory cs) {
		return repo.save(cs);
		
	}
	
	

}
