package cn.edu.zucc.personplan.model;

import java.util.Date;

public class Coupon_have {
	private int shop_id;
	private int coupon_id;
	private Double discount_amount;
	private int user_id;
	private Double coupon_num;
    private Date coupon_deadline;
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public Double getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(Double discount_amount) {
		this.discount_amount = discount_amount;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Double getCoupon_num() {
		return coupon_num;
	}
	public void setCoupon_num(Double coupon_num) {
		this.coupon_num = coupon_num;
	}
	public Date getCoupon_deadline() {
		return coupon_deadline;
	}
	public void setCoupon_deadline(Date coupon_deadline) {
		this.coupon_deadline = coupon_deadline;
	}
}
