package com.demo.dao;

import java.util.List;

import com.demo.entity.Product;

public interface ProductRepository {

	public List<Product> getAllProductsFromDb();

	public boolean createProduct(Product product);

	public boolean deletProductById(int id);

	public boolean updateProduct(Product product , int id);

}
