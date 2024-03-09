package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
 * 예제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 *      컴퓨터의 숫자는 난수를 이용하여 구한다.
 *      (스트라이크는 S, 볼은 B로 출력한다.)
 *      
 *      컴퓨터의 난수 ==> 9 5 7
 *      
 * 실행 예시)
 *         숫자입력 >> 3 5 6
 *         3 5 6 ==> 1S, 0B
 *         숫자입력 >> 7 8 9
 *         7 8 9 ==> 0S, 2B
 *         숫자입력 >> 9 7 5
 *         9 7 5 ==> 1S, 2B
 *         숫자입력 >> 9 5 7
 *         9 7 5 ==> 3S 0B
 *         
 *         축하합니다. 당신은 4번째만에 맞췄습니다.
 */

public class BaseBallTest_self {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		/*
		 * SET 활용하여 컴퓨터의 난수 랜덤으로 3개 받고 저장(중복 제거)
		 * 
		 * SCAN으로 입력값 3개 받기
		 * 
		 * 컴퓨터와 입력자의 것 비교
		 * 자리, 숫자 일치하면 S, 숫자만 일치하면 B
		 */
		
		HashSet<Integer> num = new HashSet<Integer>();
		
		while(num.size() < 3) {
			int result = (int) (Math.random() * (9-1-1)+1);
			num.add(result);
		}
		
//		System.out.println("컴퓨터의 난수 : " + num);
		
		ArrayList<Integer> aList = new ArrayList<Integer>();
		
		System.out.println("숫자 야구 게임");
		System.out.println("숫자 3개를 입력하세요.");
		aList.add(scan.nextInt());
		aList.add(scan.nextInt());
		aList.add(scan.nextInt());
		
		// 스트라이크, 볼 개수, 시도횟수 객체 생성
		int strikeCnt = 0;
		int ballCnt = 0;
		int cnt = 0;
		
		while(true) {
			
			strikeCnt = 0;
			ballCnt = 0;
			
			// 반복문으로 기존 데이터와 입력받은 데이터를 비교한다.
			for (int i = 0; i < 3; i++) {
				
				// 컴퓨터 난수에 입력된 값이 포함되는 경우
				if (num.contains(aList.get(i))) { 
					
					// 인덱스가 일치하는 경우 스트라이크
					if (num.contains(aList.get(i)) && num.toArray()[i] == aList.get(i)) {
						strikeCnt++;
					} else { // 인덱스가 일치하지 않는 경우 볼
						ballCnt++;
					}
				}
				
			}
			
			cnt++;
			
			System.out.println("입력 >> " + aList);
			System.out.println("결과 >> " + strikeCnt + "S" + ballCnt + "B");
			
			if(strikeCnt == 3) {
				break;
			}
			
			System.out.println("숫자 3개를 입력하세요.");
			aList.set(0,  scan.nextInt());
			aList.set(1,  scan.nextInt());
			aList.set(2,  scan.nextInt());
		}
		
		System.out.println("축하합니다. 당신은 " + cnt + "번째만에 맞췄습니다.");
		System.out.println("답 >>  " + num);
		
	}
	
}
