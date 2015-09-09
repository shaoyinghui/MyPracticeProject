package model;

public class return1 {
	private String judge;
	private int remaining_number;
	private boolean duijiang;
	public return1(String judge,boolean duijiang){
		this.judge=judge;
		this.duijiang=duijiang;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	public int getRemaining_number() {
		return remaining_number;
	}
	public void setRemaining_number(int remaining_number) {
		this.remaining_number = remaining_number;
	}
	public boolean isDuijiang() {
		return duijiang;
	}
	public void setDuijiang(boolean duijiang) {
		this.duijiang = duijiang;
	}
	

}
