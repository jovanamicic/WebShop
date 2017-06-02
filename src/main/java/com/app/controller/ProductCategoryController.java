package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ProductCategoryDTO;
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

}
