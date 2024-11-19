package studentTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDB {

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Employees> employeesList = new ArrayList<Employees>();

		// 1. jdbc driver load 2. connection
		con = DBConnection.dbCon();

		// 3. statement(query: C,U,R,d)
		// 4. result set
		// 5. 데이터 저장 진행
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");
			while (rs.next()) {
				int employeeId = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				int salary = rs.getInt("SALARY");
				System.out.println(employeeId + "\t" + firstName + "\t" + salary);
				Employees employees = new Employees(employeeId, firstName, salary);
				employeesList.add(employees);
			}
		} catch (SQLException e) {
			System.out.println("데이터베이스 실행문 에러"+e.toString());
		}
		
		//6데이터 출력
		employeesListPrint(employeesList);

		// 7. close
		DBConnection.dbClose(con, stmt, rs);

	}
	//테이블 내용 출력
	private static void employeesListPrint(ArrayList<Employees> employeesList) {
		for (Employees e : employeesList) {
			System.out.println(e.toString());
		}

	}

}
