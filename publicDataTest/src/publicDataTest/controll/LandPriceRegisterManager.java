package publicDataTest.controll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import publicDataTest.PublicDataAPITest;
import publicDataTest.model.LandPriceVO;

public class LandPriceRegisterManager {
	public Scanner sc = new Scanner(System.in);

	// 과목 등록(insert)
	public void InsertManager() {
		LandPriceDAO ldao = new LandPriceDAO();
		boolean successFlag = false;
		// 네트워크로부터 데이터를 입력받는다.
		ArrayList<LandPriceVO> landPricelist = PublicDataAPITest.apiDataLoad();
		for (LandPriceVO lvo : landPricelist) {
			int count = ldao.landPriceCheckNodeNoSelect(lvo);
			try {
				if (count <= 0) {

					successFlag = ldao.landPriceInsert(lvo);
				} else {
					successFlag = ldao.landPriceUpdate(lvo);
				}
				// 화면출력
				if (successFlag == true) {
					System.out.println("데이터를  입력또는 수정하였습니다.");
				} else {
					System.out.println("데이터를  입력또는 수정실패하였습니다.");
				}

			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}

	}

	// 과목 목록(select)
	public void selectManager() {
		LandPriceDAO ldao = new LandPriceDAO();
		// 화면으로부터 입력을 받는다
		// 데이터베이스 요청
		LandPriceVO lvo = new LandPriceVO();
		ArrayList<LandPriceVO> list = ldao.landPriceSelect();

		// 화면출력
		if (list.size() != 0) {
			printLandPriceList(list);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	// 과목 삭제(delete)
	public void deleteManager() {
		// 수정하기 위한 전체출력
		LandPriceDAO ldao = new LandPriceDAO();
		LandPriceVO lvo = new LandPriceVO();

		ArrayList<LandPriceVO> lessonlist = ldao.landPriceSelect();

		if (lessonlist.size() != 0) {
			printLandPriceList(lessonlist);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
		// 화면으로부터 입력을 받는다
		System.out.print("삭제할 번호>>");
		int nodeno = Integer.parseInt(sc.nextLine());
		lvo = new LandPriceVO();
		lvo.setNodeno(nodeno);
		boolean successFlag = ldao.landPriceDelete(lvo);

		// 화면출력
		if (successFlag == true) {
			System.out.println(nodeno + "번이 삭제되었습니다.");
		} else {
			System.out.println("삭제 실패되었습니다.");
		}
	}

	// 과목 수정(update)
	public void UpdateManager() {
		// 수정하기 위한 전체출력
		LandPriceDAO ldao = new LandPriceDAO();
		LandPriceVO lvo = new LandPriceVO();

		// 화면출력
		ArrayList<LandPriceVO> lessonlist = ldao.landPriceSelect();
		if (lessonlist.size() != 0) {
			printLandPriceList(lessonlist);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
		// 화면으로부터 입력을 받는다
		System.out.print("수정할번호>>");
		int nodeno = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 위도입력>>");
		double gpslati = Double.parseDouble(sc.nextLine().trim());
		System.out.print("수정할 경도>>");
		double gpslong = Double.parseDouble(sc.nextLine().trim());
		System.out.print("수정할 노드아이디>>");
		String nodeid = (sc.nextLine()).trim();
		System.out.print("수정할 정류소>>");
		String nodenm = (sc.nextLine()).trim();

		lvo = new LandPriceVO(nodeno, gpslati, gpslong, nodeid, nodenm);
		boolean successFlag = ldao.landPriceUpdate(lvo);

		// 화면출력
		if (successFlag == true) {
			System.out.println(nodeno + "번호를 수정하였습니다.");
		} else {
			System.out.println("수정 실패되었습니다.");
		}
	}

	// 화면출력
	public void printLandPriceList(ArrayList<LandPriceVO> list) {
		for (LandPriceVO data : list) {
			System.out.println(data);
		}

	}
}
