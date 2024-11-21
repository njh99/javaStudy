package playerTest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;








public class PlayerMain {

	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		
		while(!exitFlag) {
			printMenu();
			int num = Integer.parseInt(sc.nextLine()); 
			switch (num) {
			case PlayerMenu.INSERT:
				PlayerInsert();
				break;
			case PlayerMenu.UPDATE:
				PlayerUpdate();
				break;
			case PlayerMenu.DELETE:
				PlayerDelete();
				break;
			case PlayerMenu.PRINT:
				PlayerPrint();
				break;
			case PlayerMenu.FUNC:
				PlayerFunc();
				break;
			case PlayerMenu.PROC:
				PlayerProc();
				break;
			case PlayerMenu.EXIT:
				exitFlag = true;
				break;
			default:
				System.out.println("잘못된 번호입니다.");
				return;
			}
		}
		System.out.println("종료 되었습니다.");
		
	}
	private static void PlayerProc() throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;
		con = DBConnection.dbCon();
		System.out.println("경기 수를 추가할 선수의 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		System.out.println("추가할 경기 수를 입력하세요");
		int game = Integer.parseInt(sc.nextLine());
		cstmt = con.prepareCall("{call PLAYER_PROCEDURE(?,?,?)}");
		cstmt.setInt(1, no);
		cstmt.setInt(2, game);
		cstmt.registerOutParameter(3, Types.VARCHAR);
		int result = cstmt.executeUpdate();
		String message = cstmt.getNString(3);
		System.out.println(message);
		System.out.println((result != 0) ? "경기 수 변경성공" : "경기 수 변경 실패");
		DBConnection.dbClose(con, cstmt);
	}
	private static void PlayerFunc() throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;
		con = DBConnection.dbCon();
		System.out.println("선수의 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		cstmt = con.prepareCall("{ ? = call PLAYER_FUNCTION(?)}");
		cstmt.registerOutParameter(1, Types.VARCHAR);
		cstmt.setInt(2, no);
		int result = cstmt.executeUpdate();
		String message = cstmt.getNString(1);
		System.out.println((result != 0) ? "선수가 확인되었습니다." : "FUNCTION 실패");
		System.out.println(message);
		DBConnection.dbClose(con, cstmt);
	}

	// DELETE
	private static void PlayerDelete() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBConnection.dbCon();
		System.out.println("삭제할 선수의 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		pstmt = con.prepareStatement("DELETE FROM PLAYER WHERE NO =?");
		pstmt.setInt(1, no);
		int result =pstmt.executeUpdate();
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		DBConnection.dbClose(con, pstmt);
	}
	// UPDATE
	private static void PlayerUpdate() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		Player pl = null;
		con = DBConnection.dbCon();
		System.out.println("수정할 번호를 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 이름을 입력하세요");
		String name = sc.nextLine();
		System.out.println("수정할 포지션을 입력하세요");
		String possit = sc.nextLine();
		System.out.println("수정할 주급을 입력하세요");
		int weekSal = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 경기 수를 입력하세요");
		int game = Integer.parseInt(sc.nextLine());
		pl = new Player(no, name, possit, weekSal, game);
		pstmt = con.prepareStatement("UPDATE PLAYER SET NAME = ?, POSSIT = ?, WEEKSAL = ? , GAME = ? WHERE NO = ?");
		pstmt.setString(1, pl.getName());
		pstmt.setString(2, pl.getPossit());
		pstmt.setInt(3, pl.getWeekSal());
		pstmt.setInt(4, pl.getGame());
		pstmt.setInt(5, pl.getNo());
		int result = pstmt.executeUpdate();
		System.out.println((result != 0)?"수정완료":"수정실패");
		DBConnection.dbClose(con, pstmt);
	}
	// INSERT
	private static void PlayerInsert() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArrayList<Player> pllist = new ArrayList<Player>();
		Player pl = null;
		con = DBConnection.dbCon();
		int no = 0;
		System.out.println("이름을 입력하세요");
		String name = sc.nextLine();
		System.out.println("포지션을 입력하세요 FW/MF/DF/GK");
		String possit = sc.nextLine();
		System.out.println("주급을 입력하세요");
		int weekSal = Integer.parseInt(sc.nextLine());
		System.out.println("경기 수를 입력하세요");
		int game = Integer.parseInt(sc.nextLine());
		pl = new Player(no, name, possit, weekSal, game);
		pstmt = con.prepareStatement("INSERT INTO PLAYER VALUES(PLAYER_NO_SEQ.nextval,?,?,?,?)");
		pstmt.setString(1, pl.getName());
		pstmt.setString(2, pl.getPossit());
		pstmt.setInt(3, pl.getWeekSal());
		pstmt.setInt(4, pl.getGame());
		int result = pstmt.executeUpdate();
		System.out.println((result != 0) ? "삽입성공" : "삽입실패");
		DBConnection.dbClose(con, pstmt);
	}
	// PRINT
	private static void PlayerPrint() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Player> pllist = new ArrayList<Player>();
		con = DBConnection.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM PLAYER");
		while(rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getNString("NAME");
			String possit = rs.getNString("POSSIT");
			int weekSal = rs.getInt("WEEKSAL");
			int game = rs.getInt("GAME");
			Player pl = new Player(no, name, possit, weekSal, game);
			pllist.add(pl);
			
		}
		pllistPrint(pllist);
		
	}
	private static void pllistPrint(ArrayList<Player> pllist) {
		for(Player pl : pllist) {
			System.out.println(pl.toString());
		}
		
	}
	private static void printMenu() {
		System.out.println("메뉴를 선택하세요");
		System.out.println("1. 입력, 2. 수정, 3. 삭제, 4. 출력, 5. 선수확인, 6.경기 수 추가 7. 종료");
	}

}
