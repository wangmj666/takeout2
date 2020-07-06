package cn.edu.zucc.personplan.model;

import java.util.Date;

public class Good_order {
	private int order_id;
	private int shop_id;
	private int user_id;
	private int rider_id;
	private Double ori_money;
	private Double fin_money;
	private int full_reduction_id;
	private int coupon_id;
	private Date order_time;
	private Date requie_time;
	private int addr_id;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRider_id() {
		return rider_id;
	}
	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}
	public Double getOri_money() {
		return ori_money;
	}
	public void setOri_money(Double ori_money) {
		this.ori_money = ori_money;
	}
	public Double getFin_money() {
		return fin_money;
	}
	public void setFin_money(Double fin_money) {
		this.fin_money = fin_money;
	}
	public int getFull_reduction_id() {
		return full_reduction_id;
	}
	public void setFull_reduction_id(int full_reduction_id) {
		this.full_reduction_id = full_reduction_id;
	}
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public Date getRequie_time() {
		return requie_time;
	}
	public void setRequie_time(Date requie_time) {
		this.requie_time = requie_time;
	}
	public int getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}
	

}
