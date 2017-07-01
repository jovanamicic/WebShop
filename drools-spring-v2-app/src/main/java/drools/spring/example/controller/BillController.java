package drools.spring.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.dto.ItemDTO;
import drools.spring.example.model.Bill;
import drools.spring.example.model.Customer;
import drools.spring.example.model.DiscountItem;
import drools.spring.example.model.Item;
import drools.spring.example.service.BillService;
import drools.spring.example.service.CustomerService;
import drools.spring.example.service.ItemService;
import drools.spring.example.service.ProductService;

@RestController
@RequestMapping(value = "bills")
public class BillController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BillService billService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> addNew(@RequestBody List<ItemDTO> dto, HttpSession session){
		//logged customer
		Customer customer = customerService.findByUsername((String) session.getAttribute("user"));
		Bill b = new Bill();
		b.setDate(new Date());
		b.setCustomer(customer);
		b.setState("created");
		
		b = billService.save(b);
		
		ArrayList<Item> items = new ArrayList<Item>();
		int num = 0;
		double originalPriceBill = 0; 
		double finalPriceSum = 0;
		for (ItemDTO i : dto) {
			Item item = new Item();
			item.setBill(b);
			item.setNum(++num);
			item.setProduct(productService.findOneById(i.getId()));
			item.setUnitPrice(i.getPrice());
			item.setQuantity(i.getQuantity());
			item.setOriginalPrice(i.getPrice() * i.getQuantity());
			item = itemService.getDiscount(item);
			double finalPrice = item.getOriginalPrice() * (1 - ((double) item.getDiscount() / 100));
			item.setFinalPrice(finalPrice); 
			item = itemService.save(item);
			itemService.saveAllDiscountItems(item);
			items.add(item);
			originalPriceBill = originalPriceBill + (item.getOriginalPrice());
			finalPriceSum = finalPriceSum + item.getFinalPrice();
		}
		
		Set<Item> itemsSet = new HashSet<Item>(items);
		b.setItems(itemsSet);
		
		b.setOriginalTotalPrice(originalPriceBill);
		b = billService.getDiscount(b);
		double finalPrice = finalPriceSum * (1 - ((double) b.getDiscount() / 100));
		b.setFinalPrice(finalPrice);
		b = billService.getCoupons(b);
		billService.save(b);
		
		customer.setPoints(customer.getPoints() + (int) b.getCouponsGained());
		customerService.save(customer);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
