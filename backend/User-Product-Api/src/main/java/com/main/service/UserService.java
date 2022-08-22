package com.main.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.dao.CartDao;
import com.main.dao.DiscountEligibilityDao;
import com.main.dao.OrderDao;
import com.main.dao.RoleDao;
import com.main.dao.UserDao;
import com.main.dao.WishlistDao;
import com.main.entity.Cart;
import com.main.entity.DiscountEligibility;
import com.main.entity.Orders;
import com.main.entity.Role;
import com.main.entity.User;
import com.main.entity.Wishlist;

@Service
public class UserService<User_Role> {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
    private WishlistDao wishlistDao;
    @Autowired
    private CartDao cartDao;
	//private User user;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private DiscountEligibilityDao discountDao;
	

	public void initRoleAndUser() {

		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin role");
		roleDao.save(adminRole);

		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Default role for newly created record");
		roleDao.save(userRole);

		User adminUser = new User();
		adminUser.setUserName("admin123");
		adminUser.setUserPassword(getEncodedPassword("admin@pass"));
		adminUser.setUserFirstName("admin");
		adminUser.setUserLastName("admin");
		adminUser.setEmailId("admin@mail.com");
		adminUser.setPhoneNumber("9845678254");
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
		userDao.save(adminUser);

		User user = new User();
		user.setUserName("raj123");
		user.setUserPassword(getEncodedPassword("raj@123"));
		user.setUserFirstName("raj");
		user.setUserLastName("sharma");
		user.setEmailId("raj@mail.com");
		user.setPhoneNumber("9845678254");

		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);
		user.setRole(userRoles);
		userDao.save(user);
	}

	public User registerNewUser(User user) {
		Role role = roleDao.findById("User").get();
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		user.setRole(userRoles);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));

		user.setUserFirstName(user.getUserFirstName());
		user.setUserLastName(user.getUserLastName());
		user.setEmailId(user.getEmailId());
		user.setUserName(user.getUserName());
		user.setPhoneNumber(user.getPhoneNumber());
		return userDao.save(user);
	}

	public User registerNewAdmin(User user) {
		Role role = roleDao.findById("Admin").get();
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		user.setRole(userRoles);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));

		user.setUserFirstName(user.getUserFirstName());
		user.setUserLastName(user.getUserLastName());
		user.setEmailId(user.getEmailId());
		user.setUserName(user.getUserName());
		user.setPhoneNumber(user.getPhoneNumber());
		return userDao.save(user);
	}
	
	public List<User> getUsersList(User user) {
		return (List<User>) userDao.findAll();
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public void  deleteUser(String userName) {
		User user = userDao.getById(userName);
		ArrayList<Wishlist> wishlist = (ArrayList<Wishlist>) wishlistDao.findAll();
		ArrayList<Cart> cart = (ArrayList<Cart>) cartDao.findAll();
		ArrayList<Orders> order = (ArrayList<Orders>) orderDao.findAll();
		ArrayList<DiscountEligibility> discounts = (ArrayList<DiscountEligibility>) discountDao.findAll();
		
		for(int i=0;i<wishlist.size();i++) {
			if(wishlist.get(i).getUser().equals(user)) {
				Wishlist list = wishlist.get(i);
				Integer id = list.getId();
				wishlistDao.deleteById(id);
			}
		}
		for(int i=0;i<cart.size();i++) {
			if(cart.get(i).getUser().equals(user)) {
				Cart list = cart.get(i);
				Integer id = list.getCartId();
				cartDao.deleteById(id);
			}
		}
		for(int i=0;i<order.size();i++) {
			if(order.get(i).getUser().equals(user)) {
				Orders ord = order.get(i);
				Integer id = ord.getOrderId();
				orderDao.deleteById(id);
			}
		}
		//
		for(int i=0;i<discounts.size();i++) {
			if(discounts.get(i).getUser().equals(user)) {
				DiscountEligibility ord = discounts.get(i);
				Integer id = ord.getId();
				discountDao.deleteById(id);
			}
		}
		
		userDao.deleteById(userName);
	}
}
