package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{

}
