package model;

import java.util.Date;

public class Unit_price {
	private int unit_price_id;
	private float unit_price;
	private Date date;
	public int getUnit_price_id() {
		return unit_price_id;
	}
	public void setUnit_price_id(int unit_price_id) {
		this.unit_price_id = unit_price_id;
	}
	public float getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(float unit_price) {
		this.unit_price = unit_price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
