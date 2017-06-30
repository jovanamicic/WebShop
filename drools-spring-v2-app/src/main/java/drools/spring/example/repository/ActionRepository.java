package drools.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.Action;


public interface ActionRepository extends JpaRepository<Action, Integer>{

	Action findById(int id);
}
