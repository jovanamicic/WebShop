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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.dto.BillDTO;
import drools.spring.example.dto.DiscountBillDTO;
import drools.spring.example.dto.DiscountItemDTO;
import drools.spring.example.dto.ItemDTO;
import drools.spring.example.model.Bill;
import drools.spring.example.model.Customer;
import drools.spring.example.model.DiscountBill;
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
	public ResponseEntity<BillDTO> addNew(@RequestBody List<ItemDTO> dto, HttpSession session) {
		// logged customer
		Customer customer = customerService.findByUsername((String) session.getAttribute("user"));
		Bill b = new Bill();
		b.setDate(new Date());
		b.setCustomer(customer);
		b.setState("created");
		b.setDiscountsBill(new HashSet<DiscountBill>());

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

		BillDTO retVal = new BillDTO();
		retVal.setId(b.getId());
		retVal.setFinalPrice(finalPrice);
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/points/{ind}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> usePoints(@RequestBody BillDTO dto, @PathVariable String ind) {

		Bill bill = billService.findOne(dto.getId());
		Customer c = customerService.findByUsername(bill.getCustomer().getUsername());
		int customerPoints = c.getPoints();

		if (ind.equals("true") && customerPoints != 0) {
			if (bill.getFinalPrice() > customerPoints) {
				bill.setFinalPrice(bill.getFinalPrice() - customerPoints);
				bill.setCouponsSpent(customerPoints);
				c.setPoints(0);

			} else {
				bill.setCouponsSpent(bill.getFinalPrice());
				bill.setFinalPrice(0.0);
				c.setPoints((int) (customerPoints - bill.getFinalPrice()));
			}
		} else {
			bill.setCouponsSpent(0.0);
		}

		bill.setState("ordered");
		billService.save(bill);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<BillDTO> usePoints(@PathVariable int id) {
		Bill b = billService.findOne(id);

		BillDTO bill = new BillDTO();
		bill.setOriginalPrice(b.getOriginalTotalPrice());
		bill.setFinalPrice(b.getFinalPrice());
		bill.setDiscount(b.getDiscount());
		bill.setGainedPoints((int) b.getCouponsGained());
		bill.setSpentPoints((int) b.getCouponsSpent());

		ArrayList<DiscountItemDTO> discountItems = new ArrayList<DiscountItemDTO>();
		for (Item i : b.getItems()) {
			for (DiscountItem di : i.getDiscountsItems()) {
				DiscountItemDTO dto = new DiscountItemDTO(di.getItem().getProduct().getName(), di.getDiscount());
				discountItems.add(dto);
			}
		}
		
		bill.setDiscountItems(discountItems);

		ArrayList<DiscountBillDTO> discountBill = new ArrayList<DiscountBillDTO>();
		for (DiscountBill di : b.getDiscountsBill()) {
			DiscountBillDTO dto = new DiscountBillDTO(di.getDiscount());
			discountBill.add(dto);
		}
		
		bill.setDiscountBill(discountBill);

		return new ResponseEntity<>(bill, HttpStatus.OK);
	}
	
	@RequestMapping(value="/status/{status}", method = RequestMethod.GET)
	public ResponseEntity<List<BillDTO>> getStatusBills(@PathVariable String status) {
		List<Bill> bills = billService.findByState(status); 
		List<BillDTO> retVal = new ArrayList<BillDTO>();
		for (Bill b : bills) {
			BillDTO dto = new BillDTO();
			dto.setId(b.getId());
			dto.setOriginalPrice(b.getOriginalTotalPrice());
			dto.setFinalPrice(b.getFinalPrice());
			dto.setDiscount(b.getDiscount());
			retVal.add(dto);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value="/cancel/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> getStatusBills(@PathVariable int id) {
		Bill b = billService.findOne(id);
		b.setState("cancelled");
		billService.save(b);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
