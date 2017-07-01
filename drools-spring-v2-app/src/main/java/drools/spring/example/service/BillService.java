package drools.spring.example.service;

import java.util.List;
import java.util.Set;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.spring.example.model.Bill;
import drools.spring.example.model.DiscountBill;
import drools.spring.example.model.Limit;
import drools.spring.example.repository.BillRepository;

@Service
public class BillService {
	
	@Autowired
	private BillRepository repo;
	
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
}
