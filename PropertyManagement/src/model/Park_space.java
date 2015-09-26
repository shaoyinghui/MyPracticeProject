package model;

public class Park_space {
	private int park_space_id;
	private Owner_account owner_account;
	private String park_position;
	public int getPark_space_id() {
		return park_space_id;
	}
	public void setPark_space_id(int park_space_id) {
		this.park_space_id = park_space_id;
	}
	public Owner_account getOwner_account() {
		return owner_account;
	}
	public void setOwner_account(Owner_account owner_account) {
		this.owner_account = owner_account;
	}
	public String getPark_position() {
		return park_position;
	}
	public void setPark_position(String park_position) {
		this.park_position = park_position;
	}

}
