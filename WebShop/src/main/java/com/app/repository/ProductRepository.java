package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Product;
import com.app.model.ProductCategory;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findById(String key);
	List<Product> findByName(String key);
	List<Product> findByProductCategory(ProductCategory productCategory);

}
