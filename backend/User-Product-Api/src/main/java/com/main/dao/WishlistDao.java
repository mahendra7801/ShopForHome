package com.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Wishlist;

@Repository
public interface WishlistDao extends JpaRepository<Wishlist, Integer> {

}
