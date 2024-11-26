package com.kh.java.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.java.model.FaVO;

public class FaRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	
	//수정
	public static void FaUpdate() throws SQLException {

		System.out.println("수정할 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 이름을 입력하세요");
		String name = sc.nextLine();
		System.out.println("수정할 포지션을 입력하세요");
		String possit = sc.nextLine();
		System.out.println("수정할 주급을 입력하세요");
		int weekSal = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 이적료을 입력하세요");
		int Fee = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 전화번호를 입력하세요");
		String phone = sc.nextLine();
		System.out.println("수정할 전(前)소속팀을 입력하세요");
		String team = sc.nextLine();
		
		
		FaVO fvo = new FaVO(no, name, possit, weekSal, Fee, phone, team);
		boolean successFlag = FaDAO.FaUpdate(fvo);
		if (successFlag == true) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
	}
	//삭제
	public static void FaDelete() throws SQLException {

		System.out.println("삭제할 선수의 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		FaVO fvo = new FaVO();
		fvo.setNo(no);
		boolean successFlag = FaDAO.FaDelete(fvo);
		if (successFlag == true) {
			System.out.println("삭제처리 성공");
		} else {
			System.out.println("삭제처리 실패");
		}
	}
	//출력
	public static void FaPrint() throws SQLException {
		
		ArrayList<FaVO> Falist = new ArrayList<FaVO>();
		Falist = FaDAO.FaPrint();
		printPlayerList(Falist);
			
		}
	
	//출력문
	public static void printPlayerList(ArrayList<FaVO> Falist) {
		System.out.println("======================");
		for (FaVO fvo : Falist) {
			System.out.println(String.format("%s",fvo.toString()));
		}
		System.out.println("======================");
	}
	
		
	
}
