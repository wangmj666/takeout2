package cn.edu.zucc.personplan.model;

import java.util.Date;

public class Coupon_information {
	private int shop_id;
	private int coupon_id;
	private Double discount_amount;
	private int order_require_number;
	private Date startdate;
	private Date endate;
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
	public int getOrder_require_number() {
		return order_require_number;
	}
	public void setOrder_require_number(int order_require_number) {
		this.order_require_number = order_require_number;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEndate() {
		return endate;
	}
	public void setEndate(Date endate) {
		this.endate = endate;
	}
	

}
