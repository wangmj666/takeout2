package cn.edu.zucc.personplan.model;

import java.util.Date;

public class Rider_money {
	private int order_id;
	private int rider_id;
	private Date order_time;
	private String user_rider_comment;
	private Double rider_income;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getRider_id() {
		return rider_id;
	}
	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public String getUser_rider_comment() {
		return user_rider_comment;
	}
	public void setUser_rider_comment(String user_rider_comment) {
		this.user_rider_comment = user_rider_comment;
	}
	public Double getRider_income() {
		return rider_income;
	}
	public void setRider_income(Double rider_income) {
		this.rider_income = rider_income;
	}

}
