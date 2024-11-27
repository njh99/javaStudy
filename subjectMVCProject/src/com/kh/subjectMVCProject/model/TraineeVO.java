package com.kh.subjectMVCProject.model;

import java.sql.Date;

public class TraineeVO {
	// T.NO, T.SECTION,t.registdate,S.num,S.NAME as sname,L.ABBRE,L.NAME AS lname
	private int no; // pk seq
	private String s_num; // student fk 번호
	private String abbre; // lesson fk 과목요약
	private String section; // 전공,부전공
	private Date registdate; // 수강신청일
	// student join
	private String s_name;
	// lesson join
	private String l_name;

	// 생성자
	public TraineeVO() {
	}

	public TraineeVO(int no, String s_num, String abbre, String section, Date registdate) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
		this.registdate = registdate;
	}

	public TraineeVO(String s_num, String abbre, String section) {
		super();
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
	}

	//조인을 위한 생성자
	public TraineeVO(int no, String s_num, String abbre, String section, Date registdate, String s_name,
			String l_name) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
		this.registdate = registdate;
		this.s_name = s_name;
		this.l_name = l_name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public String getAbbre() {
		return abbre;
	}

	public void setAbbre(String abbre) {
		this.abbre = abbre;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Date getRegistdate() {
		return registdate;
	}

	public void setRegistdate(Date registdate) {
		this.registdate = registdate;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	@Override
	public String toString() {
		return "[" + no + ", " + s_num + ", " + abbre + ", " + section + ", " + registdate + "]";
	}
	
	public String toAllString() {
	return "TraineeVO [no=" + no + ", s_num=" + s_num + ", abbre=" + abbre + ", section=" + section
			+ ", registdate=" + registdate + ", s_name=" + s_name + ", l_name=" + l_name + "]";
	}
	

}
