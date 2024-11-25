package com.kh.java.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.java.model.PlayerVo;
import com.kh.java.model.ReleaseVO;

public class ReleaseRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	public static void ReleaseUpdate() throws SQLException {
		System.out.println("수정할 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 이름을 입력하세요");
		String name = sc.nextLine();
		System.out.println("수정할 포지션을 입력하세요");
		String possit = sc.nextLine();
		System.out.println("수정할 주급을 입력하세요");
		int weekSal = Integer.parseInt(sc.nextLine());
		ReleaseVO rvo = new ReleaseVO(no, name, possit, weekSal);
		boolean successFlag = ReleaseDAO.ReleaseUpdate(rvo);
		if (successFlag == true) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
	}
	public static void ReleaseDelete() throws SQLException {
		System.out.println("삭제할 선수의 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		ReleaseVO rvo = new ReleaseVO();
		rvo.setNo(no);
		boolean successFlag = ReleaseDAO.ReleaseDelete(rvo);
		if (successFlag == true) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
	}
	public static void ReleasePrint() throws SQLException {
		ArrayList<ReleaseVO> releaselist = new ArrayList<ReleaseVO>();
		releaselist = ReleaseDAO.ReleasePrint();
		printReleaseList(releaselist);
	}
	private static void printReleaseList(ArrayList<ReleaseVO> releaselist) {
		System.out.println("======================");
		for (ReleaseVO rvo : releaselist) {
			System.out.println(String.format("%s",rvo.toString()));
		}
		System.out.println("======================");
		
	}
}
