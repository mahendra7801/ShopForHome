package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.dao.UserDao;
import com.main.entity.DiscountEligibility;
import com.main.entity.User;
import com.main.service.DiscountEligibilityService;
import com.netflix.discovery.converters.Auto;

@RestController
public class DiscountEligibilityController {

	@Autowired
	private DiscountEligibilityService discountService;
	@Autowired
	private UserDao userDao;
	
	@PostMapping("/addDiscountEligibility/{userName}/{amount}")
	public DiscountEligibility addDiscountEligible(@PathVariable String userName,@PathVariable Double amount) {
		DiscountEligibility eligible = new DiscountEligibility();
		User user = userDao.getById(userName);
		eligible.setUser(user);
		eligible.setDiscountAmount(amount);
		return discountService.addtoEligibility(eligible);
	}
	
	@GetMapping("/getDiscountEligibility")
	public List<DiscountEligibility> getAllEligible(){
		return discountService.getAllEligible();
	}
	
	@DeleteMapping("/deleteUserCoupon/{id}")
	public void deleteUserCoupon(@PathVariable Integer id) {
		discountService.deleteUserCoupon(id);
	}
	
}
