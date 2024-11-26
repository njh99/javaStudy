package com.kh.java.view;

public interface MenuViewer {
	public static void mainMenuView() {
		System.out.println();
		System.out.println("선수단 확인 프로그램");
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 선수단 정보");
		System.out.println("2. 방출명단 정보");
		System.out.println("3. 이적시장 정보");
		System.out.println("4. 프로그램 종료");
		System.out.print("번호 선택 : ");
	}

	// 선수단 정보 메뉴
	public static void PlayerMenuView() {
		String menu1 = "선수등록";
		String menu2 = "선수정보 수정";
		String menu3 = "선수방출";
		String menu4 = "출력";
		String menu5 = "선수확인";
		String menu6 = "경기추가";
		String menu7 = "메뉴";
		String info1 = String.format("1. %s\n", menu1);
		String info2 = String.format("2. %s\n", menu2);
		String info3 = String.format("3. %s\n", menu3);
		String info4 = String.format("4. %s\n", menu4);
		String info5 = String.format("5. %s\n", menu5);
		String info6 = String.format("6. %s\n", menu6);
		String info7 = String.format("7. %s\n", menu7);
		System.out
				.println("====선수 메뉴====\n" + info1 + info2 + info3 + info4 + info5 + info6 + info7 + "==============");

	}
	// 방출 정보 메뉴
	public static void releasesPlayerMenuView() {
		String menu1 = "수정";
		String menu2 = "삭제";
		String menu3 = "출력";
		String menu4 = "메뉴";
		String info1 = String.format("1. %s\n", menu1);
		String info2 = String.format("2. %s\n", menu2);
		String info3 = String.format("3. %s\n", menu3);
		String info4 = String.format("4. %s\n", menu4);
		System.out
				.println("====선수 메뉴====\n" + info1 + info2 + info3 + info4  + "==============");

	}
	public static void FaPlayerMenuView() {
		String menu1 = "이적시장 수정";
		String menu2 = "선수 삭제";
		String menu3 = "이적시장 출력";
		String menu4 = "메뉴";
		String info1 = String.format("1. %s\n", menu1);
		String info2 = String.format("2. %s\n", menu2);
		String info3 = String.format("3. %s\n", menu3);
		String info4 = String.format("4. %s\n", menu4);
		System.out
				.println("====선수 메뉴====\n" + info1 + info2 + info3 + info4  + "==============");

	}
}
