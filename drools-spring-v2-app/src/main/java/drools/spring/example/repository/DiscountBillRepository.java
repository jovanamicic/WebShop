package drools.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.DiscountBill;

public interface DiscountBillRepository extends JpaRepository<DiscountBill, Integer>{

}
