package drools.spring.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.spring.example.model.User;
import drools.spring.example.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}

}
