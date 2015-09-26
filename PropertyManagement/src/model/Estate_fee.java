package model;

import java.util.Date;

public class Estate_fee {
	private int estate_fee_id;
	private Owner_account owner_account;
	private float estate_fee;
	private Date estate_fee_date;
	public int getEstate_fee_id() {
		return estate_fee_id;
	}
	public void setEstate_fee_id(int estate_fee_id) {
		this.estate_fee_id = estate_fee_id;
	}
	public Owner_account getOwner_account() {
		return owner_account;
	}
	public void setOwner_account(Owner_account owner_account) {
		this.owner_account = owner_account;
	}
	public float getEstate_fee() {
		return estate_fee;
	}
	public void setEstate_fee(float estate_fee) {
		this.estate_fee = estate_fee;
	}
	public Date getEstate_fee_date() {
		return estate_fee_date;
	}
	public void setEstate_fee_date(Date estate_fee_date) {
		this.estate_fee_date = estate_fee_date;
	}
	
}
