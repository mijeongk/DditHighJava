package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {
	
	public static void main(String[] args) {
		// 실행시켜주는 것: 자바의 가상머신 JVM 
		/*
		 * 자바 특징: 
		 * 프로그램은 다른 운영체제에서 돌려도 돌아간다.
		 * 왜? 자바 가상머신이 있기 때문에 다른 프로그램에서도 돌릴 수 있음
		 * 운영체제 바뀌면 안돌아가는데 자바는 가능(자바의 가상머신 JVM 있어서)
		 * 
		 * 자바 가상머신이 자바프로그램 실행하기 위해 만들어 놓은 메모리 영역 있다.
		 * 알고있어야할 메모리 3가지
		 * Method 영역
		 * - 클래스 정보, static 이 주로 저장 된다.
		 * - 예를 들어서 클래스 이름, 맴버 타입, 명 저장 -> 데이터 값은 콜스택, 힙영역에서 !!!!
		 * 
		 * CallStack 영역
		 * - 메소드를 호출할 때마다 메소드 호출 저장
		 * 
		 * Heap 영역
		 * - 객체를 생성하면 (ex- new Person();) 이 영역에 저장된다.
		 * 
		 * 실행시킬 때 예시
		 * Method 영역에서 
		 * - EqualsHashCodeTest class 정보를 저장해놓는다. (읽어옴)
		 * - main() 명령이 들어감 (저장)
		 * - 이후 저장함 시작메소드가 메인메소드가 있는지 검사
		 * - 자바 가상 머신이 메인 메소드 자동으로 호출해줌
		 * 
		 * CallStack 영역
		 * - 메인 메소드 프레임 잡힌다
		 * - 뭐가 저장되나? 메인 메소드에 있는 매개변수 (args: [ ]), 지역변수도 만들어짐 
		 *   (메소드마다 프레임 잡힘)
		 *   처음에는 args: null로 들어가겠지
		 * - 다시 main 다서 실행???
		 * 
		 * Person p1 = new Person(); 처음 실행
		 * - 퍼슨 클래스 찾아서  퍼슨 클래스 다시 method 영역에 저장!
		 * - 퍼슨 정보(변수, 메소드 등 정보)
		 * - p1이라는 변수 만들어짐 (메인의 지역변수) -> Callstack 메인메소드 프레임에 생성
		 * new 그러면 객체 생성 person()클래스 정보를 가지고 heap에 객제 생성해라
		 * 
		 * 힙영역에 person에 num, name 변수, 메서드들 만들어짐
		 * 변수들 값이 어떻게 들어가나?
		 * 힙영역에 만들어지고나서 Person() 호출, new 있으면 생성자 
		 * 
		 * 생성자 안만들면 어떻게 되나? 컴파일러가 기본생성자를 만들어 준다
		 * (매개변수 없는 생성자 = 기본생성자)
		 * public Person(){
		 * } ==> 이게 기본생성자 실행할게 없긴해 호출 끝 그러고  new Person(); 작업 완료
		 * 
		 * 힙영역의 만들어진것 시작 번지ㄱ ㅏ메모리에 어디 저장되어있을 것이다. 주소
		 * 번지를 반환한다 new 번지가 p1에 저장한다. 할당함() 
		 * Person p1 = new Person();
		 * 
		 * callstack에서 p1 의 주소가 들어있음 그게 힙영역의 주소를 가르킴
		 * 
		 * p1.setNum(1); => 원래 p1 번지 있는데 . 있으면 p1을 가르키는 영역(힙)에 있는 setNum을 사용해라
		 * setNum 메소드면 메소드를 호출해라.라는 뜻
		 * 그러면 callstack에 setNum()프레임이 생긴다.
		 * 
		 * 자동으로 만들어지는 것 매개변수  num 만들어짐 콜스택 메모리에 만들어지고
		 * 다시 왔어 setNum(힙)
		 * 
		 * callstack의 setNum(프레임에 자동으로 this
		 * this 매개변수 -> 자기자신 의미 메소드 안에는 기본, 자동으로 만들어. setNum의 주소가 자동으로 들어감
		 * 				-> this.num . (점은 가르키는 곳의  num에...)
		 * 			num 매개변수 callstack에 1값 넣으면 힙영역의 넘에 1을 넣어라
		 * 			다 되면 setNum이 삭제 된다. 콜스택에서 없어짐 그러면 밑 프레임으로 또 감
		 * 다시 돌아감
		 * 다음의 p1.setName("홍길동"); 이거 호출
		 * 
		 * setNum자리dp setName 프레임 생김
		 * this.name = name -> 콜스택 -> 힙영역 들어간상태.. ㅠㅠㅠ 어렵네 ㅠㅠ 또 다하면 삭제됨!
		 * 
		 * 
		 * this()메소드 -> 다른거 호출하고 싶을 때도  사용
		 * 생성자에서 자기자신 호출할 때 
		 * 
		 * Person p2 = new Person();
		 * 또 지역변수 p2가 만들어진다 메인메소드에
		 * new Persion(); -> 그전과 똑같은 person구조, 과정 거침 힙영역에 주소
		 * 콜스택의 메인메소드에 p2 주소 저장
		 * 
		 * 실행되면  p2.setNum(1); 힙영역에 1, 홍길동 저장
		 * 
		 * p3 은 p1 있는데 주소 들어있지 그러면 p3 = 100번지 이렇게 들어가는것이다 (p1=1번지였으니까)
		 * 
		 * 콜스택: 메인은 4개 만들어지고 p1, p2, p3, args
		 * 힙영역 2개 만들어짐 p1, p2
		 * 
		 */
		Person p1 = new Person();
		// 
		p1.setNum(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setNum(2);
//		p2.setName("일지매");
		p2.setNum(1);
		p2.setName("홍길동");
		
		Person p3 = p1;
		// == 값이 같아도 주소가 다르면 false 출력 (주소값으로 비교한다)
		System.out.println(p1 == p2);
		System.out.println(p1 == p3);
		System.out.println();
		
		System.out.println(p1.equals(p2));
		System.out.println(p1.equals(p3));
		System.out.println();
		
		/*
		 * 여기의 equals는 Object에 있는 equals를 활용한 것이다.
		 * equals는 == 처럼 비교한다 (참조 값을 비교=주소값)
		 * SYSO 위, 아래 같은 식임
		 * 
		 * BUT String으로 비교하면 주소가 달라도 값이 같으면 true 반환 왜?
		 * 오버라이딩(재정의)해놓음 (String에서 데이터 같으면 true로 나오도록 재정의)
		 * (원래 Object == 비교있는 스트링 객체 다시 만듦)
		 * (재정의 안하면 object의 == 활용하는 것과 같다)
		 * 
		 * 만약 바꾸고 싶을 때는? ex) Person에 재정의하기!
		 * 
		 * 오버라이딩: 부모객체가 가지고 있는 메서드를 새롭게 다시 만들 수 있음
		 */
		
		HashSet<Person> testSet = new HashSet<Person>();
		testSet.add(p1);
		testSet.add(p2);
//		testSet.add(p3); // p3과 p1와 같이 때문에 넣어도 +1 안됨
		System.out.println("Set의 개수 : " + testSet.size());
		System.out.println();
		// 
		/*
		 * set에 중복데이터 안들어가니까 동일하면 1개 나올 것 같지만 아니다 2개다
		 * 왜? 같지 않다고 set에서 판정했으니까
		 * 데이터만 같을 때는 equals만 재정의하면된다 
		 * 특히 HashS 단어 들어가는 객체 있다.
		 * 이러한 객체들은 안의 데이터의 같냐 틀리냐 비교할 떄 equals가지고만하는게 아니라
		 * hashcode도 활용해서 비교한다
		 * 
		 */
		System.out.println("hashCode 값");
		System.out.println("p1 hash : " + p1.hashCode());
		System.out.println("p2 hash : " + p2.hashCode());
		System.out.println("p3 hash : " + p3.hashCode());
		System.out.println();
		System.out.println("참조 값"); // 16진수로 표현된 것임
		System.out.println("p1 : " + p1);
		System.out.println("p2 : " + p2);
		System.out.println("p3 : " + p3);
		System.out.println();
		
		/*
		 * hashCode 객체 구별하는 값
		 * 사람으로 치면 일종의 주민번호와 같다
		 * 참조값을 기반으로 해쉬코드 만든다
		 * 참조값 같으면 해쉬코드도 같다
		 * 
		 * hashCode 가끔 똑같은 값 나올 경우도 있다
		 * 이 가능성이 낮음 (확률적으로 중복될 수 있지만 낮게 만들진 값)
		 * 혹시나 같으면 equals도 같이 비교하기 때문에 진짜 같은지 또 확인하는 것임
		 * 
		 */
	}
	
}

//	class 부모클래스 1개만 상속받을 수 있음 (단일 상속) 
//	상속 받으려면 extends 부모클래스 한다.
//	class Person extends 부모클래스{

//	인터페이스는 여러개 상속 가능
//	class Person extends 부모클래스 implements 인터페이스들{

//	모든 클래스들은 기본적으로 Object 상속 받는다. 생략된 것이다 (오브젝트에 있는 메서드다)
//	class Person extends Object{
	class Person{
	private int num;
	private String name;
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	/*
	 * super 자기 부모
	 * super() ? 있나?
	 */
	@Override
	// 재정의 방법
	public boolean equals(Object obj) { 
		// this 현재 객체 (this 메소드 호출하면 p1.equals(p2) 여기 중 p1 가르킴)
		if(this == obj) { // 참조값이 같다 
			return true;
		}
		
		if(obj == null) {
			return false;
		}
		
		// obj가 실제 어떤 것인지 알아야함 person이니까 다 person 이어야한다?
		if(this.getClass() != obj.getClass()) { // 같은 유형의 클래스인지 검사 (ex: person 인데 member 이면 flase
			return false;
		}
		// 참조값이 같을 땐 true. 참조값이 다르고 null 아니고 같은 유형의 클래스이면~
		Person that = (Person) obj; // 매개변수의 값을 현재 객체 유형으로 형변환 한다.
		
		// Objects.equals(객체1, 객체2) ==> 객체1과 객체2가 같으면 true 반환
		return this.num == that.num && Objects.equals(this.name, that.name);
		
		// 지금 super의 부모는 object다 
//		return super.equals(obj);
		
	}
	
//	@Override
//	public int hashCode() {
//		return Objects.hash(num, name); // 변수 더 있으면 ()에 추가해주면 된다.
//	}
	
}