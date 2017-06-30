package drools.spring.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.dto.ActionDTO;
import drools.spring.example.dto.ProductCategoryDTO;
import drools.spring.example.model.Action;
import drools.spring.example.model.ProductCategory;
import drools.spring.example.service.ActionService;
import drools.spring.example.service.ProductCategoryService;

@RestController
@RequestMapping(value = "actions")
public class ActionsController {
	
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> addNew(@RequestBody ActionDTO dto) throws ParseException{
		Action a = new Action();
		a.setDiscount(dto.getDiscount());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		a.setFromDate(sdf.parse(dto.getFrom()));
		a.setToDate(sdf.parse(dto.getTo()));
		a.setName(dto.getName());
		Set<ProductCategory> categories = new HashSet<ProductCategory>();
		for (ProductCategoryDTO pcdto : dto.getCategories()) {
			ProductCategory pc = productCategoryService.findById(pcdto.getId());
			categories.add(pc);
		}
		a.setCategories(categories);
		actionService.save(a);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<Action>> getAll(){
		List<Action> actions = actionService.findAll();
		if (actions.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(actions, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Action> getOne(@PathVariable int id){
		Action retVal = actionService.findById(id);
		if (retVal == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> update(@RequestBody ActionDTO dto, @PathVariable int id) throws ParseException{
		Action a = actionService.findById(id);
		a.setDiscount(dto.getDiscount());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		a.setFromDate(sdf.parse(dto.getFrom()));
		a.setToDate(sdf.parse(dto.getTo()));
		a.setName(dto.getName());
		Set<ProductCategory> categories = new HashSet<ProductCategory>();
		for (ProductCategoryDTO pcdto : dto.getCategories()) {
			ProductCategory pc = productCategoryService.findById(pcdto.getId());
			categories.add(pc);
		}
		a.setCategories(categories);
		actionService.save(a);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
