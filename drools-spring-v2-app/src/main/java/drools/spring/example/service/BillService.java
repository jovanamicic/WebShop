package drools.spring.example.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.spring.example.model.Bill;
import drools.spring.example.model.Customer;
import drools.spring.example.model.DiscountBill;
import drools.spring.example.repository.BillRepository;
import drools.spring.example.repository.DiscountBillRepository;

@Service
public class BillService {
	
	@Autowired
	private BillRepository repo;
	
	@Autowired
	private DiscountBillRepository discountBillRepository;
	
	private final KieContainer kieContainer;
	   
    @Autowired
    public BillService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

	public Bill save(Bill b) {
		return repo.save(b);
	}

	public Bill getDiscount(Bill b) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(b);
		kieSession.getAgenda().getAgendaGroup("discountBill").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose(); 
		
		for (DiscountBill db : b.getDiscountsBill()) {
			discountBillRepository.save(db);
		}
		
		b = setTotalDiscount(b);
		
		return b;
	}

	public Bill setTotalDiscount(Bill bill){
		int discount = 0;
		for (DiscountBill bi : bill.getDiscountsBill()) {
			discount = discount + bi.getDiscount();
		}
		
		bill.setDiscount(discount);
		return bill;
	}

	public Bill getCoupons(Bill b) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(b);
		kieSession.getAgenda().getAgendaGroup("couponsBill").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose(); 
		return b;
	}

	public Bill findOne(int id) {
		return repo.findOne(id);
	}

	public List<Bill> findByState(String status) {
		return repo.findByState(status);
	}

	public List<Bill> findByCustomer(String c) {
		return repo.findByCustomerUsername(c);
	}

	public Bill getFinalPrice(Bill b) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(b);
		kieSession.getAgenda().getAgendaGroup("discountBillFinal").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose(); 
		return b;
	}

}
