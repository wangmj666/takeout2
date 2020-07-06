package cn.edu.zucc.personplan.model;

import java.util.Date;

public class Jidan_coupon {
	
	private int shop_id;
	private int coupon_id;
	private int user_id;
	private Double discount_amount;
	private int ordered;
	private int order_require_number;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Double getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(Double discount_amount) {
		this.discount_amount = discount_amount;
	}
	public int getOrdered() {
		return ordered;
	}
	public void setOrdered(int ordered) {
		this.ordered = ordered;
	}
	public int getOrder_require_number() {
		return order_require_number;
	}
	public void setOrder_require_number(int order_require_number) {
		this.order_require_number = order_require_number;
	}

}
