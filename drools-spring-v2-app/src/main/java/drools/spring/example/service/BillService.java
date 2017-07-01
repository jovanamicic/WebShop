package drools.spring.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.spring.example.model.Bill;
import drools.spring.example.repository.BillRepository;

@Service
public class BillService {
	
	@Autowired
	private BillRepository repo;

	public Bill save(Bill b) {
		return repo.save(b);
	}

}
