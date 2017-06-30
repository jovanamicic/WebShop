package drools.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{

	ProductCategory findById(int id);
}
