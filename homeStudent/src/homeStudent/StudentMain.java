package homeStudent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import oracle.jdbc.driver.DBConversion;

public class StudentMain {
	public static Scanner sc = new Scanner(System.in);
	public static final int INSERT = 1, UPDATE = 2, DELETE = 3, PRINT = 4, EXIT = 5;

	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		// Student 출력, 입력, 수정, 삭제 메뉴
		while (!exitFlag) {
			printMenu();
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case INSERT:
				studentInsert();
				break;
			case UPDATE:
				booksUpdate();
				break;
			case DELETE:
				studentDelete();
				break;
			case PRINT:
				studentPrint();
				break;
			case EXIT:
				exitFlag = true;
				break;
			default:
				System.out.println("잘못된 번호입니다.");
				return;
			}
		}
		System.out.println("종료 되었습니다.");

	}
	// Udate
	private static void booksUpdate() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		Student stu = null;
		con = DBConnection.dbCon();
		System.out.println("수정할 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 이름를 입력하세요");
		String name = sc.nextLine();
		System.out.println("수정할 전화번호를 입력하세요");
		String phone = sc.nextLine();
		System.out.println("수정할 성별을 입력하세요");
		String gender = sc.nextLine();
		stmt = con.createStatement();
		stu = new Student(no, name, phone, gender);
		int result = stmt.executeUpdate("UPDATE STUDENT SET NO = '"+stu.getNo()+"',NANE ='"+stu.getName()+"',PHONE ='"+stu.getPhone()+"', GENDER='"+stu.getGender()+"");
		System.out.println((result != 0)?"수정완료":"수정실패");
		DBConnection.dbClose(con, stmt);
	}

	// Delete
	private static void studentDelete() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		con = DBConnection.dbCon();
		System.out.print("삭제할번호>>");
		int no = Integer.parseInt(sc.nextLine());
		stmt = con.createStatement();
		int result = stmt.executeUpdate("DELETE FROM STUDENT WHERE NO = " + no);
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		DBConnection.dbClose(con, stmt);
	}

	// Print
	private static void studentPrint() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Student> stulist = new ArrayList<Student>();
		con = DBConnection.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM  STUDENT");
		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			String phone = rs.getString("PHONE");
			String gender = rs.getString("GENDER");
			Student stu = new Student(no, name, phone, gender);
			stulist.add(stu);

		}
		stulistPrint(stulist);
		DBConnection.dbClose(con, stmt, rs);
	}

	// INSERT
	private static void studentInsert() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ArrayList<Student> stulist = new ArrayList<Student>();
		Student stu = null;
		con = DBConnection.dbCon();
		int no = 0;
		System.out.println("이름을 입력하세요");
		String name = sc.nextLine();
		System.out.println("전화번호를 입력하세요");
		String phone = sc.nextLine();
		System.out.println("성별을 입력하세요 M/F");
		String gender = sc.nextLine();
		stu = new Student(no, name, phone, gender);
		stmt = con.createStatement();
		int result = stmt.executeUpdate("INSERT INTO STUDENT VALUES ( STU_NO_SEQ.nextval  ,'" + stu.getName() + "','"
				+ stu.getPhone() + "','" + stu.getGender() + "')");
		System.out.println((result != 0) ? "삽입성공" : "삽입실패");
		DBConnection.dbClose(con, stmt);
	}

	private static void printMenu() {
		System.out.println("학생정보 메뉴 선택하세요");
		System.out.println("1. 입력, 2. 수정, 3. 삭제, 4. 출력, 5. 종료");

	}
	//출력
	private static void stulistPrint(ArrayList<Student> stulist) {
		for (Student stu : stulist) {
			System.out.println(stu.toString());
		}

	}
}