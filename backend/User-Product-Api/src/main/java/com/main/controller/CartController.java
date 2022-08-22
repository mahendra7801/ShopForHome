package com.main.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.dao.CartDao;
import com.main.dao.ProductDao;
import com.main.dao.UserDao;
import com.main.entity.Cart;
import com.main.entity.Product;
import com.main.entity.Wishlist;
import com.main.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private ProductDao productDao;
	
	@PostMapping("/addToCart/{userName}") 
	public Cart addToCart(@PathVariable String userName, @RequestBody Product product){
		Cart cart = new Cart();
		com.main.entity.User user = userDao.getById(userName);
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date today = new Date();
		
		Integer quantity_number = 1;
		System.out.println(product.getProductDescription());
		
		Double cartCost = (quantity_number * product.getProductDiscountedPrice());
		//System.out.println(formatter.format(today));
		System.out.println("in wishlist controller");
		
		System.out.println(user);
		cart.setUser(user);
		cart.setProduct(product);
		cart.setCreatedDate(today);
		cart.setQuantity_number(quantity_number);
		cart.setCartCost(cartCost);
	
		return cartService.addToCart(cart);
	}
	
	@GetMapping("/getCart/{userName}")
	public List<Cart> getCartList(@PathVariable String userName) {
		return cartService.getCartList(userName);
	}
	
	//delete a product from cart
	@DeleteMapping("/removeCartProduct/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCartproduct(@PathVariable int id){
		cartService.deleteCartProduct(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/removeAllProducts")
	public ResponseEntity<Map<String, Boolean>> deleteAllCartproduct(){
		cartService.deleteAllCartProduct();
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/increaseQuantity/{cartId}")
	public ResponseEntity<Cart> updateCart(@PathVariable Integer cartId, @RequestBody Cart cartDetails){
		Product product = cartDetails.getProduct();
		Cart cart = cartDao.findById(cartId)
				.orElseThrow(() -> new IllegalStateException(
				          "No Cart with \"" + cartId + "\" and \"" + cartDetails + "\""));
		Integer updatedQuantity = cartDetails.getQuantity_number() + 1;
		Double updatedprice = updatedQuantity * product.getProductDiscountedPrice();
		cart.setQuantity_number(updatedQuantity);
		cart.setCartCost(updatedprice);
				
		Cart updatedCart = cartDao.save(cart);
		return ResponseEntity.ok(updatedCart);
	}
	
	@PutMapping("/decreaseQuantity/{cartId}")
	public ResponseEntity<Cart> updateReduceCart(@PathVariable Integer cartId, @RequestBody Cart cartDetails){
		Product product = cartDetails.getProduct();
		Cart cart = cartDao.findById(cartId).orElseThrow(() -> new IllegalStateException(
		          "No Cart with \"" + cartId + "\" and \"" + cartDetails + "\""));
		Integer updatedQuantity = cartDetails.getQuantity_number() - 1;
		Double updatedprice = updatedQuantity * product.getProductDiscountedPrice();
		cart.setQuantity_number(updatedQuantity);
		cart.setCartCost(updatedprice);
		Cart updatedCart = cartDao.save(cart);
		return ResponseEntity.ok(updatedCart);
	}

}
