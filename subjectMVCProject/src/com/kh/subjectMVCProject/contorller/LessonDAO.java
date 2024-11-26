package com.kh.subjectMVCProject.contorller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.lessonVO;

//자바에서 데이터베이스 접근해서 CURD 전문적으로 담당하는 클래스
public class LessonDAO {
	public final String LESSON_SELECT = "SELECT * FROM LESSON";
	public final String LESSON_SELECT_SORT = "SELECT * FROM LESSON ORDER BY NAME";
	public final String LESSON_DELETE = "DELETE FROM LESSON WHERE NO =?";
	public final String LESSON_UPDATE = "UPDATE LESSON SET ABBRE =?, NAME =? WHERE NO =?";
	public final String LESSON_INSERT = "INSERT INTO LESSON VALUES(LESSON_SEQ.NEXTVAL,?,?)";

	// lesson 테이블에서 select 출력레코드를 리턴
	public ArrayList<lessonVO> lessonSelect(lessonVO lvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		ResultSet rs = null; // 오라클에서 결과물을 받는 객체
		ArrayList<lessonVO> lessonList = new ArrayList<lessonVO>();// 결과값을 다른객체전달하기 위해서 사용하는 객체

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_SELECT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String abbre = rs.getString("ABBRE");
				String name = rs.getString("NAME");
				lessonVO lessonvo = new lessonVO(no, abbre, name);
				lessonList.add(lessonvo);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return lessonList;
	}

	public ArrayList<lessonVO> lessonSelectSort(lessonVO lvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		ResultSet rs = null; // 오라클에서 결과물을 받는 객체
		ArrayList<lessonVO> lessonList = new ArrayList<lessonVO>();// 결과값을 다른객체전달하기 위해서 사용하는 객체

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_SELECT_SORT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String abbre = rs.getString("ABBRE");
				String name = rs.getString("NAME");
				lessonVO lessonvo = new lessonVO(no, abbre, name);
				lessonList.add(lessonvo);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return lessonList;

	}

	// lesson 테이블에서 delete 레코드를 삭제
	public boolean lessonDelete(lessonVO lvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_DELETE);
			pstmt.setInt(1, lvo.getNo());
			int count = pstmt.executeUpdate();
			successFlag = (count !=0)?true:false;
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;
	
	}

	// lesson 테이블에서 Update 레코드를 수정
	public boolean lessonUpdate(lessonVO lvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_UPDATE);
			pstmt.setString(1, lvo.getAbbre());
			pstmt.setString(2, lvo.getName());
			pstmt.setInt(3, lvo.getNo());
			int count = pstmt.executeUpdate();
			successFlag = (count !=0)?(true):(false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;
	
	}

	// lesson 테이블에서 insert 레코드를 삽입
	public boolean lessonInsert(lessonVO lvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_INSERT);
			pstmt.setString(1, lvo.getAbbre());
			pstmt.setString(2, lvo.getName());
			int count = pstmt.executeUpdate();
			successFlag = (count !=0)?(true):(false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;
	
	}
}
