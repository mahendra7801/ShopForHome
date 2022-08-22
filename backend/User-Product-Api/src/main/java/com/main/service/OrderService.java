package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dao.OrderDao;
import com.main.entity.Orders;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	public List<Orders> getallOrders(){
		return orderDao.findAll();
	}
		
}
