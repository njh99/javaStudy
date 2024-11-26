package com.kh.java;

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

import com.kh.java.controller.FaRegisterManager;
import com.kh.java.controller.PlayerRegisterManager;
import com.kh.java.controller.ReleaseRegisterManager;
import com.kh.java.view.FA_PLAYER_CHOICE;
import com.kh.java.view.MENU_CHOICE;
import com.kh.java.view.MenuViewer;
import com.kh.java.view.PLAYER_CHOICE;
import com.kh.java.view.RELEASE_PLAYER_CHOICE;




public class PlayerMVCProject {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		boolean exitFlag = false;

		while (!exitFlag) {
			MenuViewer.mainMenuView();
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case MENU_CHOICE.PLAYER:
				playerMenu();
				break;
			case MENU_CHOICE.RELEASEPLAYER:
				ReleaseplayerMenu();
				break;
			case MENU_CHOICE.FA:
				FaplayerMenu();
				break;
			case MENU_CHOICE.EXIT:
				exitFlag = true;
				break;
			default:
				System.out.println("잘못된 번호입니다.");
				return;
			}
		}
		System.out.println("종료 되었습니다.");

	}
	private static void playerMenu() throws FileNotFoundException, SQLException, IOException {
		int num; 
		boolean exitFlag = false;
		PlayerRegisterManager prm = new PlayerRegisterManager();
		while(!exitFlag) {
			MenuViewer.PlayerMenuView();
			num = Integer.parseInt(sc.nextLine()); 
			switch (num) {
			case PLAYER_CHOICE.INSERT:
				PlayerRegisterManager.PlayerInsert();
				break;
			case PLAYER_CHOICE.UPDATE:
				PlayerRegisterManager.PlayerUpdate();
				break;
			case PLAYER_CHOICE.DELETE:
				PlayerRegisterManager.PlayerDelete();
				break;
			case PLAYER_CHOICE.PRINT:
				PlayerRegisterManager.PlayerPrint();
				break;
			case PLAYER_CHOICE.PLAYER_CHECK:
				PlayerRegisterManager.PlayerFunc();
				break;
			case PLAYER_CHOICE.ADD_GAME:
				PlayerRegisterManager.PlayerProc();
				break;
			case PLAYER_CHOICE.EXIT:
				exitFlag = true;
				break;
			default:
				System.out.println("잘못된 번호입니다.");
				return;
			}
		}
		System.out.println("선수단정보가 종료 되었습니다.");
		
	}
	private static void ReleaseplayerMenu() throws FileNotFoundException, SQLException, IOException {
		int num; 
		boolean exitFlag = false;
		while(!exitFlag) {
			MenuViewer.releasesPlayerMenuView();;
			num = Integer.parseInt(sc.nextLine()); 
			switch (num) {
			
			case RELEASE_PLAYER_CHOICE.UPDATE:
				ReleaseRegisterManager.ReleaseUpdate();
				break;
			case RELEASE_PLAYER_CHOICE.DELETE:
				ReleaseRegisterManager.ReleaseDelete();
				break;
			case RELEASE_PLAYER_CHOICE.PRINT:
				ReleaseRegisterManager.ReleasePrint();
				break;
			
			case RELEASE_PLAYER_CHOICE.EXIT:
				exitFlag = true;
				break;
			default:
				System.out.println("잘못된 번호입니다.");
				return;
			}
		}
		System.out.println("방출정보가 종료 되었습니다.");
		
	}
	private static void FaplayerMenu() throws FileNotFoundException, SQLException, IOException {
		int num; 
		boolean exitFlag = false;
		while(!exitFlag) {
			MenuViewer.FaPlayerMenuView();;
			num = Integer.parseInt(sc.nextLine()); 
			switch (num) {
			
			case FA_PLAYER_CHOICE.UPDATE:
				FaRegisterManager.FaUpdate();
				break;
			case FA_PLAYER_CHOICE.DELETE:
				FaRegisterManager.FaDelete();
				break;
			case FA_PLAYER_CHOICE.PRINT:
				FaRegisterManager.FaPrint();
				break;
				
			case FA_PLAYER_CHOICE.EXIT:
				exitFlag = true;
				break;
			default:
				System.out.println("잘못된 번호입니다.");
				return;
			}
		}
		System.out.println("이적시장정보가 종료 되었습니다.");
		
	}
	}

