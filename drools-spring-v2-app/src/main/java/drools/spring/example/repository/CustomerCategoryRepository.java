package drools.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.CustomerCategory;

public interface CustomerCategoryRepository  extends JpaRepository<CustomerCategory, Integer>{
	
	CustomerCategory findByName(String name);

	CustomerCategory findById(int id);

}
