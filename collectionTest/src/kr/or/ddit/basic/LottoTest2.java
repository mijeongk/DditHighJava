package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
 *  로또를 구매하는 프로그램 작성하기
 
 사용자는 로또를 구매할 때 구매할 금액을 입력하고
 입력한 금액에 맞게 로또번호를 출력한다.
 (단, 로또 한장의 금액은 1000원이며 최대 100장까지만 구입할 수 있고,
      거스름돈도 계산하여 출력한다.)

	==========================
         	Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	 2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 2500  <-- 입력
			
	행운의 로또번호는 아래와 같습니다.
	로또번호1 : 2,3,4,5,6,7
	로또번호2 : 20,21,22,23,24,25
			
	받은 금액은 2500원이고 거스름돈은 500원입니다.

	==========================
         	Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	 2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 900  <-- 입력
	
	입력 금액이 너무 적습니다. 로또번호 구입 실패!!!

	==========================
         	Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	 2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 101000  <-- 입력
	
	입력 금액이 너무 많습니다. 로또번호 구입 실패!!!
			
   	 ==========================
         	Lotto 프로그램
	--------------------------
	  1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 2  <-- 입력
		
	감사합니다
 */
public class LottoTest2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        final int LOTTO_PRICE = 1000; // 로또 한 장의 가격
        final int MAX_LOTTO_COUNT = 100; // 최대 구매 가능한 로또 장수

        System.out.println("==========================");
        System.out.println("\t\tLotto 프로그램");
        System.out.println("--------------------------");
        System.out.println(" 1. Lotto 구입");
        System.out.println(" 2. 프로그램 종료");
        System.out.println("==========================");

        while (true) {
            System.out.print("메뉴 선택 : ");
            int menu = scan.nextInt();

            if (menu == 1) {
                System.out.println("\nLotto 구입 시작");
                System.out.println("\n(1000원 로또번호 한줄)");

                System.out.print("금액 입력 : ");
                int amount = scan.nextInt();

                if (amount < LOTTO_PRICE) {
                    System.out.println("\n입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
                } else if (amount > LOTTO_PRICE * MAX_LOTTO_COUNT) {
                    System.out.println("\n입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
                } else {
                    int lottoCount = amount / LOTTO_PRICE; // 구매한 로또 장수
                    int change = amount % LOTTO_PRICE; // 거스름돈

                    System.out.println("\n로또번호");

                    for (int i = 1; i <= lottoCount; i++) {
                        int[] lotto = new int[6];

                        for (int j = 0; j < 6; j++) {
                            lotto[j] = random.nextInt(45) + 1;
                        }

                        Arrays.sort(lotto);

                        System.out.print("로또번호" + i + " : ");
                        for (int num : lotto) {
                            System.out.print(num + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("\n받은 금액은 " + amount + "원이고 거스름돈은 " + change + "원입니다.");
                }
            } else if (menu == 2) {
                System.out.println("\n감사합니다.");
                break;
            } else {
                System.out.println("\n잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

        scan.close();
    }
}
