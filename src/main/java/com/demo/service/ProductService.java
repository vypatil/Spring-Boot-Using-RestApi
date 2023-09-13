package com.demo.service;

import java.util.List;

import com.demo.entity.Product;

public interface ProductService {

	public List<Product> getAllProducts();

	public boolean createProduct(Product product);

	public boolean deletProductById(int id);

	public boolean updateProduct(Product product , int id);
}
