package cn.edu.zucc.personplan.model;

import java.util.Date;

public class Good_detail {
	private int good_id;
	private int good_fl;
	private int shop_id;
	private String good_name;
	private Double price;
	private Double favourable_price;
	public int getGood_id() {
		return good_id;
	}
	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}
	public int getGood_fl() {
		return good_fl;
	}
	public void setGood_fl(int good_fl) {
		this.good_fl = good_fl;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getFavourable_price() {
		return favourable_price;
	}
	public void setFavourable_price(Double favourable_price) {
		this.favourable_price = favourable_price;
	}

}
