package booktest2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {
	public static Connection dbCon() {
		// 오라클 접속 할 수 있는 객체참조변수 선언
		// 2. connection
		Connection con = null;

		// 1. jdbc driver load
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//오라클 드라이버를 찾아서 메로리에 로딩
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe", "hr", "hr");//오라클서버(데이터베이스 서버) 접속 요청
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
		 catch (SQLException e) {
			System.out.println(e.toString());
		}
		return con;
	}
	//sql 객체 close
	public static void dbClose(Connection con, Statement stmt, ResultSet rs) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void dbClose(Connection con, Statement stmt) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
