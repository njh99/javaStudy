package com.kh.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.java.model.ReleaseVO;

public class ReleaseDAO {
	public static String selectSQL = "SELECT * FROM RELEASE";
	public static String updateSQL = "UPDATE RELEASE SET NAME = ?, POSSIT = ?, WEEKSAL = ?   WHERE NO = ?";
	public static String deleteSQL = "DELETE FROM RELEASE WHERE NO =?";
	public static boolean ReleaseUpdate(ReleaseVO rvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		
		pstmt = con.prepareStatement(updateSQL);
		pstmt.setString(1, rvo.getName());
		pstmt.setString(2, rvo.getPossit());
		pstmt.setInt(3, rvo.getWeekSal());
		pstmt.setInt(4, rvo.getNo());
		int result = pstmt.executeUpdate();
		successFlag =(result !=0)?true:false;
		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}
	public static boolean ReleaseDelete(ReleaseVO rvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		
		pstmt = con.prepareStatement(deleteSQL);
		pstmt.setInt(1, rvo.getNo());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false;
		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}
	public static ArrayList<ReleaseVO> ReleasePrint() throws SQLException{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ReleaseVO> releaselist = new ArrayList<ReleaseVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(selectSQL);
	
		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getNString("NAME");
			String possit = rs.getNString("POSSIT");
			int weekSal = rs.getInt("WEEKSAL");
			ReleaseVO rvo = new ReleaseVO(no, name, possit, weekSal);
			releaselist.add(rvo);

		}
		DBUtility.dbClose(con, stmt,rs);
		return releaselist;
	}
	
}
