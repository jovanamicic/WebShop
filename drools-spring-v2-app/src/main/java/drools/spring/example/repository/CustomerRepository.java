package drools.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Customer findByUsername(String username);

}
