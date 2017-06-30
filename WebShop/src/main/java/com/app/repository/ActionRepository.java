package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Action;

public interface ActionRepository extends JpaRepository<Action, Integer>{

	Action findById(int id);
}
