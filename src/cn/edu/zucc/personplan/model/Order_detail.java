package cn.edu.zucc.personplan.model;

public class Order_detail {
	private int order_id;
	private int good_id;
	private int order_num;
	private Double order_price;
	private Double discount_pice;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getGood_id() {
		return good_id;
	}
	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public Double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}
	public Double getDiscount_pice() {
		return discount_pice;
	}
	public void setDiscount_pice(Double discount_pice) {
		this.discount_pice = discount_pice;
	}

}
