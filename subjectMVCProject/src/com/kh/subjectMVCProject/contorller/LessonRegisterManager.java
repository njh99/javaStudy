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
		System.out.print("과목요약입력 O-운영체제 ,A-어셈블러 ,C-컴파일러  ,J-자료 ,P프로밍 ,D-디비 ,S-소프공학>>");
		String abbre = (sc.nextLine().trim());
		System.out.print("과목명입력 O-운영체제 ,A-어셈블러 ,C-컴파일러  ,J-자료 ,P프로밍 ,D-디비 ,S-소프공학>>");
		String name = (sc.nextLine().trim());

		lessonVO lvo = new lessonVO(abbre, name);
		boolean successFlag = ldao.lessonInsert(lvo);

		// 화면출력
		if (successFlag == true) {
			System.out.println(name + "과목을 입력하였습니다.");
		} else {
			System.out.println("입력 실패되었습니다.");
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
		// 수정하기 위한 전체출력
		LessonDAO ldao = new LessonDAO();
		lessonVO lvo = new lessonVO();
		
		
		ArrayList<lessonVO> lessonlist = ldao.lessonSelect(lvo);
		
		if (lessonlist.size() != 0) {
			printLessonList(lessonlist);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
		// 화면으로부터 입력을 받는다
		System.out.print("삭제할 번호>>");
		int no = Integer.parseInt(sc.nextLine());

		lvo = new lessonVO();
		lvo.setNo(no);
		boolean successFlag = ldao.lessonDelete(lvo);

		// 화면출력
		if (successFlag == true) {
			System.out.println(no + "번이 삭제되었습니다.");
		} else {
			System.out.println("삭제 실패되었습니다.");
		}
	}

	// 과목 수정(update)
	public void UpdateManager() {
		// 수정하기 위한 전체출력
		LessonDAO ldao = new LessonDAO();
		lessonVO lvo = new lessonVO();
		ArrayList<lessonVO> lessonlist = ldao.lessonSelect(lvo);

		// 화면출력
		if (lessonlist.size() != 0) {
			printLessonList(lessonlist);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
		// 화면으로부터 입력을 받는다
		System.out.print("수정할번호>>");
		int no = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 과목요약입력 O-운영체제 ,A-어셈블러 ,C-컴파일러  ,J-자료 ,P프로밍 ,D-디비 ,S-소프공학>>");
		String abbre = (sc.nextLine().trim());
		System.out.print("수정할 과목명입력 O-운영체제 ,A-어셈블러 ,C-컴파일러  ,J-자료 ,P프로밍 ,D-디비 ,S-소프공학>>");
		String name = (sc.nextLine().trim());

		lvo = new lessonVO(no, abbre, name);
		boolean successFlag = ldao.lessonUpdate(lvo);

		// 화면출력
		if (successFlag == true) {
			System.out.println(no + "번호를 수정하였습니다.");
		} else {
			System.out.println("수정 실패되었습니다.");
		}
	}

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
