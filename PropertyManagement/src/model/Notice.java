package model;

import java.util.Date;

public class Notice {
	private int notice_id;
	private String notice_title;
	private String notice_content;
	private Date notice_publish_time;
	private Notice_type notice_type;
	
	
	
	
	public Notice_type getNotice_type() {
		return notice_type;
	}
	public void setNotice_type(Notice_type notice_type) {
		this.notice_type = notice_type;
	}
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public Date getNotice_publish_time() {
		return notice_publish_time;
	}
	public void setNotice_publish_time(Date notice_publish_time) {
		this.notice_publish_time = notice_publish_time;
	}
	
	
}
