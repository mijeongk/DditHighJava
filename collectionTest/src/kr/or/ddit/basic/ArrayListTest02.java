package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 문제1) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에
 * 		이 ArrayList에 저장된 데이터들 중에서 
 * 		'김'씨 성의 이름을 모두 출력하는 프로그램을 작성하시오.
 * 		(단, 입력은 Scanner 객체를 이용한다.)
 */

public class ArrayListTest02 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
		
		System.out.println("5명의 사람을 입력하시오");
		
		ArrayList<String> nameList = new ArrayList<String>();
		
		for(int i = 1; i<=5; i++) {
			System.out.println(i + "번째 사람 이름 입력 >> ");
			String name = scan.nextLine();
			nameList.add(name);
		}
		
		System.out.println();
		
		
		
		System.out.println("김씨 성을 가진 이름 목록 : ");
		
		for (int i = 0; i < nameList.size(); i++) {
			// 문자열 비교
//			if(nameList.get(i).substring(0, 1).equals("김") ) {
//				System.out.println(nameList.get(i));
//			}
			// 문자 비교
//			if (nameList.get(i).charAt(0) == '김') {
//				System.out.println(nameList.get(i));
//			}
			// indexOf "김"의 위치를 찾아라 == 0 으로 두면 성으로 찾는 것과 동일
//			if(nameList.get(i).indexOf("김") == 0) {
//				System.out.println();
//			}
			// 시작글자 찾기
			if(nameList.get(i).startsWith("김")) {
				System.out.println(nameList.get(i));
			}
			
		}

		System.out.println("--------------------");
		System.out.println("김씨 성을 가진 이름 목록:");
		for (String name : nameList) {
			if (name.startsWith("김")) {
				System.out.println(name);
			}
		}

	}
	
}
