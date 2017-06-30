package drools.spring.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.spring.example.model.Customer;
import drools.spring.example.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;

	public Customer save(Customer newC) {
		return repo.save(newC);
	}

	public Customer findByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	

}
