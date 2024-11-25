package com.kh.java.view;

public class PlayerMenu {
	public static void printMenu() {
		String menu1 = "입력";
		String menu2 = "수정";
		String menu3 = "삭제";
		String menu4 = "출력";
		String menu5 = "선수확인";
		String menu6 = "경기추가";
		String menu7 = "종료";
		String info1 = String.format("1. %s\n", menu1);
		String info2 = String.format("2. %s\n", menu2);
		String info3 = String.format("3. %s\n", menu3);
		String info4 = String.format("4. %s\n", menu4);
		String info5 = String.format("5. %s\n", menu5);
		String info6 = String.format("6. %s\n", menu6);
		String info7 = String.format("7. %s\n", menu7);
		System.out.println("====선수 메뉴====\n"+info1+info2+info3+info4+info5+info6+info7+"==============");
	

}
}
