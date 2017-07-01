package drools.spring.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.spring.example.model.Bill;

public interface BillRepository  extends JpaRepository<Bill, Integer>{

	List<Bill> findByCustomerUsernameAndDateAfter(String username, Date dateBefore);

}
