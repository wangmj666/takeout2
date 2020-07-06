package cn.edu.zucc.personplan.model;

import java.util.Date;

public class Rider {
	private String rider_id;
	private String rider_name;
	private Date joining_date;
	private String rider_level;
	
	public String getRider_id() {
		return rider_id;
	}
	public void setRider_id(String rider_id) {
		this.rider_id = rider_id;
	}
	public String getRider_name() {
		return rider_name;
	}
	public void setRider_name(String rider_name) {
		this.rider_name = rider_name;
	}
	public Date getJoining_date() {
		return joining_date;
	}
	public void setJoining_date(Date joining_date) {
		this.joining_date = joining_date;
	}
	public String getRider_level() {
		return rider_level;
	}
	public void setRider_level(String rider_level) {
		this.rider_level = rider_level;
	}

}
