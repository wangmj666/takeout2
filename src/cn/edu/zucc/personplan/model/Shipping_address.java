package cn.edu.zucc.personplan.model;

public class Shipping_address {
	private int addr_id;
	private int user_id;
	private String province;
	private String city;
	private String district;
	private String addr;
	private String linkman;
	private String pick_tel;
	public int getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPick_tel() {
		return pick_tel;
	}
	public void setPick_tel(String pick_tel) {
		this.pick_tel = pick_tel;
	}
	
	

}
