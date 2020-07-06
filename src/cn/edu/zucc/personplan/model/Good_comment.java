package cn.edu.zucc.personplan.model;

import java.util.Date;

public class Good_comment {
	private int good_id;
	private int user_id;
	private int shop_id;
	private String good_comment;
	private String good_star;
	private Date good_comment_date;
	private String good_picture;
	public int getGood_id() {
		return good_id;
	}
	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String getGood_comment() {
		return good_comment;
	}
	public void setGood_comment(String good_comment) {
		this.good_comment = good_comment;
	}
	public String getGood_star() {
		return good_star;
	}
	public void setGood_star(String good_star) {
		this.good_star = good_star;
	}
	public Date getGood_comment_date() {
		return good_comment_date;
	}
	public void setGood_comment_date(Date good_comment_date) {
		this.good_comment_date = good_comment_date;
	}
	public String getGood_picture() {
		return good_picture;
	}
	public void setGood_picture(String good_picture) {
		this.good_picture = good_picture;
	}

}
