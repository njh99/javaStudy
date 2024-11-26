package com.kh.subjectMVCProject.contorller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.lessonVO;

public class LessonRegisterManager {
	public Scanner sc = new Scanner(System.in);
	// 과목 등록(insert)
	public void InsertManager() {
		LessonDAO ldao = new LessonDAO();
		// 화면으로부터 입력을 받는다
		System.out.print("과목요약>>");
		System.out.print("K-국어  / M-수학  /  E-영어  / H-역사  / P-\r\n"
                + "프로그래밍 / D-데이터베이스 / ED-교육학이론\r\n"
                + "");
		int no = Integer.parseInt(sc.nextLine());

		lessonVO lvo = new lessonVO();
		lvo.setNo(no);
		ldao.lessonDelete(lvo);
		boolean successFlag = ldao.lessonDelete(lvo);

		// 화면출력
		if (successFlag == true) {
			System.out.println(no + "번이 삭제되었습니다.");
		} else {
			System.out.println("삭제 실패되었습니다.");
		}
	}
	// 과목 목록(select)
	public void selectManager() {
		LessonDAO ldao = new LessonDAO();
		// 화면으로부터 입력을 받는다
		// 데이터베이스 요청
		lessonVO lvo = new lessonVO();
		ArrayList<lessonVO> lessonlist = ldao.lessonSelect(lvo);

		// 화면출력
		if (lessonlist.size() != 0) {
			printLessonList(lessonlist);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	// 과목 삭제(delete)
	public void deleteManager() {
		LessonDAO ldao = new LessonDAO();
		// 화면으로부터 입력을 받는다
		System.out.print("삭제할 번호>>");
		int no = Integer.parseInt(sc.nextLine());

		lessonVO lvo = new lessonVO();
		lvo.setNo(no);
		ldao.lessonDelete(lvo);
		boolean successFlag = ldao.lessonDelete(lvo);

		// 화면출력
		if (successFlag == true) {
			System.out.println(no + "번이 삭제되었습니다.");
		} else {
			System.out.println("삭제 실패되었습니다.");
		}
	}
	// 과목 수정(update)

	// 과목 정렬(sort)
	public void selectSortManager() {
		LessonDAO ldao = new LessonDAO();
		// 화면으로부터 입력을 받는다
		// 데이터베이스 요청
		lessonVO lvo = new lessonVO();
		ArrayList<lessonVO> lessonlist = ldao.lessonSelectSort(lvo);
		// 화면출력
		if (lessonlist.size() != 0) {
			printLessonList(lessonlist);
		} else {
			System.out.println("정렬할 데이터가 없습니다.");
		}
	}
	// 화면출력
	public void printLessonList(ArrayList<lessonVO> lessonlist) {
		for (lessonVO data : lessonlist) {
			System.out.println(data);
		}

	}
}
