package com.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDTO;
import com.app.dto.UserLiteDTO;
import com.app.model.Customer;
import com.app.model.User;
import com.app.service.CustomerService;
import com.app.service.UserService;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
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

}
