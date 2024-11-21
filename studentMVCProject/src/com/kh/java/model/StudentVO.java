package com.kh.java.model;

public class StudentVO {
	private int no;
	private String name;
    private int kor;
    private int eng;
    private int mat;
    private int total;
    private double ave;
    private int rank;
    
    //디생 
	public StudentVO() {}
	//전체 매생 하나 필요하면 따로 만든다 모든 전달 방식은 VO로 통일
	public StudentVO(int no, String name, int kor, int eng, int mat, int total, double ave, int rank) {
		super();
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.total = total;
		this.ave = ave;
		this.rank = rank;
	}
	//필요한부분 추가
	public StudentVO(int no, String name, int kor, int eng, int mat) {
		super();
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	
	
	public StudentVO(String name, int kor, int eng, int mat) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	//갯터 셋터
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
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getAve() {
		return ave;
	}
	public void setAve(double ave) {
		this.ave = ave;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	//String.format();
	@Override
	public String toString() {
		return "StudentVO [no=" + no + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", total="
				+ total + ", ave=" + ave + ", rank=" + rank + "]";
	}
}
