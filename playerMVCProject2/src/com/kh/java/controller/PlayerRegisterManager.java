package com.kh.java.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;


import com.kh.java.model.PlayerVo;

public class PlayerRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	//삽입
	public static void PlayerInsert() throws SQLException, FileNotFoundException, IOException {

		int no = 0;
		System.out.println("이름을 입력하세요");
		String name = sc.nextLine();
		System.out.println("포지션을 입력하세요 FW/MF/DF/GK");
		String possit = sc.nextLine();
		System.out.println("주급을 입력하세요");
		int weekSal = Integer.parseInt(sc.nextLine());
		System.out.println("경기 수를 입력하세요");
		int game = Integer.parseInt(sc.nextLine());
		PlayerVo playerVO = new PlayerVo(no, name, possit, weekSal, game);
		boolean successFlag = PlayerDAO.PlayerInsert(playerVO);
		if (successFlag == true) {
			System.out.println("삽입 성공");
		} else {
			System.out.println("삽입 실패");
		}
	}
	//수정
	public static void PlayerUpdate() throws SQLException, FileNotFoundException, IOException {

		System.out.println("수정할 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 이름을 입력하세요");
		String name = sc.nextLine();
		System.out.println("수정할 포지션을 입력하세요");
		String possit = sc.nextLine();
		System.out.println("수정할 주급을 입력하세요");
		int weekSal = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 경기 수를 입력하세요");
		int game = Integer.parseInt(sc.nextLine());
		PlayerVo pvo = new PlayerVo(no, name, possit, weekSal, game);
		boolean successFlag = PlayerDAO.PlayerUpdate(pvo);
		if (successFlag == true) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
	}
	//삭제
	public static void PlayerDelete() throws SQLException, FileNotFoundException, IOException {

		System.out.println("삭제할 선수의 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		PlayerVo pvo = new PlayerVo();
		pvo.setNo(no);
		boolean successFlag = PlayerDAO.PlayerDelete(pvo);
		if (successFlag == true) {
			System.out.println("삭제처리 성공");
		} else {
			System.out.println("삭제처리 실패");
		}
	}
	//출력
	public static void PlayerPrint() throws SQLException, FileNotFoundException, IOException {
		
		ArrayList<PlayerVo> playerlist = new ArrayList<PlayerVo>();
		playerlist = PlayerDAO.PlayerPrint();
		printPlayerList(playerlist);
			
		}
	//FUNC
	public static void PlayerFunc() throws SQLException, FileNotFoundException, IOException {
		
		System.out.println("선수의 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		PlayerVo pvo = new PlayerVo();
		pvo.setNo(no);
		boolean successFlag = PlayerDAO.PlayerFunc(pvo);
		if (successFlag == true) {
			System.out.println("함수처리 성공");
		} else {
			System.out.println("함수처리 실패");
		}
	}	
	//출력문
	public static void printPlayerList(ArrayList<PlayerVo> playerlist) {
		System.out.println("======================");
		for (PlayerVo pvo : playerlist) {
			System.out.println(String.format("%s",pvo.toString()));
		}
		System.out.println("======================");
	}
	//PROC
	public static void PlayerProc() throws SQLException, FileNotFoundException, IOException {
		
		System.out.println("경기 수를 추가할 선수의 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		System.out.println("추가할 경기 수를 입력하세요");
		int game = Integer.parseInt(sc.nextLine());
		PlayerVo pvo = new PlayerVo();
		pvo.setNo(no);
		pvo.setGame(game);
		boolean successFlag = PlayerDAO.PlayerProc(pvo);
		if (successFlag == true) {
			System.out.println("프로시저처리 성공");
		} else {
			System.out.println("프로시저처리 실패");
		}
		
	}
}
