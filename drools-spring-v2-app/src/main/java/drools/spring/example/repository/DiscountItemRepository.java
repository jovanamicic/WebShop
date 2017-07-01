package drools.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.DiscountItem;

public interface DiscountItemRepository extends JpaRepository<DiscountItem, Integer>{

}
