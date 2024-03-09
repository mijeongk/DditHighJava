package kr.or.ddit.basic;

// 제네릭을 사용하지 않는 class
class NonGeneric{
	private Object value;
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
}

/*
	- 제네릭 클래스 만드는 방법
	
	형식)
	class 클래스명<제네릭타입글자>{
		public 제네릭타입글자 변수명; // 변수 선언에 제네릭을 사용한 경우
		...
		...
		
		// 메서드의 반환값 타입으로 제네릭을 사용한 경우
		public 제네릭타입클자 메서드명(매개변수들...){ 
			...
			return 반환값;
		}
		
		// 매개변수의 타입으로 제네릭을 사용한 경우
		public 반환값타입 메서드명(제네릭타입글자 변수명, ...){
			... 
			... 
		}
	}
	
	- 제네릭 타입 글자로 많이 사용되는 것들
	  T ==> Type
	  K ==> Key
	  v ==> Value
	  E ==> Element
 */

// Generic을 적용하는 class 만들기
class MyGeneric<T> { // 제네릭타입글자 영대문자로 1개의 알파벳 쓰면됨
	private T value;
	
	public T getValue() {
		return value;
	}
	
	public void setValue (T value) {
		this.value = value;
	}
}

public class GenericTest {
	
	public static void main(String[] args) {
		NonGeneric non1 = new NonGeneric();
		non1.setValue("안녕하세요.");

		NonGeneric non2 = new NonGeneric();
		non2.setValue(123);
		
		// 실제 저장은 object 라는 것으로 포장 되어서 된 것입니다. 그 포장을 벗기려면 형 변환 해야함.
		String str1 = (String) non1.getValue();
		System.out.println("문자열 반환 값 : " + str1);
		
		int temp1 = (int) non2.getValue();
		System.out.println("정수형 반환 값 : " + temp1);
		// 이런 작업 할 때 문제점
		// 잘못 판단해서 문자열인데 인트형이라 생각해서 형변환 잘못 쓸 수 있음
		// 근데 문법적으로는 문제 없지만 실행하고오류 남
//		int temp2 = (int) non1.getValue(); // 형변환에서 오류...
		// 어떤 경우 실행하면 실행 되는데 결과가 다를 때도 있다. (찾기 어려운 에러..ㅠㅠ)
		
		System.out.println("---------------------");
		
		MyGeneric<String> my1 = new MyGeneric<String>();
		
		my1.setValue("우리나라");
//		my1.setValue(123);// 제네릭과 다른  종류의 데이터를 저장할 수 없다.
		
		MyGeneric<Integer> my2 = new MyGeneric<>(); // new의 <>안 Integer 생략가능
		my2.setValue(500);
		
		String str2 = my1.getValue(); // 데이터를 꺼내올 때 형 변환 없이 사용할 수 있다.
		int temp2 = my2.getValue();
		
		System.out.println("Generic 문자열 반환값 : " + str2);
		System.out.println("Generic 정수형 반환값 : " + temp2);
		
	}
	
}
