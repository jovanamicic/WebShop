package drools.spring.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.Product;
import drools.spring.example.model.ProductCategory;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findById(String key);
	List<Product> findByName(String key);
	List<Product> findByProductCategory(ProductCategory productCategory);

}
