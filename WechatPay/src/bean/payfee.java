package bean;

public class payfee {
	
	private int person_id;
	private String city;
	private int fee_table_id;
	private String fee_type;
	private int fee_number;
	private boolean is_student;
	private String pay_status;
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getFee_table_id() {
		return fee_table_id;
	}
	public void setFee_table_id(int fee_table_id) {
		this.fee_table_id = fee_table_id;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public int getFee_number() {
		return fee_number;
	}
	public void setFee_number(int fee_number) {
		this.fee_number = fee_number;
	}
	public boolean isIs_student() {
		return is_student;
	}
	public void setIs_student(boolean is_student) {
		this.is_student = is_student;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	
	
	
}
