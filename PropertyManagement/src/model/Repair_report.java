package model;

import java.util.Date;

public class Repair_report {
	private int repair_report_id;
	private House house;
	private String repair_description;
	private String repair_object;
	private String object_position;
	private Date apply_time;
	private String repairer_name;
	private String repairer_phone;
	private String repair_result;
	private Date repair_time;
	private Float repair_fee;
	
	
	public Repair_report(){}
	public Repair_report(String rd,House h,String ro,String op,Date ad){
		this.repair_description = rd;
		this.house = h;
		this.repair_object = ro;
		this.object_position = op;
		this.apply_time = ad;
		this.repair_time = null;
	}
	public Float getRepair_fee() {
		return repair_fee;
	}
	public void setRepair_fee(Float repair_fee) {
		this.repair_fee = repair_fee;
	}
	
	public int getRepair_report_id() {
		return repair_report_id;
	}
	public void setRepair_report_id(int repair_report_id) {
		this.repair_report_id = repair_report_id;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	
	public String getRepair_description() {
		return repair_description;
	}
	public void setRepair_description(String repair_description) {
		this.repair_description = repair_description;
	}
	public String getRepair_object() {
		return repair_object;
	}
	public void setRepair_object(String repair_object) {
		this.repair_object = repair_object;
	}
	public String getObject_position() {
		return object_position;
	}
	public void setObject_position(String object_position) {
		this.object_position = object_position;
	}
	public Date getApply_time() {
		return apply_time;
	}
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	public String getRepairer_name() {
		return repairer_name;
	}
	public void setRepairer_name(String repairer_name) {
		this.repairer_name = repairer_name;
	}
	public String getRepairer_phone() {
		return repairer_phone;
	}
	public void setRepairer_phone(String repairer_phone) {
		this.repairer_phone = repairer_phone;
	}
	public String getRepair_result() {
		return repair_result;
	}
	public void setRepair_result(String repair_result) {
		this.repair_result = repair_result;
	}
	public Date getRepair_time() {
		return repair_time;
	}
	public void setRepair_time(Date repair_time) {
		this.repair_time = repair_time;
	}

}
