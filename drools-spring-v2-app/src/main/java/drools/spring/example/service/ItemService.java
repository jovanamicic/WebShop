package drools.spring.example.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.spring.example.model.Action;
import drools.spring.example.model.Item;
import drools.spring.example.repository.ActionRepository;
import drools.spring.example.repository.BillRepository;
import drools.spring.example.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository repo;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private ActionRepository actionRepository;
	
	private final KieContainer kieContainer;
	   
    @Autowired
    public ItemService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

	public Item save(Item i) {
		return repo.save(i);
	}

	public Item getDiscount(Item item) {
		List<Action> actionsAll = actionRepository.findAll();
		List<Action> actions = new ArrayList<Action>();
		for (Action action : actionsAll) {
			if (action.getFromDate().before(new Date()) && action.getToDate().after(new Date())){
				actions.add(action);
			}
		}

		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(item);
		kieSession.getAgenda().getAgendaGroup("discountItem").setFocus();
		kieSession.setGlobal("inLast15DaysBills", billRepository.findByCustomerUsernameAndDateAfter(item.getBill().getCustomer().getUsername(), dateBefore(15)));
		kieSession.setGlobal("inLast30DaysBills", billRepository.findByCustomerUsernameAndDateAfter(item.getBill().getCustomer().getUsername(), dateBefore(30)));
		kieSession.setGlobal("actions", actions);
		kieSession.fireAllRules();
		kieSession.dispose(); 
		return item;
	}
	
	public Date dateBefore(int days){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -days); 
		Date date = cal.getTime();
		return date;
	}

}
