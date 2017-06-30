package drools.spring.example.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.dto.CustomerDTO;
import drools.spring.example.dto.UserDTO;
import drools.spring.example.dto.UserLiteDTO;
import drools.spring.example.model.Customer;
import drools.spring.example.model.CustomerCategory;
import drools.spring.example.model.User;
import drools.spring.example.service.CustomerCategoryService;
import drools.spring.example.service.CustomerService;
import drools.spring.example.service.UserService;


@RestController
@RequestMapping(value = "customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerCategoryService customerCategoryService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> register(@RequestBody UserDTO dto){
		if (dto == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Customer newC = new Customer();
		newC.setName(dto.getName());
		newC.setSurname(dto.getSurname());
		newC.setRole("customer");
		newC.setUsername(dto.getUsername());
		newC.setPassword(dto.getPassword());
		newC.setRegDate(new Date());
		newC.setPoints(0);
		newC.setAddress(dto.getAddress());
		CustomerCategory cc = customerCategoryService.findByName("basic");
		newC.setCcategory(cc);
		customerService.save(newC);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserLiteDTO> login(@RequestBody UserDTO dto){
		User user = userService.findByUsername(dto.getUsername());
		if (user == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (!user.getPassword().equals(dto.getPassword())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		UserLiteDTO retVal = new UserLiteDTO();
		retVal.setUsername(user.getUsername());
		retVal.setRole(user.getRole());
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String username){
		Customer c = customerService.findByUsername(username);
		if (c == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		CustomerDTO retVal = new CustomerDTO(c.getName(), c.getSurname(), c.getUsername(), c.getRegDate(), c.getAddress(), c.getPoints(), c.getCcategory().getName());
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
}
