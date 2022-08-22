package com.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.DiscountEligibility;

@Repository
public interface DiscountEligibilityDao extends JpaRepository<DiscountEligibility, Integer> {

}
