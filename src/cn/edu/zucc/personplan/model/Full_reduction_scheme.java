package cn.edu.zucc.personplan.model;

public class Full_reduction_scheme {
	
	private int shop_id;
	private int full_reduction_id;
	private Double full_reduction_fee;
	private Double coupon;
	private String dadd;
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getFull_reduction_id() {
		return full_reduction_id;
	}
	public void setFull_reduction_id(int full_reduction_id) {
		this.full_reduction_id = full_reduction_id;
	}
	public Double getFull_reduction_fee() {
		return full_reduction_fee;
	}
	public void setFull_reduction_fee(Double full_reduction_fee) {
		this.full_reduction_fee = full_reduction_fee;
	}
	public Double getCoupon() {
		return coupon;
	}
	public void setCoupon(Double coupon) {
		this.coupon = coupon;
	}
	public String getDadd() {
		return dadd;
	}
	public void setDadd(String dadd) {
		this.dadd = dadd;
	}
}
