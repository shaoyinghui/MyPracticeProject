package model;

public class Owner {
private int owner_id;
private String owner_name;
private String owner_phone;
private int owner_gender;
private String owner_email;
private int owner_age;

private Owner_account owner_account;

public int getOwner_id() {
	return owner_id;
}
public void setOwner_id(int owner_id) {
	this.owner_id = owner_id;
}

public String getOwner_name() {
	return owner_name;
}
public void setOwner_name(String owner_name) {
	this.owner_name = owner_name;
}
public String getOwner_phone() {
	return owner_phone;
}
public void setOwner_phone(String owner_phone) {
	this.owner_phone = owner_phone;
}
public int getOwner_gender() {
	return owner_gender;
}
public void setOwner_gender(int owner_gender) {
	this.owner_gender = owner_gender;
}
public int getOwner_age() {
	return owner_age;
}
public void setOwner_age(int owner_age) {
	this.owner_age = owner_age;
}
public String getOwner_email() {
	return owner_email;
}
public void setOwner_email(String owner_email) {
	this.owner_email = owner_email;
}

public Owner_account getOwner_account() {
	return owner_account;
}
public void setOwner_account(Owner_account owner_account) {
	this.owner_account = owner_account;
}

}
