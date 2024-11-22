package com.kh.subjectMVCProject.main;

import com.kh.subjectMVCProject.contorller.LessonRegisterManager;
import com.kh.subjectMVCProject.contorller.StudentRegisterManager;
import com.kh.subjectMVCProject.contorller.SubjectRegisterManager;
import com.kh.subjectMVCProject.contorller.TraineeRegisterManager;
import com.kh.subjectMVCProject.view.LESSON_CHOICE;
import com.kh.subjectMVCProject.view.MENU_CHOICE;
import com.kh.subjectMVCProject.view.MenuViewer;
import com.kh.subjectMVCProject.view.SUBJECT_CHOICE;
import com.kh.subjectMVCProject.view.TRAINEE_CHOICE;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class SubjectMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int no;
		while (true) {
			try {
				MenuViewer.mainMenuView();
				no = Integer.parseInt(sc.nextLine());

				switch (no) {
				case MENU_CHOICE.SUBJECT:
					subjectMenu();
					break;
				case MENU_CHOICE.STUDENT:
					studentMenu();
					break;
				case MENU_CHOICE.LESSON:
					lessonMenu();
					break;
				case MENU_CHOICE.TRAINEE:
					traineeMenu();
					break;
				case MENU_CHOICE.EXIT:
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
				return;

			}
		}

	}

	private static void traineeMenu() {
		int no;
		TraineeRegisterManager trm = new TraineeRegisterManager();
		MenuViewer.studentMenuView();
		no = Integer.parseInt(sc.nextLine());
		switch (no) {
		case TRAINEE_CHOICE.INSERT:
			System.out.println("");
			// srm.insertManager();
			break;
		case TRAINEE_CHOICE.UPDATE:
			System.out.println("");
//            studnetManager.studnetUpdate();
			break;
		case TRAINEE_CHOICE.LIST:
			System.out.println("");
//            studnetManager.studnetTotalList();
			break;
		case TRAINEE_CHOICE.DELETE:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}

	}

	private static void lessonMenu() {
		int no;
		MenuViewer.studentMenuView();
		no = Integer.parseInt(sc.nextLine());
		LessonRegisterManager lrm = new LessonRegisterManager();
		switch (no) {
		case LESSON_CHOICE.INSERT:
			System.out.println("");
			// srm.insertManager();
			break;
		case LESSON_CHOICE.UPDATE:
			System.out.println("");
//            studnetManager.studnetUpdate();
			break;
		case LESSON_CHOICE.LIST:
			System.out.println("");
//            studnetManager.studnetTotalList();
			break;
		case LESSON_CHOICE.DELETE:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}

	}

	private static void studentMenu() {
		int no;

		// StudentRegisterManager studnetManager = new StudentRegisterManager();
		MenuViewer.studentMenuView();
		no = Integer.parseInt(sc.nextLine());
		StudentRegisterManager srm = new StudentRegisterManager();
		switch (no) {
		case SUBJECT_CHOICE.INSERT:
			System.out.println("");
			// srm.insertManager();
			break;
		case SUBJECT_CHOICE.UPDATE:
			System.out.println("");
//            studnetManager.studnetUpdate();
			break;
		case SUBJECT_CHOICE.LIST:
			System.out.println("");
//            studnetManager.studnetTotalList();
			break;
		case SUBJECT_CHOICE.DELETE:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}

	}

	// 학과정보메뉴
	private static void subjectMenu() throws SQLException, IOException {
		int no;

		// StudentRegisterManager studnetManager = new StudentRegisterManager();
		MenuViewer.studentMenuView();
		no = Integer.parseInt(sc.nextLine());
		SubjectRegisterManager srm = new SubjectRegisterManager();
		switch (no) {
		case SUBJECT_CHOICE.INSERT:
			System.out.println("");
			srm.insertManager();
			break;
		case SUBJECT_CHOICE.UPDATE:
			System.out.println("");
//	            studnetManager.studnetUpdate();
			break;
		case SUBJECT_CHOICE.LIST:
			System.out.println("");
//	            studnetManager.studnetTotalList();
			break;
		case SUBJECT_CHOICE.DELETE:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}

	}
}
