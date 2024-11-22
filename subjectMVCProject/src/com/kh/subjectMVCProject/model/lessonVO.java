package com.kh.subjectMVCProject.model;

public class lessonVO {
	private int no;
	private String abbre;
	private String name;
	
	public lessonVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getAbbre() {
		return abbre;
	}

	public void setAbbre(String abbre) {
		this.abbre = abbre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "lessonVO [no=" + no + ", abbre=" + abbre + ", name=" + name + "]";
	}
	
}
