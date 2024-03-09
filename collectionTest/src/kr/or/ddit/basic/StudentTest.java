package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
 * 		이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화처리를 한다.
 * 		
 * 		이 Student 객체는 List에 저장하여 관리한다.
 * 
 * 		List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현한다.
 * 		그리고 총점의 역순(내림차순)으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬하는 외부 정렬 기준 클래스를
 * 		작성하여 정렬된 결과를 출력하시오.
 * 		(등수는 구하지 않아도 됨...)
 */
public class StudentTest {
	
	// 등수를 구하는 메서드 // 매개변수 필요
	public void createRank(List<Student> stdList) {
		
		for(Student std1 : stdList) { // 기준 데이터를 구하기 위한 반복문 // std1
			int rank = 1; // 처음에는 1등으로 설정해 놓고 시작한다.
			
			for(Student std2 : stdList) { // 비교 대상을 나타내는 반복문
				// 기준보다 비교 대상이 큰 값ㅇ르 만나면 rank값을 증가시킨다.
				if(std1.getTotal() < std2.getTotal()) {
					rank++;
				}

			} // 비교 for문 끝..
			
			std1.setRank(rank);	// 구해진 등수를 기준 객체에 저장한다.
			
		} // 기준 for문 끝..
	}
	
	public static void main(String[] args) {
		ArrayList<Student> stdList = new ArrayList<Student>();

		stdList.add(new Student(1, "홍길동", 100, 99, 98));
		stdList.add(new Student(3, "이순신", 70, 60, 55));
		stdList.add(new Student(7, "칠순이", 89, 99, 65));
		stdList.add(new Student(2, "성춘향", 89, 99, 65));
		stdList.add(new Student(5, "강감찬", 79, 91, 78));
		stdList.add(new Student(4, "일지매", 100, 41, 98));
		stdList.add(new Student(6, "변학도", 100, 98, 98));

		/*
		 * 
		 * void 메소드는 정적 메소드(인스턴스 메소드) 
		 * static 객체를 생성핮 ㅣ않곧도 생성할 수 있는 것
		 * instance  new 명령어로 객체 생성하면 만들어지는 것 
		 * (객체 생성했을 때 만들어지는 것이 인스턴스다)
		 */
		
		// StudentTest의 객체가 만들어져야만 사용할수 있는것이 ㅇ인스턴스 메서드? createRank?
		// 등수 구하는 메서드 호출하기
		StudentTest stdTest = new StudentTest(); // 이거 이용해서 호출할 수 있음 
		stdTest.createRank(stdList);
		
		System.out.println("정렬 전...");
		for (Student std : stdList) {
			System.out.println(std);
		}

		System.out.println("-------------------------------------------------------------------------------------");

		// 학번의 오름차순으로 정렬하기 (내부 정렬 기준으로 정렬하기)
		Collections.sort(stdList);
		
		System.out.println("학번의 오름차순 정렬 후...");
		for (Student std : stdList) {
			System.out.println(std);
		}
		
		System.out.println("-------------------------------------------------------------------------------------");
		
		Collections.sort(stdList, new SortByTotal());
		
		System.out.println("총점 내림차순으로 정렬 ...");

		for (Student std : stdList) {
			System.out.println(std);
		}
		
		System.out.println("-------------------------------------------------------------------------------------");

	}
}

/*
 * 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
 * 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화처리를 한다.
 * 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현한다.
 */
class Student implements Comparable<Student> {
	private int stuNum;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank;

	
	// total, rank 제외하고 만들면 된다.
	public Student(int stuNum, String name, int kor, int eng, int math) {
		super();
		this.stuNum = stuNum;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		// 파라미터에 넣지 않고 total 만들 수 있음
		this.total = kor+eng+math;
	}

	public int getStuNum() {
		return stuNum;
	}
	
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "StudentGrade [stuNum=" + stuNum + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", total=" + total + ", rank=" + rank + "]";
	}

	// 학번을 오름차순으로 정렬되게 하는 기준 만들기
	@Override
	public int compareTo(Student std) {
		// 정적메소드 객체 생성 안해도 활용 가능
		// compareTo 이거 안에서는 compare 활용해서 객체 생성 안해도 활용 가능?
		return Integer.compare(this.getStuNum(), std.getStuNum());
	}

}

// 총점의 역순(내림차순)으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬하는 외부 정렬 기준 클래스
class SortByTotal implements Comparator<Student>{

	@Override
	public int compare(Student std1, Student std2) {
		
		if(std1.getTotal() == std2.getTotal()) {
			return std1.getName().compareTo(std2.getName());
		}else {
			
			// compareTo 활용하려고 쓴 것이다. 인티져 만들어야한다 객체 생성해야만 쓴다.
			return new Integer(std1.getTotal()).compareTo(std2.getTotal()) * -1;
			// 이 밑에 껄 합쳐서 한줄로 쓴 것이다.
//			Integer k = new Integer(std1.getTotal());
//			return k.compareTo(std2.getTotal());
		}
	}
	
}
