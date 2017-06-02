package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Action;
import com.app.repository.ActionRepository;

@Service
public class ActionService {
	
	@Autowired
	private ActionRepository repo;

	public Action save(Action a) {
		return repo.save(a);
		
	}

	public List<Action> findAll() {
		return repo.findAll();
	}

	public Action findById(int id) {
		return repo.findById(id);
	}
	
	

}
