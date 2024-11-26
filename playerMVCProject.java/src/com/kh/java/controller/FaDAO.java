package com.kh.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.java.model.FaVO;
import com.kh.java.model.ReleaseVO;

public class FaDAO {
	public static String selectSQL = "SELECT * FROM FA";
	public static String updateSQL = "UPDATE FA SET NAME = ?, POSSIT = ?, WEEKSAL = ?,FEE = ?, PHONE =?,TEAM = ?   WHERE NO = ?";
	public static String deleteSQL = "DELETE FROM FA WHERE NO =?";
	public static boolean FaUpdate(FaVO fvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		
		pstmt = con.prepareStatement(updateSQL);
		pstmt.setString(1, fvo.getName());
		pstmt.setString(2, fvo.getPossit());
		pstmt.setInt(3, fvo.getWeeksal());
		pstmt.setInt(4, fvo.getFee());
		pstmt.setString(5, fvo.getPhone());
		pstmt.setString(6, fvo.getTeam());
		pstmt.setInt(7, fvo.getNo());
		int result = pstmt.executeUpdate();
		successFlag =(result !=0)?true:false;
		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}
	public static boolean FaDelete(FaVO fvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		
		pstmt = con.prepareStatement(deleteSQL);
		pstmt.setInt(1, fvo.getNo());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false;
		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}
	public static ArrayList<FaVO> FaPrint() throws SQLException{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<FaVO> falist = new ArrayList<FaVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(selectSQL);
	
		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getNString("NAME");
			String possit = rs.getNString("POSSIT");
			int weekSal = rs.getInt("WEEKSAL");
			int fee = rs.getInt("FEE");
			String phone = rs.getString("PHONE");
			String team = rs.getString("TEAM");
			FaVO fvo = new FaVO(no, name, possit, weekSal, fee, phone, team);
			falist.add(fvo);

		}
		DBUtility.dbClose(con, stmt,rs);
		return falist;
	}
	
}
