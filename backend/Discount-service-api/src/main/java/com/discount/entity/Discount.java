package com.discount.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer DiscountId;

	private String discountCoupon;

	private Double discountAmount;

	public Integer getDiscountId() {
		return DiscountId;
	}

	public void setDiscountId(Integer discountId) {
		DiscountId = discountId;
	}

	public String getDiscountCoupon() {
		return discountCoupon;
	}

	public void setDiscountCoupon(String discountCoupon) {
		this.discountCoupon = discountCoupon;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Discount(Integer discountId, String discountCoupon, Double discountAmount) {
		super();
		DiscountId = discountId;
		this.discountCoupon = discountCoupon;
		this.discountAmount = discountAmount;
	}

	public Discount() {
		super();
	}

	@Override
	public String toString() {
		return "Discount [DiscountId=" + DiscountId + ", discountCoupon=" + discountCoupon + ", discountAmount="
				+ discountAmount + "]";
	}
	
}
