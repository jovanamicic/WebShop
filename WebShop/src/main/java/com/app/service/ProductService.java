package com.app.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Product;
import com.app.model.ProductCategory;
import com.app.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	private final KieContainer kieContainer;
	   
    @Autowired
    public ProductService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

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

	public Product findMoreThan(Product p) {
		KieSession session = kieContainer.newKieSession();
		
		session.insert(p);
		session.fireAllRules();
		session.dispose();
		return p;
	}

}
