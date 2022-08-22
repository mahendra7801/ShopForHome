package com.main.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.dao.DiscountEligibilityDao;
import com.main.dao.OrderDao;
import com.main.dao.ProductDao;
import com.main.dao.UserDao;
import com.main.entity.DiscountEligibility;
import com.main.entity.Orders;
import com.main.entity.Product;
import com.main.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DiscountEligibilityDao discountEligibilityDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrderDao orderDao;

	@GetMapping("/getOrders")
	public List<Orders> getallOrders() {

		return orderService.getallOrders();
	}

	@PostMapping("/addToOrders/{userName}/{grandTotal}/{cartCost}/{Quantity}")
	public Orders addToOrders(@PathVariable String userName, @PathVariable Double grandTotal,@PathVariable Double cartCost,@PathVariable Integer Quantity,
			@RequestBody Product productDetails) {
		Orders order = new Orders();
		//get user
		com.main.entity.User user = userDao.getById(userName);

		//get the users having discount and  the amount 
		Double TotalPrice = 0.0;
		ArrayList<DiscountEligibility> discountEligibility = (ArrayList<DiscountEligibility>) discountEligibilityDao.findAll();
		System.out.println(discountEligibility.size());
		if (discountEligibility.size() > 1 || discountEligibility.size() == 1) {
			for (int i = 0; i < discountEligibility.size(); i++) {
				if (discountEligibility.get(i).getUser().getUserName() == userName) {
					TotalPrice = grandTotal - discountEligibility.get(i).getDiscountAmount();
				} else {
					TotalPrice = grandTotal;
				}
			}
		} else {
			TotalPrice = grandTotal;
		}
		
		//get today date 
		Date today = new Date();
		order.setOrderDate(today);
		order.setGrandTotalPrice(TotalPrice);
		order.setUser(user);
		order.setProduct(productDetails);
		order.setQuantity(Quantity);
		order.setTotal(cartCost);
		return orderDao.save(order);
	}

}
