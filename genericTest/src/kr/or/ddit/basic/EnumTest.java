package kr.or.ddit.basic;

/*
	enum(열거형)	==> 서로 관련 있는 상수들의 집합을 나타낸다.
				==> 클래스처럼 보이게 하는 상수
	- 작성 방법
	1) 클래스처럼 독립된 java파일에 만드는 방법
	2) 하나의 java파일에 클래스와 같이 만드는 방법
	3) 클래스 내부에 내부 클래스처럼 만드는 방법
	
	- 열거형 속성 및 메서드
	1) 열거형변수.name()		==> 열거형 상수의 이름을 문자열로 반환한다.
	2) 열거형변수.ordinal()	==> 열거형 상수가 정의된 순서값(index값)을 반환한다. (0부터 시작)
	3) 열거형이름.valueOf("열거형상수명")		==> 인수값에 지정한 '열거형상수명'과
											일치하는 열거형 상수를 반환한다.
	4) 열거형이름.상수명		==> 열거형이름.valueOf("상수명")와 같다.
	5) 열거형이름.values()		==> 모든 상수들을 배열로 가져온다.
	
	----------------------------------------------------------------------------
	
	- 열거형 선언하기
	방법1)
		enum 열거형이름 {상수명1, 상수명2, 상수명3, ... }
	방법2)
		enum 열거형이름 { 
			상수명1(값들1, ...), // 여러개 지정 가능
			상수명2(값들2, ...),
			...
			상수명n(값들n, ...);
			
			// 상수선언할때 괄호에 넣어둔 값들 
			// '값들'이 저장될 변수를 선언한다.
			// 값들 들어간 순서의 타입이 같아야함???
			private 자료형이름 변수명1; // 이 변수명의 개수는 위 괄호속 값들 개수와 동일하다.
			private 자료형이름 변수명2;
			...
			
			// 열거형의 생성자를 작성한다.
			// ==> 열거형의 생성자는 '열거형상수'에 '값들'을 셋팅하는 역할을 수행한다.
			// ==> 열거형의 생성자의 접근 제한자는 묵시적으로 'private'이다.
			
			private 열거형이름(자료형이름 변수명, ...){
				위에 선언된 변수명들을 초기화한다.
				... 
			}
			
			// 위에 선언한 변수들을 외부에서 불러올 수 있는 getter 메서드를 작성한다.
			...
		}
			
 */
public class EnumTest {
	// 3) 클래스 내부에 내부 클래스처럼 만드는 방법 + 열거형 선언하기 방법1)
	public enum Color {RED, GREEN, BLUE}
	public enum Count {ONE, TWO, THREE}
	
	public enum Season{
		// 기간, 온도
		봄("3월부터 5월까지", 13),	// 상수명(값들, ...) 형식의 선언
		여름("6월부터 8월까지", 26), 
		가을("9월부터 11월까지", 15), 
		겨울("12월부터 2월까지", 1);
		
		// 값들이 저장될 변수 선언
		private String span;
		private int temp;
		
		// 생성자 (private 생략 가능)
		Season(String months, int temp){  // private Season(String months, int temp){ 
			span = months; // 다르면 저장하면 되고
			this.temp = temp; // 같으면 이렇게 하믄된다.
		}
		
		// getter 메서드 (값 꺼내쓰기 위해서 쓴 것임)
		public String getSpan() {
			return span;
		}
		
		public int getTemp() {
			return temp;
		}
		
	}
	
	public static void main(String[] args) {
		
		/*
		System.out.println("RED : " + ConstTest.RED);
		System.out.println("Three : " + ConstTest.THREE);
		
		// 상수 같다라고 나오는 결과가 문제다..
		if(ConstTest.ONE == ConstTest.RED) {
			System.out.println("같다...");
		}else {
			System.out.println("같지 않다...");
		}
		*/
		/*
			데이터 타입이 같으니 비교 가능.
			논리적으론 이상하지만 컴퓨터 입장은 아니다.
			사람 입장에서는 논리적으로 만든 목적 다른데 같게 나오니.. 그럼 안되는뎅...
			비교가 잘못되어서 결과 잘못 나올 수 있다.
			이런 경우 에러가 더 찾기 어렵다.
			
			해결법: 상수도 사용 목적에 맞게 관리할 수 있도록 기법 제공한다. --> 열거형
		 */
		// 3) 열거형이름.valueOf("열거형상수명") 상수명을 문자열로 지정하여 GREEN 상수 가져와라?
		Color mycol = Color.valueOf("GREEN"); // Color.GREEN 과 같다.
		Count mycnt = Count.ONE; // Count.valueOf("One") 과 같다.
		
		System.out.println("mycol의 name : " + mycol.name());
		System.out.println("mycnt의 name : " + mycnt.name());
		System.out.println();
		
		System.out.println("mycol의 ordinal : " + mycol.ordinal());
		System.out.println("mycnt의 ordinal : " + mycnt.ordinal());
		System.out.println();
		
		// 아까와 같은 논리적 오류 생기지 않음
//		if(Color.RED == Count.ONE) { // 다른 종류의 열거형끼리는 비교가 불가능하다.
			
//		}
		
		if(mycol == Color.BLUE) { // 같은 종류의 열거형끼리만 비교가 가능하다.
			System.out.println("같다...");
		}else {
			System.out.println("같지 않다...");
		}
		System.out.println();
		
		System.out.println("---------------");
		
		Season ss = Season.봄;
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("span : " + ss.getSpan());
		System.out.println("temp : " + ss.getTemp());
		System.out.println();
		
		for(Season time : Season.values()) {
			System.out.println(time.name() + " == " + time 
					+ " ==> " + time.getSpan() + ", " + time.getTemp());
		}
		System.out.println();
		
		// 열거형을 switch문에서 비교하기
		// case 옆에는 '상수명'만 기술해야 한다.
		switch(mycol) {
//			case Color.RED : break; // case 열거형이름.상수명 : ==> 이것은 사용 불가
			case RED :
				System.out.println("RED 입니다.."); break;
			case GREEN :
				System.out.println("GREEN 입니다.."); break;
			case BLUE :
				System.out.println("BLUE 입니다.."); break;
		}
		
	}
	
}
