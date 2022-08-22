package com.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer OrderId;
	
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
	@JoinColumn(name="userName")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;

	private Double GrandTotalPrice;
	
	private Integer Quantity;
	
	private Double Total;
	
	private Date orderDate;

	public Integer getOrderId() {
		return OrderId;
	}

	public void setOrderId(Integer orderId) {
		OrderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getGrandTotalPrice() {
		return GrandTotalPrice;
	}

	public void setGrandTotalPrice(Double grandTotalPrice) {
		GrandTotalPrice = grandTotalPrice;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Orders(Integer orderId, User user, Product product, Double grandTotalPrice, Integer quantity, Double total,
			Date orderDate) {
		super();
		OrderId = orderId;
		this.user = user;
		this.product = product;
		GrandTotalPrice = grandTotalPrice;
		Quantity = quantity;
		Total = total;
		this.orderDate = orderDate;
	}

	public Orders() {
		super();
	}

	@Override
	public String toString() {
		return "Orders [OrderId=" + OrderId + ", user=" + user + ", product=" + product + ", GrandTotalPrice="
				+ GrandTotalPrice + ", Quantity=" + Quantity + ", Total=" + Total + ", orderDate=" + orderDate + "]";
	}
	
}
