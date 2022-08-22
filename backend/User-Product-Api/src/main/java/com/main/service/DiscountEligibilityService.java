package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dao.DiscountEligibilityDao;
import com.main.entity.DiscountEligibility;

@Service
public class DiscountEligibilityService {
	
	@Autowired
	private DiscountEligibilityDao discountDao;

	public DiscountEligibility addtoEligibility(DiscountEligibility eligility) {
		return discountDao.save(eligility);
	}
	
	public List<DiscountEligibility> getAllEligible(){
		return discountDao.findAll();
	}
	public void deleteUserCoupon(Integer id) {
		discountDao.deleteById(id);
	}

}
