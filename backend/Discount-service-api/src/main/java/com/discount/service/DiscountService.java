package com.discount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.discount.dao.DiscountDao;
import com.discount.entity.Discount;

@Service
public class DiscountService {
	
	@Autowired
	private DiscountDao discountDao;
	
	public Discount addCoupon(@RequestBody Discount discount){
		return discountDao.save(discount);
		
	}
	
	public List<Discount> getCoupons(){
		return discountDao.findAll();
	}
	
	public void deleteCoupon(Integer DiscountId) {
		discountDao.deleteById(DiscountId);
		
	}

}
