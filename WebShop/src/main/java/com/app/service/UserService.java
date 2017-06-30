package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}

}
