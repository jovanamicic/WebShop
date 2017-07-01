package drools.spring.example.controller;

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

import drools.spring.example.dto.PriceSearchDTO;
import drools.spring.example.dto.ProductCategoryDTO;
import drools.spring.example.dto.ProductDTO;
import drools.spring.example.model.Product;
import drools.spring.example.model.ProductCategory;
import drools.spring.example.service.ProductCategoryService;
import drools.spring.example.service.ProductService;

@RestController
@RequestMapping(value = "products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> findProducts(@PathVariable String key) {
		List<Product> products = productService.findAll();
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ProductDTO> retVal = new ArrayList<ProductDTO>();
		for (Product pid : products) {
			if (pid.getName().toLowerCase().contains(key.toLowerCase())
					|| pid.getId().toLowerCase().contains(key.toLowerCase())) {
				if (!pid.isDeleted() && pid.getStock() > 0) {
					ProductCategoryDTO category = new ProductCategoryDTO(pid.getProductCategory().getId(),
							pid.getProductCategory().getName());
					ProductDTO dto = new ProductDTO(pid.getId(), pid.getName(), category, pid.getStock(),
							pid.getPrice());
					retVal.add(dto);
				}
			}
		}
		if (retVal.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/category/{key}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> findProductByCategory(@PathVariable String key) {
		ProductCategory pc = productCategoryService.findById(Integer.parseInt(key));
		List<Product> products = productService.findByProductCategory(pc);
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ProductDTO> retVal = new ArrayList<ProductDTO>();
		for (Product pid : products) {
			if (!pid.isDeleted() && pid.getStock() > 0) {
				ProductCategoryDTO category = new ProductCategoryDTO(pid.getProductCategory().getId(),
						pid.getProductCategory().getName());
				ProductDTO dto = new ProductDTO(pid.getId(), pid.getName(), category, pid.getStock(), pid.getPrice());
				retVal.add(dto);
			}
		}
		if (retVal.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/price", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<ProductDTO>> findProductByCategory(@RequestBody PriceSearchDTO pdto) {
		double from = Double.parseDouble(pdto.getMinPrice());
		double to = Double.parseDouble(pdto.getMaxPrice());
		List<Product> products = productService.findAll();
		List<ProductDTO> retVal = new ArrayList<ProductDTO>();
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		for (Product pid : products) {
			if (pid.getPrice() >= from && pid.getPrice() <= to) {
				ProductCategoryDTO category = new ProductCategoryDTO(pid.getProductCategory().getId(),
						pid.getProductCategory().getName());
				ProductDTO dto = new ProductDTO(pid.getId(), pid.getName(), category, pid.getStock(), pid.getPrice());
				retVal.add(dto);
			}
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductDTO> getOne(@PathVariable String id) {
		Product p = productService.findOneById(id);
		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ProductCategoryDTO category = new ProductCategoryDTO(p.getProductCategory().getId(),
				p.getProductCategory().getName());
		ProductDTO retVal = new ProductDTO(p.getId(), p.getName(), category, p.getStock(), p.getPrice());
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/moreThan/{id}", method = RequestMethod.GET)
	public Product moreThan(@PathVariable String id) {
		Product product = productService.findOneById(id);
		Product p = productService.findMoreThan(product);
		return p;
	}

	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> getProductsForRefill() {
		List<Product> products = productService.findProductsForRefill();
		List<ProductDTO> retVal = new ArrayList<ProductDTO>();
		for (Product p : products) {
			if (p.isRefill()) {
				ProductDTO dto = new ProductDTO();
				dto.setId(p.getId());
				dto.setName(p.getName());
				dto.setPrice(p.getPrice());
				dto.setStock(p.getStock());
				dto.setMinimumInStock(p.getMinimumInStock());
				dto.setCategory(new ProductCategoryDTO(p.getProductCategory().getId(), p.getProductCategory().getName()));
				retVal.add(dto);
			}
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/refill", method = RequestMethod.GET)
	public ResponseEntity<Void> refill() {
		List<Product> products = productService.findByRefill(true);
		for (Product product : products) {
			product.setStock(product.getStock() +  product.getMinimumInStock()); //refilling with the number of minimum in stock
			product.setRefill(false);
			productService.save(product);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
