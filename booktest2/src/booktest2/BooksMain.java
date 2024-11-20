package booktest2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;

import oracle.jdbc.driver.DBConversion;

public class BooksMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		// 사용자로부터 Books 출력, 입력, 수정, 삭제 , 종료요청받는다.
		while (!exitFlag) {
			printMenu();
			int num = Integer.parseInt(scan.nextLine());
			switch (num) {
			case BookMenu.PRINT:
				booksPrint();
				break;
			case BookMenu.INSERT:
				booksInsert();
				break;
			case BookMenu.UPDATE:
				booksUpdate();
				break;
			case BookMenu.DELETE:
				booksDelete();
				break;
			case BookMenu.SALARY_UP:
				employeeSalaryUp();
				break;
			case BookMenu.EXIT:
				exitFlag = true;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
		}
		System.out.println("The end");
	}

	// 연봉 10%인상
	private static void employeeSalaryUp() throws SQLException {
		// Connection
		Connection con = null;
		CallableStatement cstmt = null;
		// 1 Load,2 connect
		con = DBConnection.dbCon();
		System.out.println("인상될 ID 입력: >>");
		
		int id= Integer.parseInt(scan.nextLine());
		System.out.println("인상금액 입력하세요: >>");
		int price= Integer.parseInt(scan.nextLine());
		// 3. cstmt = con.prepareCall("{call EMP1_PROCEDURE(?,?,?)}");
		cstmt = con.prepareCall("{call  BOOKS_PROCEDURE(?,?,?)}");//자바에서 프로시져 부르는 법.
		cstmt.setInt(1, id);
		cstmt.setInt(2, price);
		// 출력될 데이터 값으로 3번을 바인딩 시킨다.
		cstmt.registerOutParameter(3, Types.VARCHAR);
		
		// 수정할 데이터를 입력
		
		int result = cstmt.executeUpdate();
		String message = cstmt.getString(3);
		System.out.println(message);
		// 4.내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "책값인상 프로시저성공" : "책값인상 프로시저실패");
		// 6.sql 객체 반남
		DBConnection.dbClose(con, cstmt);

	}

	// 삭제
	private static void booksDelete() throws SQLException {
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load,2 connect
		con = DBConnection.dbCon();
		// 3.statement
		System.out.print("삭제할번호>>");
		int no = Integer.parseInt(scan.nextLine());
		pstmt = con.prepareStatement("DELETE FROM BOOKS WHERE ID =? ");
		pstmt.setInt(1, no);
		int result = pstmt.executeUpdate();
		// 4.내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		// 6.sql 객체 반남
		DBConnection.dbClose(con, pstmt);
	}

	// 수정
	private static void booksUpdate() throws SQLException {
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load,2 connect
		con = DBConnection.dbCon();
		// 3.statement
		// 수정할 데이터를 입력
		Books books = new Books(11, "yhj", "njh", "1999", 33000);
		pstmt = con.prepareStatement("UPDATE BOOKS SET TITLE = ?,PUBLISHER = ?, YEAR = ?, PRICE = ? WHERE ID = ?");
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());
		pstmt.setInt(5, books.getId());
		int result = pstmt.executeUpdate();
		// 4.내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "수정성공" : "수정실패");
		// 6.sql 객체 반남
		DBConnection.dbClose(con, pstmt);

	}

	// 삽입
	private static void booksInsert() throws SQLException {
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1 Load,2 connect
		con = DBConnection.dbCon();
		// 3.statement
		Books books = new Books(0, "hello", "kdj", "2008", 23000);
		pstmt = con.prepareStatement("INSERT INTO BOOKS VALUES(books_id_seq.nextval,?,?,?,?)");
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());
		int result = pstmt.executeUpdate();
		// 4.내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "삽입성공" : "삽입실패");
		// 6.sql 객체 반남
		DBConnection.dbClose(con, pstmt);

	}

	// 출력
	public static void booksPrint() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Books> booksList = new ArrayList<Books>();

		// 1 Load,2 connect
		con = DBConnection.dbCon();
		// 3.statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM  BOOKS");
		// 4.테이블내용 가져오기
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String publisher = rs.getString("PUBLISHER");
			String year = rs.getString("YEAR");
			int price = rs.getInt("PRICE");
			Books books = new Books(id, title, publisher, year, price);
			booksList.add(books);
		}
		// 5.출력하기
		booksListPrint(booksList);
		// 6.sql 객체 반남
		DBConnection.dbClose(con, stmt, rs);
	}

	private static void printMenu() {
		System.out.println("Books Menu(1.출력, 2.입력, 3.수정  4.삭제  5.책값인상 6. 종료)");
		System.out.print(">>");
	}

	private static void booksListPrint(ArrayList<Books> booksList) {
		for (Books books : booksList) {
			System.out.println(books.toString());
		}

	}

}