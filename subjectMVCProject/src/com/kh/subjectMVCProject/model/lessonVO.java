package com.kh.subjectMVCProject.model;

public class lessonVO {
	private int no; 		//PK SEQ
    private String abbre;   //과목 요약
    private String name; 	//과목이름
	
    
    //생성자 디생,매생 생성자 오버로딩
    
    public lessonVO(int no, String abbre, String name) {
		this.no = no;
		this.abbre = abbre;
		this.name = name;
	}

	public lessonVO(String abbre, String name) {
		this.abbre = abbre;
		this.name = name;
	}

	public lessonVO() {}

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
		return "[" + no + ", " + abbre + ", " + name + "]";
	}

    
}
