package com.main.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
public class DiscountEligibility {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "userName")
	private User user;
	
	private Double DiscountAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getDiscountAmount() {
		return DiscountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		DiscountAmount = discountAmount;
	}

	public DiscountEligibility(Integer id, User user, Double discountAmount) {
		super();
		this.id = id;
		this.user = user;
		DiscountAmount = discountAmount;
	}

	public DiscountEligibility() {
		super();
	}

	@Override
	public String toString() {
		return "DiscountEligibility [id=" + id + ", user=" + user + ", DiscountAmount=" + DiscountAmount + "]";
	}

}
