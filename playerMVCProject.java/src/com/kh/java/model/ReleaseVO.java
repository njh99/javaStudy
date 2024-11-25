package com.kh.java.model;

public class ReleaseVO {
	 private int no;
	 private String name;
	 private String possit;
	 private int weekSal;
	
	 public ReleaseVO(int no, String name, String possit, int weekSal) {
		super();
		this.no = no;
		this.name = name;
		this.possit = possit;
		this.weekSal = weekSal;
	}

	public ReleaseVO() {
		super();
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

	public int getWeekSal() {
		return weekSal;
	}

	public void setWeekSal(int weekSal) {
		this.weekSal = weekSal;
	}
	@Override
	public String toString() {
		return "[번호:" + no + ", 이름:" + name + ", 포지션:" + possit + ", 주급:" + weekSal + "]";
	}


}
