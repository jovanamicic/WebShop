package drools.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.Item;

public interface ItemRepository  extends JpaRepository<Item, Integer>{

}
