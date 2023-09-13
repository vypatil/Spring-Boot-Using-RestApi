package com.demo.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Product;
import com.demo.service.ProductService;
import static com.demo.util.Constants.*;  // for creating constant String 
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1")
@Log4j2
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
		log.info("Request comming to fetch products");
		List<Product> prodList = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(prodList,HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<String> createProduct(@RequestBody Product product){
		
		log.info("Request received to store the product : " , product);
															// constant string return if product save.
		String res = (productService.createProduct(product))? SUCCESSFULLY_STORED: ERROR_WHILE_STORING;
		
		return new ResponseEntity<String>(res , HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/products/{id}")
	public boolean deletProductById(@PathVariable("id") int id) {
		
		return productService.deletProductById(id);
		
	}
	
	
	@PutMapping("/products/{id}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product , @PathVariable int id) {
		
		log.info("Request recived for update Product" , product);
		String res = (productService.updateProduct(product,id))? SUCCESSFULLY_UPDATE : ERROR_WHILE_UPDATING;
		return new ResponseEntity<>(res, HttpStatus.OK);
		
	}
	
}
