package com.kh.java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.java.controller.PlayerRegisterManager;
import com.kh.java.view.PlayerCURDMenu;
import com.kh.java.view.PlayerMenu;



public class PlayerMVCProject {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
			boolean exitFlag = false;
			
			while(!exitFlag) {
				PlayerMenu.printMenu();
				int num = Integer.parseInt(sc.nextLine()); 
				switch (num) {
				case PlayerCURDMenu.INSERT:
					PlayerRegisterManager.PlayerInsert();
					break;
				case PlayerCURDMenu.UPDATE:
					PlayerRegisterManager.PlayerUpdate();
					break;
				case PlayerCURDMenu.DELETE:
					PlayerRegisterManager.PlayerDelete();
					break;
				case PlayerCURDMenu.PRINT:
					PlayerRegisterManager.PlayerPrint();
					break;
				case PlayerCURDMenu.FUNC:
					PlayerRegisterManager.PlayerFunc();
					break;
				case PlayerCURDMenu.PROC:
					PlayerRegisterManager.PlayerProc();
					break;
				case PlayerCURDMenu.EXIT:
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
