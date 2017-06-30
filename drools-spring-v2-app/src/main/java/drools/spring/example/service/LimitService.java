package drools.spring.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.spring.example.model.Limit;
import drools.spring.example.repository.LimitRepository;

@Service
public class LimitService {
	
	@Autowired
	private LimitRepository repo;

	public Limit findById(int id) {
		return repo.findById(id);
	}

	public Limit save(Limit limit) {
		return repo.save(limit);
	}

}
