package model;

import java.util.Date;

public class lottery_info {
private int lottery_number_id;
private Date time;
private String wechat_id;
private int gift_id;
private String person_name;
private String person_phone;
private String person_ip;
private boolean is_exchanged;
public int getLottery_number_id() {
	return lottery_number_id;
}
public void setLottery_number_id(int lottery_number_id) {
	this.lottery_number_id = lottery_number_id;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
public String getWechat_id() {
	return wechat_id;
}
public void setWechat_id(String wechat_id) {
	this.wechat_id = wechat_id;
}
public int getGift_id() {
	return gift_id;
}
public void setGift_id(int gift_id) {
	this.gift_id = gift_id;
}
public String getPerson_name() {
	return person_name;
}
public void setPerson_name(String person_name) {
	this.person_name = person_name;
}
public String getPerson_phone() {
	return person_phone;
}
public void setPerson_phone(String person_phone) {
	this.person_phone = person_phone;
}
public String getPerson_ip() {
	return person_ip;
}
public void setPerson_ip(String person_ip) {
	this.person_ip = person_ip;
}
public boolean isIs_exchanged() {
	return is_exchanged;
}
public void setIs_exchanged(boolean is_exchanged) {
	this.is_exchanged = is_exchanged;
}

}
