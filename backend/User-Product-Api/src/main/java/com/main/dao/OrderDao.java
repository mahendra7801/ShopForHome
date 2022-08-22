package com.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Orders;
@Repository
public interface OrderDao extends JpaRepository<Orders, Integer>{
	

}
