package model;

import java.util.Date;

public class Vehicle {
	private int vehicle_id;
	private Owner_account owner_account;
	private String vehicle_plate;
	private Date vehicle_register_time;
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public Owner_account getOwner_account() {
		return owner_account;
	}
	public void setOwner_account(Owner_account owner_account) {
		this.owner_account = owner_account;
	}
	public String getVehicle_plate() {
		return vehicle_plate;
	}
	public void setVehicle_plate(String vehicle_plate) {
		this.vehicle_plate = vehicle_plate;
	}
	public Date getVehicle_register_time() {
		return vehicle_register_time;
	}
	public void setVehicle_register_time(Date vehicle_register_time) {
		this.vehicle_register_time = vehicle_register_time;
	}

}
