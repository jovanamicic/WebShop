package drools.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.Limit;

public interface LimitRepository extends JpaRepository<Limit, Integer>{
	
	Limit findById(int id);

}
