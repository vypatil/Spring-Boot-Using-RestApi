package com.demo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.entity.Product;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Product> getAllProductsFromDb() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Product> productList = null;

		try {
			tx = session.beginTransaction();
			Query<Product> query = session.createQuery("from Product");
			productList = query.list();
			tx.commit();
		} catch (HibernateException e) {

			if (tx != null) {
				tx.rollback();
			}
			log.error("Exception" + e.getMessage());
		} finally {

			session.close();
			return productList;
		}

	}

	@Override
	public boolean createProduct(Product product) {

		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(product);
			tx.commit();
			log.info("Product saved to DB successfully", product);
			return true;
		} catch (Exception e) {

			log.error("Error occurred while storing the Product to DB : {}", e.getMessage());
			return false;
		} finally {
			session.close();

		}
	}

	@Override
	public boolean deletProductById(int id) {

		Session session = sessionFactory.openSession();
		boolean res = false;
		try {
			Transaction tx = session.beginTransaction();
			Product product = session.get(Product.class, id);

			if (product != null) {
				session.delete(product);
				tx.commit();
				log.info("Product deleted!!");
				res = true;
			}
		} catch (HibernateException e) {
			log.error("Product not deleted!!");
			session.close();
		}
		return res;
	}

	@Override
	public boolean updateProduct(Product product, int id) {
		log.info("inside repository");

		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Product obj = session.get(Product.class, id);
			obj.setId(product.getId());
			obj.setName(product.getName());
			obj.setPrice(product.getPrice());
			session.save(obj);
			tx.commit();
			session.close();
			log.info("Product updated successfully");
			return true;
		} catch (HibernateException e) {

			log.error("Unable to  update Product " + e.getMessage());
			return false;
		}

	}

}
