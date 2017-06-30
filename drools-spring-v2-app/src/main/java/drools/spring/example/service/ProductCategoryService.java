package drools.spring.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.spring.example.model.ProductCategory;
import drools.spring.example.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {
	
	@Autowired
	private ProductCategoryRepository repo;

	public List<ProductCategory> findAll() {
		return repo.findAll();
	}

	public ProductCategory findById(int key) {
		return repo.findById(key);
	}

	public ProductCategory save(ProductCategory pc) {
		return repo.save(pc);
		
	}

}
