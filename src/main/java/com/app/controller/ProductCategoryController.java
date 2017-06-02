package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ProductCategoruFullDTO;
import com.app.dto.ProductCategoryDTO;
import com.app.dto.ProductCategoryLiteDTO;
import com.app.model.ProductCategory;
import com.app.service.ProductCategoryService;

@RestController
@RequestMapping(value = "productCategories")
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategories(){
		List<ProductCategoryDTO> retVal = new ArrayList<ProductCategoryDTO>();
		List<ProductCategory> categories = productCategoryService.findAll();
		if (categories.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		for (ProductCategory pc : categories) {
			if (pc.getParentCategory() != null && pc.getParentCategory().getId() != 1){
				ProductCategoryDTO dto = new ProductCategoryDTO(pc.getId(),pc.getName());
				retVal.add(dto);
			}
		}
		
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value="/allPC", method = RequestMethod.GET)
	public ResponseEntity<List<ProductCategoruFullDTO>> getAllProductCategoriess(){
		List<ProductCategoruFullDTO> retVal = new ArrayList<ProductCategoruFullDTO>();
		List<ProductCategory> categories = productCategoryService.findAll();
		if (categories.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		for (ProductCategory pc : categories) {
			ProductCategoruFullDTO dto = new ProductCategoruFullDTO();
			dto.setId(pc.getId());
			dto.setName(pc.getName());
			dto.setMaxDiscount(pc.getMaxDiscount());
			if (pc.getParentCategory()!= null)
				dto.setCategory(new ProductCategoryDTO(pc.getParentCategory().getId(), pc.getParentCategory().getName()));
			retVal.add(dto);
		}
		
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> addNewPC(@RequestBody ProductCategoryLiteDTO dto){
		ProductCategory pc = new ProductCategory();
		ProductCategory parentCategory = productCategoryService.findById(1);
		pc.setName(dto.getName());
		pc.setMaxDiscount(Integer.parseInt(dto.getMaxDiscount()));
		pc.setParentCategory(parentCategory);
		productCategoryService.save(pc);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductCategoruFullDTO> getOne(@PathVariable int id){
		ProductCategory pc = productCategoryService.findById(id);
		if (pc == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ProductCategoruFullDTO retVal = new ProductCategoruFullDTO();
		retVal.setId(pc.getId());
		retVal.setName(pc.getName());
		retVal.setMaxDiscount(pc.getMaxDiscount());
		if (pc.getParentCategory() != null){
			ProductCategoryDTO category = new ProductCategoryDTO(pc.getParentCategory().getId(), pc.getParentCategory().getName());
			retVal.setCategory(category);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> updatePC(@PathVariable int id, @RequestBody ProductCategoryLiteDTO dto){
		ProductCategory pc = productCategoryService.findById(id);
		ProductCategory parentCategory = productCategoryService.findById(dto.getCategory());
		pc.setName(dto.getName());
		pc.setMaxDiscount(Integer.parseInt(dto.getMaxDiscount()));
		pc.setParentCategory(parentCategory);
		productCategoryService.save(pc);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
