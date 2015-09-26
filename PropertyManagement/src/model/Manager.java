package model;

import java.util.Date;

public class Manager {
	private int manager_id;
	private String manager_account;
	private String manager_password;
	private String manager_name;
	private String manager_phone;
	private String manager_tel;
	private String manager_email;
	private int manager_gender;
	private int manager_age;
	private Date entry_time;
	
	
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_account() {
		return manager_account;
	}
	public void setManager_account(String manager_account) {
		this.manager_account = manager_account;
	}
	public String getManager_password() {
		return manager_password;
	}
	public void setManager_password(String manager_password) {
		this.manager_password = manager_password;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getManager_phone() {
		return manager_phone;
	}
	public void setManager_phone(String manager_phone) {
		this.manager_phone = manager_phone;
	}
	public String getManager_tel() {
		return manager_tel;
	}
	public void setManager_tel(String manager_tel) {
		this.manager_tel = manager_tel;
	}
	public String getManager_email() {
		return manager_email;
	}
	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}
	public int getManager_gender() {
		return manager_gender;
	}
	public void setManager_gender(int manager_gender) {
		this.manager_gender = manager_gender;
	}
	public int getManager_age() {
		return manager_age;
	}
	public void setManager_age(int manager_age) {
		this.manager_age = manager_age;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

}
