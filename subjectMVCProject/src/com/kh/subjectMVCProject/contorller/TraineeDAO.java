package com.kh.subjectMVCProject.contorller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.TraineeVO;

public class TraineeDAO {
	// 쿼리문
	public final String TRAINEE_SELECT = "SELECT * FROM TRAINEE";
	public final String TRAINEE_ALL_SELECT = "select T.NO, T.SECTION,t.registdate,S.num,S.NAME AS sname,L.ABBRE,L.NAME AS lname "
			+ " from TRAINEE T INNER JOIN STUDENT S ON T.S_NUM = S.NUM "
			+ " INNER JOIN LESSON L ON T.ABBRE = L.ABBRE ORDER BY T.NO";
			
	public final String TRAINEE_SELECT_SORT = "SELECT * FROM TRAINEE ORDER BY NAME";
	public final String TRAINEE_DELETE = "DELETE FROM TRAINEE WHERE NO =?";
	public final String TRAINEE_UPDATE = "UPDATE TRINEE SET S_NUM = ?, ABBRE = ?, SECTION = ? WHERE NO =?";
	public final String TRAINEE_INSERT = "INSERT INTO TRAINEE VALUES(TRAINEE_SEQ.NEXTVAL,?,?,?,SYSDATE)";

	// traineeInsert
	// lesson 테이블에서 select 출력레코드를 리턴
	public boolean traineeInsert(TraineeVO tvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_INSERT);
			pstmt.setString(1, tvo.getS_num());
			pstmt.setString(2, tvo.getAbbre());
			pstmt.setString(3, tvo.getSection());
			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true) : (false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;

	}

	// traineeSelect
	public ArrayList<TraineeVO> traineeSelect(TraineeVO tvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		ResultSet rs = null; // 오라클에서 결과물을 받는 객체
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();// 결과값을 다른객체전달하기 위해서 사용하는 객체

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_SELECT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String s_num = rs.getString("S_NUM");
				String abbre = rs.getString("ABBRE");
				String section = rs.getString("SECTION");
				Date registdate = rs.getDate("REGISTDATE");
				TraineeVO traineevo = new TraineeVO(no, s_num, abbre, section, registdate);
				traineeList.add(traineevo);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return traineeList;
	}
	// traineeAllSelect(join Lesson, Student)
	public ArrayList<TraineeVO> traineeAllSelect(TraineeVO tvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		ResultSet rs = null; // 오라클에서 결과물을 받는 객체
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();// 결과값을 다른객체전달하기 위해서 사용하는 객체
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_SELECT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String section = rs.getString("SECTION");
				Date registdate = rs.getDate("REGISTDATE");
				String s_num = rs.getString("NUM");
				String s_name = rs.getString("SNAME");
				String abbre = rs.getString("ABBRE");
				String l_name = rs.getString("LNAME");
				TraineeVO traineevo = new TraineeVO(no, s_num, abbre, section, registdate, s_name, l_name);
				traineeList.add(traineevo);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return traineeList;
	}
	// traineeDelete
	// 자동으로 커밋 기능이 세팅 되어있다.
	public boolean traineeDelete(TraineeVO tvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		boolean successFlag = false;
		
		try {
			//커밋을 수동으로 바꾼다.
			con = DBUtility.dbCon();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(TRAINEE_DELETE);
			pstmt.setInt(1, tvo.getNo());
			int count = pstmt.executeUpdate();
			successFlag = (count !=0)?true:false;
			if(count != 0) {
				successFlag = true;
				//삭제 성공시 커밋 기능을 부여
				con.commit();
			}else{
				successFlag = false;
				//삭제 실패시 롤백 기능을 부여
				con.rollback();
			}
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;
	
	}
	// traineeUpdate
	public boolean traineeUpdate(TraineeVO tvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_UPDATE);
			pstmt.setString(1, tvo.getS_num());
			pstmt.setString(2, tvo.getAbbre());
			pstmt.setString(3, tvo.getSection());
			int count = pstmt.executeUpdate();
			successFlag = (count !=0)?(true):(false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;
	
	}
	// traineeSort
	public ArrayList<TraineeVO> traineeSelectSort(TraineeVO tvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		ResultSet rs = null; // 오라클에서 결과물을 받는 객체
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();// 결과값을 다른객체전달하기 위해서 사용하는 객체

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_SELECT_SORT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String s_num = rs.getString("S_NUM");
				String abbre = rs.getString("ABBRE");
				String section = rs.getString("SECTION");
				Date registdate = rs.getDate("REGISTDATE");
				TraineeVO traineevo = new TraineeVO(no, s_num, abbre, section, registdate);
				traineeList.add(traineevo);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return traineeList;

	}
}
