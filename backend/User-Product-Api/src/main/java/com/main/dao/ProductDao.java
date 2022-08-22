package com.main.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Product;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer>{

}
