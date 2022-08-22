package com.main.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.dao.ProductDao;
import com.main.entity.Product;
import com.main.helper.CSVHelper;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	public Product addNewProduct(Product product) {
		return productDao.save(product);

	}

	public void save(MultipartFile file) {
		try {
			List<Product> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
			productDao.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public List<Product> getAllTutorials() {
		return (List<Product>) productDao.findAll();
	}

	public List<Product> getProductsList(Product product) {
		return (List<Product>) productDao.findAll();
	}
}
