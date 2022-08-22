package com.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.main.dao.CartDao;
import com.main.dao.UserDao;
import com.main.entity.Cart;
import com.main.entity.User;
import com.main.entity.Wishlist;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;
	@Autowired
	private UserDao userDao;

	public Cart addToCart(Cart cart) {
		return cartDao.save(cart);
	}

	public List<Cart> getCartList(String userName) {
		List<Cart> returnList = new ArrayList<Cart>();
		List<Cart> cartlist = cartDao.findAll();
		System.out.println(cartlist);
		for (int i = 0; i < cartlist.size(); i++) {
			if (cartlist.get(i).getUser().getUserName().contentEquals(userName)) {
				returnList.add(cartlist.get(i));
			} else {
				continue;
			}
		}
		return returnList;
	}
	
	//delete a product in cart
		public ResponseEntity<Map<String, Boolean>> deleteCartProduct(int id) {
			cartDao.deleteById(id);

			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		public ResponseEntity<Map<String, Boolean>> deleteAllCartProduct(){
			cartDao.deleteAll();
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
}
