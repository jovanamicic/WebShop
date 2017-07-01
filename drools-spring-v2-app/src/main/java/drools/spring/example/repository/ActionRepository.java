package drools.spring.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.Action;


public interface ActionRepository extends JpaRepository<Action, Integer>{

	Action findById(int id);
}
