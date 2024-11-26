package com.kh.java.model;

public class FaVO {
	private int no; 
    private String  name;
    private String possit;
    private int weeksal;
    private int fee;
    private String phone;
    private String team;
    
    
    
	public FaVO(int no, String name, String possit, int weeksal) {
		super();
		this.no = no;
		this.name = name;
		this.possit = possit;
		this.weeksal = weeksal;
	}

	public FaVO(int no, String name, String possit, int weeksal, int fee, String phone, String team) {
		super();
		this.no = no;
		this.name = name;
		this.possit = possit;
		this.weeksal = weeksal;
		this.fee = fee;
		this.phone = phone;
		this.team = team;
	}

	public FaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPossit() {
		return possit;
	}

	public void setPossit(String possit) {
		this.possit = possit;
	}

	public int getWeeksal() {
		return weeksal;
	}

	public void setWeeksal(int weeksal) {
		this.weeksal = weeksal;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "[" + no + ", " + name + ", " + possit + ", " + weeksal + ", " + fee
				+ ", " + phone + ", " + team + "]";
	}
    
    
}
