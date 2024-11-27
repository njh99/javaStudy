package com.kh.subjectMVCProject.contorller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.StudentVO;
import com.kh.subjectMVCProject.model.TraineeVO;

public class TraineeRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	public static void totalSelectManager(){
		// 전체 학생리스트를 출력요청
		TraineeDAO tdao = new TraineeDAO();
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();
		traineeList = tdao.traineeAllSelect(new TraineeVO());
		if (traineeList.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printTraineeAllList(traineeList);
	}

	public static void selectManager(){
		TraineeDAO tdao = new TraineeDAO();
		ArrayList<TraineeVO> traineelist = new ArrayList<TraineeVO>();
		traineelist = tdao.traineeSelect(new TraineeVO());
		if (traineelist.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printTraineeList(traineelist);
	}

	public static void insertManager(){
		TraineeDAO tdao = new TraineeDAO();
		// 3.statement
		System.out.print("학생 번호등록(학생이름입력하세요): ");
		String name = sc.nextLine();
		//검색된 이름으로 학번, 이름, 이메일 출력 통해서 학번입력
		StudentRegisterManager srm = new StudentRegisterManager();
		srm.selectNameSearchManager();
		
		System.out.println("번호등록");
		String s_name = sc.nextLine();
		//student 검색기능추가
		
		LessonRegisterManager lrm = new LessonRegisterManager();
		lrm.selectManager();
		
		System.out.println("과목요약입력 >>");
		String abbre = (sc.nextLine().trim());
		
		
		System.out.print("전공/부전공/교양 선택>> ");
		String section = (sc.nextLine().trim());
		
		TraineeVO studentVO = new TraineeVO(0, s_name, abbre, section, null);
		boolean successFlag = tdao.traineeInsert(studentVO);

		if (successFlag == true) {
			System.out.println("입력처리 성공");
		} else {
			System.out.println("입력처리 실패");
		}
	}

	public static void updateManager(){
		TraineeDAO tdao = new TraineeDAO();
		//Trainee 테이블 전체내용을 보여준다.
		selectManager();
		// 3.statement
		System.out.print("수정할번호입력>> ");
		int no = Integer.parseInt(sc.nextLine());
		//검색된 이름으로 학번, 이름, 이메일 출력 통해서 학번입력
		StudentRegisterManager srm = new StudentRegisterManager();
		srm.selectNameSearchManager();
		
		System.out.println("번호등록");
		String s_name = sc.nextLine();
		//student 검색기능추가
		
		LessonRegisterManager lrm = new LessonRegisterManager();
		lrm.selectManager();
		
		System.out.println("과목요약입력 >>");
		String abbre = (sc.nextLine().trim());
		
		
		System.out.print("전공/부전공/교양 선택>> ");
		String section = (sc.nextLine().trim());
		
		TraineeVO traineeVO = new TraineeVO(no, s_name, abbre, section, null);
		boolean successFlag = tdao.traineeUpdate(traineeVO);

		if (successFlag == true) {
			System.out.println("입력처리 성공");
		} else {
			System.out.println("입력처리 실패");
		}
	}
	public static void deleteManager(){
		System.out.print("삭제할 수강신청 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());
		TraineeVO tvo = new TraineeVO();
		TraineeDAO tdao = new TraineeDAO();
		tvo.setNo(no);
		boolean successFlag = tdao.traineeDelete(tvo);

		if (successFlag == true) {
			System.out.println("삭제처리 성공");
		} else {
			System.out.println("삭제처리 실패");
		}
	}

	public static void sortManager() throws SQLException, FileNotFoundException, IOException {
		TraineeDAO tdao = new TraineeDAO();
		ArrayList<TraineeVO> traineelist = null;
		traineelist = tdao.traineeSelectSort(new TraineeVO());
		
		if (traineelist.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printTraineeList(traineelist);
	}
	

	// 전체 학생리스트를 출력진행

	public static void printTraineeList(ArrayList<TraineeVO> traineeList) {
		System.out.println("============================================");
		for (TraineeVO tv : traineeList) {
			System.out.println(tv.toString());
		}
		System.out.println("============================================");
	}

	public static void printTraineeAllList(ArrayList<TraineeVO> traineeList) {
		System.out.println("============================================");
		for (TraineeVO tv : traineeList) {
			System.out.println(tv.toAllString());
		}
		System.out.println("============================================");
	}
}