package com.kh.subjectMVCProject.contorller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Connection con = DBUtility.dbCon();
		if(con != null) {
			System.out.println("데이터 성공");
		}else {
			System.out.println("접속실패");
		}
	}

}
