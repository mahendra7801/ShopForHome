package com.main.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "userName")
	private User user;
	
	private Date createdDate;
	
	private Double cartCost;
	
	private Integer quantity_number;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getCartCost() {
		return cartCost;
	}

	public void setCartCost(Double cartCost) {
		this.cartCost = cartCost;
	}

	public Integer getQuantity_number() {
		return quantity_number;
	}

	public void setQuantity_number(Integer quantity_number) {
		this.quantity_number = quantity_number;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart(Integer cartId, User user, Date createdDate, Double cartCost, Integer quantity_number,
			Product product) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.createdDate = createdDate;
		this.cartCost = cartCost;
		this.quantity_number = quantity_number;
		this.product = product;
	}

	public Cart() {
		super();
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", user=" + user + ", createdDate=" + createdDate + ", cartCost=" + cartCost
				+ ", quantity_number=" + quantity_number + ", product=" + product + "]";
	}

}
