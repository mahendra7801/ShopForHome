package com.main.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.dao.UserDao;
import com.main.dao.WishlistDao;
import com.main.entity.Product;
import com.main.entity.Wishlist;
import com.main.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private UserDao userDao;
	@PostMapping("/addToWishlist/{userName}") 
	public Wishlist addToWishList(@PathVariable String userName, @RequestBody Product product){
		Wishlist wishlist = new Wishlist();
		com.main.entity.User user = userDao.getById(userName);
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date today = new Date();
		//System.out.println(formatter.format(today));
		System.out.println("in wishlist controller");
		System.out.println(user);
		wishlist.setUser(user);
		wishlist.setProduct(product);
		wishlist.setCreatedDate(today);
		return wishlistService.addToWishlist(wishlist);
	}
	
	@GetMapping("/getWishlist/{userName}")
	public List<Wishlist> getWishList(@PathVariable String userName) {
		return wishlistService.getWishList(userName);
	}
	
	@DeleteMapping("/removeWishlistProduct/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteWishlistproduct(@PathVariable int id){
		wishlistService.deleteWishlistProduct(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
