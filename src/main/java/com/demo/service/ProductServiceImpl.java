package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ProductRepository;
import com.demo.entity.Product;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		log.info("Request in service class");
		return productRepository.getAllProductsFromDb();
	}

	@Override
	public boolean createProduct(Product product) {
		log.info("Request inside service for create product");
		return productRepository.createProduct(product);
	}

	@Override
	public boolean deletProductById(int id) {
			
		return productRepository.deletProductById(id);
	}

	@Override
	public boolean updateProduct(Product product , int id) {
		log.info("inside service");
		return productRepository.updateProduct(product,id);
	}

}
