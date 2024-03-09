package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {
	
	public static void main(String[] args) {
	/*
	 * - List와 Set의 차이점
	 * 1. List
	 *    - 데이터의 순서(index)가 있다.
	 *    - 중복되는 데이터를 저장할 수 있다. 
	 * 2. Set
	 *    - 데이터의 순서(index)가 없다.
	 *    - 중복되는 데이터를 저장할 수 없다.*** 여러가지 활용할 수 있음
	 *    
	 *    예시) 중복되는 데이터를 저장할 수 없다. 주소 추적할 때?
	 *         우리반 25명. 번호표 나눠주고 3명 추첨
	 *         번호 중에 어떤 데이터가 나올지 몰라야한다. 
	 *         랜덤 (난수 이용 - 컴퓨터가 인위적으로 한 것이기 때문에 뭐나올지 모름)
	 *         그 다음에 난수 이용하면 또 앞에 뽑았던 것 나올 수 있으니
	 *         이럴 때 추첨한 번호 이미 만들어진 번호를 안만들어지게 하려면 SET을 이용
	 *         난수값 만들어서 SET에 저장. 
	 *         첫번째 데이터 삽입, 
	 *         두번째 데이터 이미 있는 것은 SET에 저장 안됨, ....
	 */
		
		HashSet hs1 = new HashSet();
		
		System.out.println("Set의 개수 : " + hs1.size());
		System.out.println();
		
		// Set에 데이터를 추가할 때도 add()메서드를 사용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
//		System.out.println("Set의 데이터들 >> " + hs1.toString());
		System.out.println("Set의 데이터들 >> " + hs1);
		System.out.println("Set의 데이터들 >> " + hs1.size());
		System.out.println();
		// 출력할 때 순서가 없다. (추가한 순서대로 나오지 않음) 
		
		// Set에 중복되는 데이터를 추가하면 false를 반환하고 데이터는 추가되지 않는다.
		// 성공 true, 실패 false
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 반환 값 >> " + isAdd);
		System.out.println("Set의 데이터들 >> " + hs1);
		System.out.println();

		isAdd = hs1.add("CC");
		System.out.println("중복될 때 반환 값 >> " + isAdd);
		System.out.println("Set의 데이터들 >> " + hs1);
		System.out.println();
		
		// set(index, 새로운데이터)
		
		/*
		 * Set의 데이터르 수정하려면 수정하는 명령(메소드)가 따로 없기 때문에
		 * 해당 자료를 삭제한 후 새로운 데이터를 추가해 주는 방법을 사용한다.
		 * 
		 * 삭제하는 메서드 : remove(삭제할자료) ==> 반환값 : 삭제성공(true), 삭제실패(false)
		 * 				 clear() ==> 전체 삭제
		 * 
		 */

		// 예) "FF" 데이터를 "EE"로 변경하기
		System.out.println("수정하는 메서드 없어서 삭제 후, 추가");
		hs1.remove("FF");
		System.out.println("삭제 후 set 데이터 >> " + hs1);
		hs1.add("EE");
		System.out.println("추가 후 set 데이터 >> " + hs1);
		System.out.println();
		
//		hs1.clear();
//		System.out.println("clear 후 set 데이터 >> " + hs1);
		
		/*
		 * Set의 데이터들은 순서(index)가 없기 때문에 List처럼 index로 데이터를 하나씩 꺼내올 수 없다.
		 * 그래서 데이터를 하나씩 차례로 얻기 위해서는 Iterator형 객체로 변환해야 한다.
		 * 
		 * - Set형의 데이터를 Iterator형 객체로 변환하는 메서드 ==> iterator()
		 * 
		 * 이터레이터 순서대로 나온다는 보장 없지만 데이터 삽입한 순서대로 기억 된다??
		 */
		
		Iterator it = hs1.iterator(); // Set 데이터를 Iterator로 변환하기
		
		/*
		 * Iterator의 hasNext()메서드
		 * ==> Iterator의 포인터가 가리키는 곳의 다음번째에 데이터가 있는 지 검사한다.
		 * ==> 반환값 : 다음번째에 데이터가 있으면 true, 없으면 false
		 */
		
		while(it.hasNext()) {
			/*
			 * Iterator의 next()메서드
			 * ==> Iterator의 포인터를 다음번째 위치로 이동한 후 그 곳의 데이터를 반환한다.
			 */
			System.out.println(it.next());
		}
		System.out.println();
		
		// 제네릭 안써서 Object 활용
		System.out.println("향상된 for문 이용");
		for(Object obj : hs1) {
			System.out.println(obj);
		}
		System.out.println();
		
		// hastNext()로 다음 데이터 있는지 검사 후, 있으면 next()로 포인터 이동해서 값 꺼냄?
		// 마지막 데이터 꺼내와 그리고 다시 올라가서 hasnext 갔다가 데이터 없는 것 확인하고 빠져나간다
		
		/*
		 * 우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성해 보자.
		 * 번호는 1번부터 25번까지 있고, 추첨할 인원은 3명이다.
		 * 당첨번호를 출력하시오.
		 */
		
		/*
		 * 시작값부터 종료값 사이의 정수형 난수 만들기
		 * (int)(Math.random() * (종료값-시작값+1)+시작값)
		 */
		// <클래스 이름쓰기!>
		HashSet<Integer> testSet = new HashSet<Integer>();
		
		while(testSet.size()<3) {
			int num = (int) (Math.random() * (25-1+1)+1);
			testSet.add(num);
		}
		
		System.out.println("당첨자 번호 >> " + testSet);
		System.out.println();
		
		// Set유형의 자료를 List형으로 변환해서 사용하기
		/*
		 * set 데이터를 매개변수로 ()안에 넣어줄 수 있음
		 * set 데이터를 가진 List 이다
		 */
		ArrayList<Integer> testList = new ArrayList<Integer>(testSet);
		System.out.println("List 데이터 출력하기...");
		for(int i=0; i<testList.size(); i++) {
			System.out.println(testList.get(i));
		}
		
	}
}
