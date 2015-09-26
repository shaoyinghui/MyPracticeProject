package model;
import java.util.Date;
public class Owner_account {
	private int account_id;
	private House house;
    private String account_name;
	private String account_password;
	private Date check_in_time;
    public int getAccount_id() {
		return account_id;
	}
    public Owner_account(){}
    public Owner_account(String on,String op){
    	this.account_name = on;
    	this.account_password = op;
    }
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public House getHouse() {
		house.getHouse_area();
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_password() {
		return account_password;
	}
	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}
	public Date getCheck_in_time() {
		return check_in_time;
	}
	public void setCheck_in_time(Date check_in_time) {
		this.check_in_time = check_in_time;
	}

}
