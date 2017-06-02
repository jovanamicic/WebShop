package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Limit;

public interface LimitRepository extends JpaRepository<Limit, Integer>{
	
	Limit findById(int id);

}
