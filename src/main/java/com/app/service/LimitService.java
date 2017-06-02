package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Limit;
import com.app.repository.LimitRepository;

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
