package kr.or.ddit.basic;

public class ArgsTest {
	/*
		매개 변수로 받은 정수들의 합계를 구하는 메서드 만들기
		(이 정수의 개수는 상황에 따라 다를 수 있다.)
	 */
	
	
	// 배열을 이용한 메서드
	// static 안붙어있는 것 호출하려면? 소속되어있는 클래스 ... => 메인 메서드 확인
	public int sumArr(int[] data) {
		// 데이터 몇개인지 모르지만 (int[] data에 값이) 이미 있다 생각하고 작업해야한다.
		int sum = 0;
		
		for(int i = 0; i < data.length; i++) {
			sum+=data[i];
		}
		
		return sum;
	}
	/*
		가변인자 ==> 메서드의 인자의 개수가 실행될 때마다 다를 때 사용한다.
				     가변인자는 메서드 안에서는 배열로 처리된다.
				     가변인자는 메서서드에서는 한 개만 사용할 수 있다. 
	 */
	// 가변인자를 이용한 메서드
	public int sumArg(int...data) { // 정수형 데이터를 가변인자로 받겠다. data 변수에 배열로 저장됨
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		return sum;
	}
	
	/*
		가변 인자와 일반적인 매개변수를 같이 사용할 경우에는
		가변 인자를 제일 뒤쪽에 배치해야 한다.
	 */
	public String sumArg2(String name, int...data) { // (일반 매개변수, 가변형 변수)
		int sum = 0;
		
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		
		return name + "의 합계 = " + sum;
	}
	
	
	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();
		
		int[] numArr = {100, 200, 300};
//		int[] numArr;
//		numArr = {100, 200, 300}; // 초기화 불가
		
		System.out.println(test.sumArr(numArr));
		
		int[] arr2 = new int[] {10, 20, 30};
//		int[] arr2;
//		arr2 = new int[] {10, 20, 30}; // 초기화 가능
		
		// 1, 2, 3, 4, 5, 6, 7, 8, 9의 합계를 메서드를 호출하여 구하시오.
		System.out.println(test.sumArr(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}));
		System.out.println();
		
		System.out.println(test.sumArg(100, 200, 300));
		System.out.println(test.sumArg(1, 2, 3, 4, 5, 6, 7, 8, 9));
		System.out.println();

		System.out.println(test.sumArg2("홍길동", 10, 20, 30, 40));
		
	}
	
}
