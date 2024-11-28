package publicDataTest.controll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import publicDataTest.model.LandPriceVO;

public class LandPriceDAO {
	public static final String LANDPRICE_SELECT = "SELECT * FROM LANDPRICE";
	public static final String LANDPRICE_CHECK_NODENO_SELECT = "SELECT COUNT(*) FROM LANDPRICE WHERE NODENO = ?";
	public static final String LANDPRICE_INSERT = "insert into LANDPRICE values(?,?,?, ?, ?)";
	public static final String LANDPRICE_UPDATE = "UPDATE LANDPRICE SET GPSLATI = ?,GPSLONG = ?,NODEID = ?,NODENM = ? WHERE  NODENO = ?";
	public static final String LANDPRICE_DELETE = "DELETE FROM LANDPRICE WHERE  NODENO = ?";

	public ArrayList<LandPriceVO> landPriceSelect() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<LandPriceVO> list = new ArrayList<LandPriceVO>();

		try {
			con = DBUtility.dbCon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(LANDPRICE_SELECT);
			if (rs.next()) {
				do {
					int nodeno = rs.getInt("NODENO");
					double gpslati = rs.getDouble("GPSLATI");
					double gpslong = rs.getDouble("GPSLONG");
					String nodeid = rs.getString("NODEID");
					String nodenm = rs.getString("NODENM");
					LandPriceVO svo = new LandPriceVO(nodeno, gpslati, gpslong, nodeid, nodenm);
					list.add(svo);
				} while (rs.next());
			} else {
				list = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			DBUtility.dbClose(con, stmt, rs);
		}

		return list;
	}

	public boolean landPriceInsert(LandPriceVO lvo) throws SQLException {
		// Conection
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(LANDPRICE_INSERT);
		pstmt.setInt(1, lvo.getNodeno());
		pstmt.setDouble(2, lvo.getGpslati());
		pstmt.setDouble(3, lvo.getGpslong());
		pstmt.setString(4, lvo.getNodeid());
		pstmt.setString(5, lvo.getNodenm());

		int result = pstmt.executeUpdate();

		DBUtility.dbClose(con, pstmt);
		successFlag = (result != 0) ? true : false;
		return successFlag;
	}

	public static boolean landPriceUpdate(LandPriceVO lvo) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LANDPRICE_UPDATE);
			pstmt.setDouble(1, lvo.getGpslati());
			pstmt.setDouble(2, lvo.getGpslong());
			pstmt.setString(3, lvo.getNodeid());
			pstmt.setString(4, lvo.getNodenm());
			pstmt.setInt(5, lvo.getNodeno());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;
	}

	public static boolean landPriceDelete(LandPriceVO lvo) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LANDPRICE_DELETE);
			pstmt.setInt(1, lvo.getNodeno());
			int result;
			result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {

		} finally {
			DBUtility.dbClose(con, pstmt);

		}
		return successFlag;
	}

	public int landPriceCheckNodeNoSelect(LandPriceVO lvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LandPriceVO> list = new ArrayList<LandPriceVO>();
		int count = 0;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LANDPRICE_CHECK_NODENO_SELECT);
			pstmt.setInt(1, lvo.getNodeno());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {

			DBUtility.dbClose(con, pstmt, rs);
		}

		return count;

	}

}
