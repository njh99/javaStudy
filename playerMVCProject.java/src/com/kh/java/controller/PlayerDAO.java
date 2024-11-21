package com.kh.java.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.kh.java.model.PlayerVo;

public class PlayerDAO {
	public static String selectSQL = "SELECT * FROM PLAYER";
	public static String insertSQL = "INSERT INTO PLAYER VALUES(PLAYER_NO_SEQ.nextval,?,?,?,?)";
	public static String updateSQL = "UPDATE PLAYER SET NAME = ?, POSSIT = ?, WEEKSAL = ? , GAME = ? WHERE NO = ?";
	public static String deleteSQL = "DELETE FROM PLAYER WHERE NO =?";
	public static String funcSQL = "{ ? = call PLAYER_FUNCTION(?)}";
	public static String procSQL = "{call PLAYER_PROCEDURE(?,?,?)}";
	//삽입
	public static boolean PlayerInsert(PlayerVo pvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ArrayList<PlayerVo> playerlist = new ArrayList<PlayerVo>();
		playerlist = null;
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(insertSQL);
		pstmt.setString(1, pvo.getName());
		pstmt.setString(2, pvo.getPossit());
		pstmt.setInt(3, pvo.getWeekSal());
		pstmt.setInt(4, pvo.getGame());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false;
		DBUtility.dbClose(con, pstmt);

		return successFlag;
	}
	//수정
	public static boolean PlayerUpdate(PlayerVo pvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(updateSQL);
		pstmt.setString(1, pvo.getName());
		pstmt.setString(2, pvo.getPossit());
		pstmt.setInt(3, pvo.getWeekSal());
		pstmt.setInt(4, pvo.getGame());
		pstmt.setInt(5, pvo.getNo());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false;
		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}
	//삭제
	public static boolean PlayerDelete(PlayerVo pvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(deleteSQL);
		pstmt.setInt(1, pvo.getNo());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false;
		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}
	//출력
	public static ArrayList<PlayerVo> PlayerPrint() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<PlayerVo> playerlist = new ArrayList<PlayerVo>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery("selectSQL");
		
		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getNString("NAME");
			String possit = rs.getNString("POSSIT");
			int weekSal = rs.getInt("WEEKSAL");
			int game = rs.getInt("GAME");
			PlayerVo pvo = new PlayerVo(no, name, possit, weekSal, game);
			playerlist.add(pvo);

		}
		
		DBUtility.dbClose(con, stmt,rs);
		return playerlist;
	}

	
	
	public static boolean PlayerFunc(PlayerVo pvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		con = DBUtility.dbCon();
		
		cstmt = con.prepareCall(funcSQL);
		cstmt.registerOutParameter(1, Types.VARCHAR);
		cstmt.setInt(2, pvo.getNo());
		int result = cstmt.executeUpdate();
		String message = cstmt.getNString(1);
		successFlag = (result != 0) ? true : false;
		System.out.println(message);
		DBUtility.dbClose(con, cstmt);
		return successFlag;
	}

	public static boolean PlayerProc(PlayerVo pvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		con = DBUtility.dbCon();
		
		cstmt = con.prepareCall(procSQL);
		cstmt.setInt(1, pvo.getNo());
		cstmt.setInt(2, pvo.getGame());
		cstmt.registerOutParameter(3, Types.VARCHAR);
		int result = cstmt.executeUpdate();
		String message = cstmt.getNString(3);
		successFlag = (result != 0) ? true : false;
		System.out.println(message);
		DBUtility.dbClose(con, cstmt);
		return successFlag;
	}
}
