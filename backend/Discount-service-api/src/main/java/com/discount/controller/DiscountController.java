package com.discount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.discount.entity.Discount;
import com.discount.service.DiscountService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DiscountController {
	@Autowired
	private DiscountService discountService;

	@PostMapping("/addCoupon")
	public Discount addCoupon(@RequestBody Discount discount){
		return discountService.addCoupon(discount);
		}
	@GetMapping("/getCoupons")
	public List<Discount> getCoupons(){
		return discountService.getCoupons();
	}
	@DeleteMapping("/deleteCoupon/{DiscountId}")
	public void deleteCoupon(@PathVariable Integer DiscountId) {
		discountService.deleteCoupon(DiscountId);
	}
	
}
