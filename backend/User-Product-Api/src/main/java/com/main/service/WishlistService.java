package com.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.main.dao.UserDao;
import com.main.dao.WishlistDao;
import com.main.entity.Product;
import com.main.entity.Wishlist;

@Service
public class WishlistService {

	@Autowired
	private WishlistDao wishlistDao;
	@Autowired
	private UserDao userDao;

	//add product to wishlist
	public Wishlist addToWishlist(Wishlist wishlist) {
		return wishlistDao.save(wishlist);
	}

	//retrieve the wishlist
	public List<Wishlist> getWishList(String userName) {

		List<Wishlist> returnList = new ArrayList<Wishlist>();
		List<Wishlist> wishlist = wishlistDao.findAll();
		System.out.println(wishlist);
		for (int i = 0; i < wishlist.size(); i++) {
			if (wishlist.get(i).getUser().getUserName().contentEquals(userName)) {
				returnList.add(wishlist.get(i));
			} else {
				continue;
			}
		}
		return returnList;
	}

	//delete a product in wishlist
	public ResponseEntity<Map<String, Boolean>> deleteWishlistProduct(int id) {
		wishlistDao.deleteById(id);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
