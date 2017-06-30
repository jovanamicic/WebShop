package drools.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.User;

public interface UserRepository  extends JpaRepository<User, Integer>{

	User findByUsername(String username);
}
