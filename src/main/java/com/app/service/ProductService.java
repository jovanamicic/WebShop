package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Product;
import com.app.model.ProductCategory;
import com.app.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;

	public List<Product> findById(String key) {
		return repo.findById(key);
	}

	public List<Product> findByName(String key) {
		return repo.findByName(key);
	}

	public List<Product> findAll() {
		return repo.findAll();
	}

	public List<Product> findByProductCategory(ProductCategory key) {
		return repo.findByProductCategory(key);
	}

	public Product findOneById(String id) {
		return repo.findById(id).get(0);
	}

}
