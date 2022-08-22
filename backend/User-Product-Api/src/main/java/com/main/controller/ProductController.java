package com.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.main.dao.CartDao;
import com.main.dao.OrderDao;
import com.main.dao.ProductDao;
import com.main.dao.WishlistDao;
import com.main.entity.Cart;
import com.main.entity.Orders;
import com.main.entity.Product;
import com.main.entity.Wishlist;
import com.main.helper.CSVHelper;
import com.main.mail.EmailSenderService;
import com.main.message.ResponseMessage;
import com.main.service.ProductService;

@CrossOrigin("http://localhost:4200")
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private EmailSenderService emailService;
	String mailSubject = "Alert!!!! Product Stock less than 10";

	@Autowired
	private WishlistDao wishlistDao;
	@Autowired
	private CartDao cartDao;
	// private User user;
	@Autowired
	private OrderDao orderDao;

	@PostMapping("/addNewProduct")
	public Product addNewProduct(@RequestBody Product product) {
		return productService.addNewProduct(product);

	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				productService.save(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@GetMapping("/getProducts")
	public List<Product> getProductList(Product product) {
		return productService.getProductsList(product);
	}

	@GetMapping("/getProduct/{productId}")
	public ResponseEntity<Product> getEmployeeById(@PathVariable Integer productId) {
		Product product = productDao.findById(productId).orElseThrow(() -> new IllegalStateException(
		          "No Product with \"" + productId + "\""));
		return ResponseEntity.ok(product);
	}
	// update product rest api

	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateEmployee(@PathVariable Integer productId,
			@RequestBody Product productdetails) {
		Product product = productDao.findById(productId).orElseThrow(() -> new IllegalStateException(
		          "No Product with \"" + productId + "\""));
		product.setProductName(productdetails.getProductName());
		product.setProductCategory(productdetails.getProductCategory());
		product.setProductDescription(productdetails.getProductDescription());
		product.setProductStock(productdetails.getProductStock());
		product.setProductDiscountedPrice(productdetails.getProductDiscountedPrice());
		product.setProductActualPrice(productdetails.getProductActualPrice());
		Product updatedProduct = productDao.save(product);
		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Integer productId) {
		Product product = productDao.findById(productId).orElseThrow(() -> new IllegalStateException(
				          "No Product with \"" + productId + "\""));
		ArrayList<Wishlist> wishlist = (ArrayList<Wishlist>) wishlistDao.findAll();
		ArrayList<Cart> cart = (ArrayList<Cart>) cartDao.findAll();
		ArrayList<Orders> order = (ArrayList<Orders>) orderDao.findAll();
		for(int i=0;i<wishlist.size();i++) {
			if(wishlist.get(i).getProduct().equals(product)) {
				Wishlist list = wishlist.get(i);
				Integer id = list.getId();
				wishlistDao.deleteById(id);
			}
		}
		for(int i=0;i<cart.size();i++) {
			if(cart.get(i).getProduct().equals(product)) {
				Cart list = cart.get(i);
				Integer id = list.getCartId();
				cartDao.deleteById(id);
			}
		}
		for(int i=0;i<order.size();i++) {
			if(order.get(i).getProduct().equals(product)) {
				Orders list = order.get(i);
				Integer id = list.getOrderId();
				orderDao.deleteById(id);
			}
		}
		
		productDao.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {
		ArrayList<Product> products = (ArrayList<Product>) productDao.findAll();
		for (int i = 0; i < products.size(); i++) {

			if (products.get(i).getProductStock() < 10) {
				String mailBody = "Hello Team,\n\nThis is the mail regarding the stock of the product,the product Stock of ID = "
						+ products.get(i).getProductId() + " and product named --" + products.get(i).getProductName()
						+ "--is reduced please update it as soon as possible! \n\n\nThankyou!";

				emailService.sendSimpleEmail("ramyasree02092000@gmail.com", mailBody, mailSubject);
			} else {
				continue;
			}
		}
	}

}
