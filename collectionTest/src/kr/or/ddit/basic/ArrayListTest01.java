package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {
	public static void main(String[] args) {
		
		// ArrayList의 기본 사용법은 Vector와 같다.
		ArrayList list1 = new ArrayList();
		
		// add()메서드를 이용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('K');
		list1.add(true);
		list1.add(123.45);
		
//		System.out.println("list1 = > " + list1.toString());
		System.out.println("list1 = > " + list1);
		System.out.println("list1 = > " + list1.size());
		System.out.println();
		
		// get()메서드를 애용해서 데이터를 꺼내온다.
		System.out.println("1번째 자료 : " + list1.get(1));
		
		// 데이터 끼워넣기도 같다.
		list1.add(3, "zzz");
		System.out.println("list1 => " + list1);
		
		// 데이터 변경하기
		// 제네릭 안썼으니 형변환 해줘야함
		String sTemp = (String)list1.set(3, "YYY");
		System.out.println("list1 => " + list1);
		System.out.println("sTemp => " + sTemp);
		
		// 삭제도 같다.
		list1.remove(3);
		System.out.println("3번째 자료 삭제 후 list1 => " + list1);
		
		list1.remove("bbb");
		System.out.println("bbb 자료 삭제 후 list => " + list1);
		
		// 제네릭을 사용할 수 있다.
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		// 다입 다른 것 넣으면 오류
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + "번째 자료 : " + list2.get(i));
		}
		System.out.println("-------------------------------------");
		
		for(String str : list2) {
			System.out.println(str);
		}
		System.out.println("-------------------------------------");
		
		/*
		 * 백터는 스레드에서 자동으로 ... 무겁다. 
		 * 리스트는 스레드에서 ... 가볍다.
		 */
		
		/*
		 * contains(비교 데이터)
		 * ==> List에 저장된 데이터 중에서 '비교데이터'가 있으면
		 * 	   true, 없으면 false를 반환한다.
		 */
		System.out.println("DDD값의 존재 여부 : " + list2.contains("DDDD"));
		System.out.println("DDD값의 존재 여부 : " + list2.contains("ZZZZ"));
		System.out.println("-------------------------------------");
		
		/*
		 * - indexOf(비교데이터)
		 * - lastIndexOf(비교데이터)
		 * ==> List 안에 '비교데이터'가 있으면 '비교데이터'가 저장된 index값은 반환하고
		 * 		없으면 -1을 반환한다.
		 * ==> indexOf()메서드는 앞에서부터 뒤쪽으로 검색하고
		 * 		lastIndexOf()메서드는 뒤에서부터 앞쪽으로 검색한다.
		 * ==> List 안에 검색된 '비교데이터'가 여러개면 첫번째로 검색된 데이터의 위치값을 반환한다.
		 */
		
		System.out.println("list2 = > " + list2);
		
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		System.out.println("list2 = > " + list2);
		
		System.out.println("DDDD의 위치 값 : " + list2.indexOf("DDDD")); // 3
		// 찾으면 뒤 자료에서는 안찾는다.
		System.out.println("ZZZZ의 위치 값 : " + list2.indexOf("ZZZZ")); // -1
		// 없는 데이터니 -1 반환
		System.out.println("DDDD의 위치 값 : " + list2.lastIndexOf("DDDD")); // 8
		System.out.println("-------------------------------------");
		
		/*
		 *  - toArray() ==> List 안의 데이터를 배열로 변환해서 반환한다.
		 *  			==> 기본적으로 Object형 배열로 변환한다. 
		 *  - toArray(new 제네릭타입[0]) ==> 제네릭 타입의 배열로 변환해서 반환한다.
		 */
		
		// 배열 변환할 때도 string 배열에 넣으면 될 것같은데 왜 object냐?
		Object[] strArr = list2.toArray();
//		String[] strArr = (String[]) list2.toArray(); // 오류 (이 방법 사용 불가) // 아래와 같이 하기
		// (문법상으로 문제 없지만 실행x) cannot be cast to [Ljava.lang.String;
		
		System.out.println("list2의 개수 : " + list2.size());
		System.out.println("배열의 개수 : " + strArr.length);
		
		for (int i = 0; i < strArr.length; i++) {
			System.out.println(i + "번째 자료 : " + strArr[i]);
		}
		System.out.println("-------------------------------------");
		
		for (int i = 0; i < strArr.length; i++) {
			String ss = (String) strArr[i];
//			System.out.println(i + "번째 자료 : " + strArr[i]);
			System.out.println(i + "번째 자료 : " + ss);
			// 아래와 차이없어보이지만 string 안했으면 변환하고 해야할 것이다.
		}
		System.out.println("-------------------------------------");
		
		// 제네릭 타입의 배열로 변환해서 가져오기
		String[] strArr2 = list2.toArray(new String[0]);
		for(String s : strArr2) {
			System.out.println(s);
		}
		System.out.println("-------------------------------------");
		
	}
	
}
