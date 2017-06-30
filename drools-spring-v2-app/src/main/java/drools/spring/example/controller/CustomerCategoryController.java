package drools.spring.example.controller;

import java.util.ArrayList;
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

import drools.spring.example.dto.CategoryDTO;
import drools.spring.example.model.CustomerCategory;
import drools.spring.example.model.Limit;
import drools.spring.example.service.CustomerCategoryService;
import drools.spring.example.service.LimitService;

@RestController
@RequestMapping(value = "customerCategories")
public class CustomerCategoryController {
	
	@Autowired
	private CustomerCategoryService customerCategoryService;
	
	@Autowired
	private LimitService limitService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> getAllCustomerCategories(){
		List<CategoryDTO> retVal = new ArrayList<CategoryDTO>();
		List<CustomerCategory> categories = customerCategoryService.findAll();
		if (categories.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		for (CustomerCategory c : categories) {
			CategoryDTO dto = new CategoryDTO(c.getId(), c.getName());
			retVal.add(dto);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<CustomerCategory> getOne(@PathVariable int id){
		CustomerCategory pc = customerCategoryService.findById(id);
		if (pc == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(pc, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> getOne(@RequestBody String json, @PathVariable int id){
		Set<Limit> limits = new HashSet<Limit>();
		if (!json.equals("[]")){
			json = json.replace("[", "");
			json = json.replace("]", "");
				json = json.replace("{", "");
				json = json.replace("}", "");
				String lines[] = json.split(",");
				List<String> values = new ArrayList<String>();
				for (String string : lines) {
					values.add(string.split(":")[1]);
				}
				for(int i = 0; i < values.size(); i = i + 4 ){
					Limit limit = limitService.findById(Integer.parseInt(values.get(i).replaceAll("\"", "")));
					if (limit == null){
						limit = new Limit();
						limit.setMinimum(Double.parseDouble(values.get(i+1).replaceAll("\"", "")));
						limit.setMaximum(Double.parseDouble(values.get(i+2).replaceAll("\"", "")));
						limit.setDiscount(Integer.parseInt(values.get(i+3).replaceAll("\"", "")));
						limit = limitService.save(limit);
					}
					limits.add(limit);
				}
		}
		CustomerCategory cs = customerCategoryService.findById(id);
		cs.setLimits(limits);
		customerCategoryService.save(cs);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
