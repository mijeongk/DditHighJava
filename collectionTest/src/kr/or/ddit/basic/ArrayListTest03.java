package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 문제2) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중에서
 * 		 별명의 길이가 제일 긴 별명을 출력하는 프로그램을 작성하시오.
 * 		(단, 입력할 때 각 별명의 길이를 다르게 입력한다.
 * 		 작성 클래스명 : ArrayListTest03)
 */
public class ArrayListTest03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("5명의 별명을 입력하시오. 단, 별명의 길이는 다르게 입력하시오.");

		ArrayList<String> aliasList = new ArrayList<String>();

		for (int i = 1; i <= 5; i++) {
			System.out.print(i + "번째 별명 입력 >> ");
			String alias = scan.nextLine();
			aliasList.add(alias);
		}
		
		// 제일 긴 별명이 저장될 변수를 선언하고, List의 첫번째 데이터로 초기화 한다.
		// 첫번째가 제일 길다고 가정하고 시작한다
		String maxAlias = aliasList.get(0);
		
		// 0번째 안하는 이유는? 이미 위에서 썼음
		for(int i=1; i<aliasList.size(); i++){
			if(maxAlias.length() < aliasList.get(i).length()) {
				maxAlias = aliasList.get(i);
			}
		}
		
		System.out.println();
		System.out.println("제일 긴 별명 : " + maxAlias);
		
	}

}
