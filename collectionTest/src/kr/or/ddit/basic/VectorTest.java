package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
		
		// 객체 생성
		Vector v1 = new Vector(); 
		/*
		 * 내부적으로 데이터 저장할 때 배열 사용한다. (배열: 크기 조절 안됨)
		 * 보통 10개정도 있는데 11번째 데이터 들어갈 때 
		 * 백터가 현재보다 2배 큰 공간 만듦 새로 만든 배열에 옮기고 11번쨰에 데이터 넣음
		 * 사용자가 사용하는 데이터에 따라 알아서 공간 만들고 옮겨준다.
		 */
		
		System.out.println("처음 크기 : " + v1.size());
		
		/*
		 * 데이터 추가하기 1: add(추가할 데이터)
		 * 반환 값 : 추가 성공(true), 추가 실패(false)
		 * 기본적으로 객체만 저장 가능하다.
		 */
		
		v1.add("aaa"); // String
		// 초창기 시절
		// rapperClass(래퍼클래스) : 8가지 기본자료형은 감싸줘야함 (첫글자 대문자) 근데 다른것 Integer, Character
		// 기본자료형(String):  byte, short, int, float, doulble, long, boolean, char (객체 아님)  
		v1.add(new Integer(10));

		/*
		 * 알아서 데이터 객체화 // auto boxing, auto unboxing 기능이 있다.
		 * 현시점에서는 편하게 쓸 수 있음
		 */
		v1.add(123); // auto boxing, auto unboxing 지원 됨
		v1.add('a');
		v1.add(true);
		
		boolean r = v1.add(3.14);
		
		System.out.println("현재의 크기: " + v1.size());
		System.out.println("반환값 : " + r);
		// toString() 활용시 데이터 값 다 넣을 수 있음 하지만 없애도 똑같이 나옴
		System.out.println("v1 => " + v1.toString());
		System.out.println("v1 => " + v1);
		
		/*
		 * 데이터 추가하기 : addElement (추가할 데이터)
		 * ==> 이전버전의 프로그램과의 호환성을 위해서 남아 있는 메서드
		 */
		v1.addElement("CCC");
		System.out.println("v1 => " + v1);
		
		/*
		 * 데이터 추가하기 3 : add(index, 데이터)
		 * ==> 'index'번째에 '데이터'를 끼워 넣는다.
		 * 		원래 데이터는 한칸씩 뒤로 간다
		 * ==> 'index'는 0부터 시작한다. 반환값은 없다.
		 */
		v1.add(1, "KKKK");
		System.out.println("v1 => " + v1);
		
		/*
		 * 데이터 꺼내오기 : get(index)
		 * ==> 'index' 번째의 데이터를 꺼내서 반환한다.
		 */
		System.out.println("0번째 데이터 : " + v1.get(0));
		
		// 안되는 것 vs 되는 것
//		int iTemp = v1.get(2);
		int iTemp = (int) v1.get(2);
		System.out.println("2번쨰 데이터 : " + iTemp);
		/*
		 * 오브젝트 가져와서 ()형변환 래퍼클래스로 integer객체로 변환 이후로
		 * 인트형으로 변환한것이다...
		 * 언박싱 기능까지 들어간 것이다.
		 * 저장시는 좋았지만 데이터 떠내올때는 데이터 알아야 형변환 가능하다.
		 * 만약 형변환 잘못하면 문법적으로 문제 없지만 실제 실행하면 에러 난다.
		 */
//		String iTemp2 = (String) v1.get(2); // 형변환 잘못 된 것
		
		/*
		 * 데이터 종류가 다양하다. 아무 데이터나 저장할수 있는 것은 Object밖에 없다.
		 * 상속 되어있을 때 부모객체 변수에는 자식객체 저장가능하다
		 * 아무거나 저장할수있는데 왜 object 하냐? 제일 조상이기 떄문에
		 * 
		 * Object로 변환 되어서 저장되는 것이다. string, int 이런식으로 됐지만 실제 데이터는 변환 된 것임
		 * 형변환 따로 안해줘도 됨
		 * 
		 * but 꺼내올 때는 Object 꺼내지는 것이다.
		 * 부모의 데이터를 자식 객체에 저장하려면 형변환 해야한다. 명시적으로 우리가.
		 * 자식 데이터가 부모쪾 객체 저장할 때는 자동 형변환 한다.
		 * 
		 * 그러나  자동 형변환 안되니 우리가 해줘야함.
		 */
		
		/*
		 * 데이터 수정하기 : set(index, 새로운 데이터)
		 * ==> 'index'번째의 데이터를 '새로운 데이터'로 변경한다.
		 * ==> 반환값 : 변경되기 전 원래의 데이터로 반환
		 */
		// 반환 값 없으면 이렇게도 가능 
		v1.set(0, "zzzz");
		// 반환 값 있을 때
		String sTemp = (String) v1.set(0, "zzzz");
		System.out.println("v1 => " + v1);
		System.out.println("원래의 데이터 : " + sTemp);
		System.out.println();
		
		/*
		 *  데이터 삭제하기 1 : remove(index)
		 *  ==> 'index'번째의 데이터를 삭제한다.
		 *  ==> 반환값 : 삭제된 데이터
		 */
		
		// 반환값 필요 없을 때
		v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		// 반환값 필요 있을 때  (형변환 필요)
		sTemp = (String) v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		System.out.println("삭제된 데이터 : " + sTemp);
		System.out.println();
		
		/*
		 * 데이터 삭제하기 2 : remove (삭제할 데이터)
		 * ==> '삭제할 데이터'를 찾아서 삭제한다 (직접 지정)
		 * ==> '삭제할 데이터'가 여러개면 이들 중에 제일 첫번째로 찾아진 자료를 삭제한다.
		 * ==> 반환값 : 삭제성공(true), 삭제 실패 (false)
		 * ==> '삭제할 데이터'가 '정수형'이거나 ',char형'일 경우에는 
		 * 		반드시 객체로 변환해서 사용해야 한다.
		 */
		
		v1.remove("CCC");
		System.out.println("CCC 삭제 후 v1 => " + v1);
		
//		v1.remove(123); // 오류
		// 123자료를 지워야하는데 123번째를 지워라 라고 인식했음 (데이터 삭제하기 1)
		
		// 객체화 시키는 방법
//		v1.remove(new Integer(123));  // 자바 1.9 이상에서는 사용 불가 (위에 썼지만 1.8이기 때문에 실행했던 거다.)
		v1.remove(Integer.valueOf(123)); // 되도록 이렇게 쓰기  
		System.out.println("123 삭제 후 v1 => " + v1); 
		
		// 이름이 같은데 기능이 조금 다르니 메소드 오버로딩
		/*
		 * 오버 로딩: 메소드 이름만 같고 메소드 ()괄호에 들어가는 매개 변수의 갯수, 타입이 다를 떄 구별하는 방법(반환값 상관 없음)
		 * 			괄호속에 있는 매개변수로 구분함
		 * 			int형 123 데이터 삭제 데이터 1-> int
		 * 			인덱스가 아닌 데이터다 라고 알려줘야함
		 * 			클래스는 객체만 저장할 수 있다. 
		 * 			123 객체 아니다 이 데이터를 객체화 시켜서 알려주기
		 * 오버 라이딩: 부모가 가지고 있는 메소드를 내가 새롭게 '재정의'하는 것
		 */
		
//		v1.remove('a');
		// 오류 (Array index out of range: 97)
		v1.remove(Character.valueOf('a'));
		System.out.println("'a' 삭제 후 v1 => "+ v1); 
		
		v1.remove(true);
		v1.remove(3.14);
		System.out.println("삭제 후 v1 => " + v1);
		
		/*
		 * 전체 데이터 삭제하기 : clear()
		 */
		
		v1.clear();
		System.out.println("clear 삭제 후 v1 => " + v1);
		System.out.println("-------------------------------------------");
		
		/*
		 * 제네릭타입(Generic Type) 
		 * ==> 클래스 내부에서 사용할 테이터의 타입을 객체를 생성할 때
		 * 		외부에서 지정해주는 기법으로 객체를 선언할 때 괄호('< >') 안에
		 * 		그 객체의 내부에서 사용할 데이터의 타입을 정해주는 것을 말한다.
		 * 
		 * - 이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터들을 저장할 수 없다.
		 * - 이 때 제네릭으로 선언될 수 있는 데이터 타입은 '클래스형'이어야 한다.
		 * 	  그래서 int는 Integer, boolean은 Boolean, char는 Character 등으로
		 * 	  대체해서 사용해야 한다.
		 * - 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요 없다.
		 */
		
		// Integer 데이터만 저장할 수 있는 
		Vector<Integer> v2 = new Vector<Integer>(); // int형 자료만 저장할 수 있는 Vector
		Vector<String> v3 = new Vector<String>(); // String형만 저장할 수 있는 Vector
		
		v3.add("안녕하세요.");
//		v3.add(100); // 오류 : 지정한 제네릭 타입과 다른 종류의 데이터를 저장할 수 없다.
		String sTemp2 = v3.get(0); // 형변환 없이 사용할 수 있다.
		
		Vector<Vector> vv = new Vector<Vector>();
		Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>>();
		// -----------------------------------------------------------
		
		v3.clear();
		System.out.println("v3의 size : " + v3.size());
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		v3.add("FFF");
		
		Vector<String> v4 = new Vector<String>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v3 => " + v3);
		System.out.println("v4 => " + v4);
		
		/*
		 * 데이터 삭제하기 3 removeAll(Collection 객체)
		 * ==> 백터의 데이터들 주엥서 'Collection 객체'가 가지고 있는 모든 데이터들을 삭제한다.
		 * ==> 반환값 : 성공(true), 실패(false) 
		 */
		
		v3.removeAll(v4); // v3의 데이터들 중에서 v4가 가지고 있는 데이터들을 모두 삭제한다.
		System.out.println("삭제 후 v3 => " + v3);
		System.out.println("----------------------------------------");
		
		v3.clear();
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		v3.add("FFF");
		
		for (int i = 0; i < v3.size(); i++) {
			System.out.println(i + "번째 데이터 : " + v3.get(i));
		}
		System.out.println("---------------------------------------");
		
		// 향상된 for문
		// 배열, 백터, list, set 다 들어갈 수 있다. v3 자리에
		// 얘가 가지고 있는 데이터중에 1개 꺼내와서 저장, 꺼내진 데이터 종류 쓴다.
		// v3 꺼내질거 string. AAA 저장되서 다시 돌아가서 2번째 데이터 넣고 실행, ... 자동으로 해줌
		// 배열 넣으면 배열 갯수만큼 돌아간다.
		// 인덱스 값은 모르고 데이터만 출력
		// 인덱스 필요하면 첫번째 FOR문 쓰고 아니면 향상된 FOR문 쓰기
		for(String str : v3) {
			System.out.println(str);
		}
		System.out.println("---------------------------------------");
		
	}
}
