package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ProductCategoryDTO;
import com.app.dto.ProductDTO;
import com.app.model.Product;
import com.app.model.ProductCategory;
import com.app.service.ProductService;

@RestController
@RequestMapping(value = "products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/{key}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> findProducts(@PathVariable String key){
		List<Product> products = productService.findAll();
		if (products.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ProductDTO> retVal = new ArrayList<ProductDTO>();
		for (Product pid : products) {
			if (pid.getName().toLowerCase().contains(key.toLowerCase()) || pid.getId().toLowerCase().contains(key.toLowerCase())){
				if (!pid.isDeleted() && pid.getStock() > 0){
					ProductCategoryDTO category = new ProductCategoryDTO(pid.getProductCategory().getId(), pid.getProductCategory().getName());
					ProductDTO dto = new ProductDTO(pid.getId(), pid.getName(), category , pid.getStock(), pid.getPrice());
					retVal.add(dto);
				}
			}
		}
		if (retVal.isEmpty()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

}
