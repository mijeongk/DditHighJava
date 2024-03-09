package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * - 정렬과 관련된 interface는 Comparable, Comparator 이렇게 두가지가 있다.
 * 
 * - Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 때 구현하는 인터페이스이다.
 * 	 (내부 정렬 기준을 만들 때 사용하는 interface)
 * - Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때 구현하는 인터페이스이다.
 * 	 (외부 정렬 기준을 만들 때 사용하는 interface)
 * 
 * - Comparable를 구현할 때는 comparTo()메서드를 재정의하고,		// 반환값 int
 *   Comparator를 구현할 때는 compare()메서드를 재정의 해야 한다.	// 반환값 int
 *   
 * - String 클래스, Wrapper 클래스, Data 클래스, File 클래스 등에는 '내부 정렬 기준'이 구현되어 있다.
 *   (내부 정렬 기준은 보통 오름차순으로 처리되도록 구현되어 있다.)
 * 
 */

public class ListSortTest01 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("이순신");
		list.add("변학도");
		list.add("강감찬");
		list.add("나희도");
		list.add("다라랑");
		
		// 추가한 순서대로 데이터가 나온다.
		System.out.println("정렬 전 >> " + list);
		
		/*
		 * 정렬은 Collections.sort() 메소드를 이용하여 정렬한다.
		 * (참고 : 배열은 Array.sort() 메서드를 이용한다.)
		 * 이 sort() 메서드는 기본적으로 '내부 정렬 기준(오름차순)'으로 정렬한다.
		 * 
		 * '내부 정렬 기준' : 리스트 추가 
		 * 스트링 클래스의 정렬 기준이 이미 들어 있음()
		 * 스트링의 내부정렬기준으로 정렬한다 이미 만들어서 제공하는 것들임 (오름차순 기준)
		 */
		
		Collections.sort(list);
		
		System.out.println("정렬 후 >> " + list); // 내부 정렬 기준, 오름차순
		
		Collections.shuffle(list); // 자료 섞기
		
		System.out.println("자료 섞기 후 >> " + list);
		
		/*
		 * 외부 정령  기준 클래스를 적용해서 정렬하기
		 * 형식) Collections.sort(리스트, 외부정렬클래스의 인스턴스);
		 */
		Collections.sort(list, new Desc());
		System.out.println("내림차순 정렬 후 >> " + list);
		
	}

}


// 정렬 방식을 정해주는 class 작성하기('외부 정렬 기준 클래스' 작성하기)
// ===> Comparator 인터페이스를 구현해서 작성한다.
// 인터페이스 구현하려면 임플리먼츠 사용 // 스트링으로 구현할 것
class Desc implements Comparator<String>{

	// 구현해야 할 메소드이다.
	// compare()메서드를 이용해서 정렬하고자 하는 기준을 정해준다.
	// ==> 이 메서드의 매개변수는 서로 인접한 데이터라고 생각하면 된다.
	
	/*
	 *  compare()메서드의 반환값(int?)
	 *  두개 데이터 비교했는데 그 순서를 바꿀지 그대로 둘지
	 *  반환값이 '0'일 때	==> 두 값이 같다
	 *  반환값이 '양수'일 때	==> 앞, 뒤의 순서를 바꾼다.
	 *  반환값이 '음수'일 때	==> 앞, 뒤의 순서를 바꾸지 않는다. 
	 */
	
	// 예) 오름차순일 경우	==> 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수
	@Override
	// 정렬해야할 것 1 5 2 7 => 2개 가지고 비교한다. 이 때 앞에 있는 변수가 str1, 뒤에 있는 변수가 str2
	public int compare(String str1, String str2) {
		// 내림차순으로 구현하기...
		// 오름차순 기준으로 했을 때 결과 양수이다, (양수일경우 반환값 있어야 함 왜?)
		// 하지만 내림차순으로 할 것이디 음수로 바꿔주기다 
//		if(str1.compareTo(str2) > 0) {
//			return -1;
//		}else if(str1.compareTo(str2) < 0) {
//			return 1;
//		}else {
//			return 0;
//		}
		return str1.compareTo(str2) * -1;
	}
	
}