package com.kh.java.playerMain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.kh.java.controller.PlayerRegisterManager;
import com.kh.java.view.MENU_CHOICE;
import com.kh.java.view.MenuViewer;
import com.kh.java.view.PLAYER_CHOICE;

public class playerMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException, SQLException, IOException {
		boolean exitFlag = false;

		while (!exitFlag) {
			MenuViewer.mainMenuView();
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case MENU_CHOICE.PLAYER:
				playerMenu();
				break;
			case MENU_CHOICE.RELEASEPLAYER:
				PlayerRegisterManager.PlayerUpdate();
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
		System.out.println("종료 되었습니다.");
		
	}

	}

