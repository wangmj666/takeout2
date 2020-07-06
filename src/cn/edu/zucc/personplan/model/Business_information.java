package cn.edu.zucc.personplan.model;

public class Business_information {
	public static Business_information currentLoginshop=null;
	private String shop_id;
	private String shop_name;
	private String Merchants_star;
	private Double consumption_per_person;
	private Double total_sales;
	public static Business_information getCurrentLoginshop() {
		return currentLoginshop;
	}
	public static void setCurrentLoginshop(Business_information currentLoginshop) {
		Business_information.currentLoginshop = currentLoginshop;
	}
	
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getMerchants_star() {
		return Merchants_star;
	}
	public void setMerchants_star(String merchants_star) {
		Merchants_star = merchants_star;
	}
	public Double getConsumption_per_person() {
		return consumption_per_person;
	}
	public void setConsumption_per_person(Double consumption_per_person) {
		this.consumption_per_person = consumption_per_person;
	}
	public Double getTotal_sales() {
		return total_sales;
	}
	public void setTotal_sales(Double total_sales) {
		this.total_sales = total_sales;
	}
	
	
}
