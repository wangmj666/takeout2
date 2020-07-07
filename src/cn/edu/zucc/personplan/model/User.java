package cn.edu.zucc.personplan.model;

import java.util.Date;

public class User {
	public static User currentLoginUser=null;
	private int user_id;
	private String user_name;
	private String user_sex;
	private String user_pwd;
	private String user_tel;
	private String user_mail;
	private String user_city;
	private Date user_regdate;
	private String vip;
	private Date vip_lastdate;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public Date getUser_regdate() {
		return user_regdate;
	}
	public void setUser_regdate(Date user_regdate) {
		this.user_regdate = user_regdate;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public Date getVip_lastdate() {
		return vip_lastdate;
	}
	public void setVip_lastdate(Date vip_lastdate) {
		this.vip_lastdate = vip_lastdate;
	}
	public static User getCurrentLoginUser() {
		return currentLoginUser;
	}
	public static void setCurrentLoginUser(User currentLoginUser) {
		User.currentLoginUser = currentLoginUser;
	}
	
}
