package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {
	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-0000-0000"));
		memList.add(new Member(5, "이순신", "010-1111-1111"));
		memList.add(new Member(9, "성춘향", "010-2222-2222"));
		memList.add(new Member(3, "강감찬", "010-3333-3333"));
		memList.add(new Member(6, "일지매", "010-4444-4444"));
		memList.add(new Member(2, "변학도", "010-5555-5555"));
		
		System.out.println("정렬 전...");
		// 추가한 순서대로 나옴
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------------");
		
		/*
		 * 정렬 하려는데 오류 
		 * 내부 정렬 기준으로 하는데 내부정렬기준 안만들었으니 오류 나왔다
		 * 2가지로 해결할 수 있음
		 * 외부정렬기준만들어서 적용시키거나(내부정렬없어도 됨)
		 * 내부정렬기준넣거나 
		 */
		Collections.sort(memList);

		System.out.println("정렬 후...");
		// 내부 정렬 넣었음
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------------");
		
		Collections.sort(memList, new SortNumDesc());
		
		System.out.println("num값의 내림차순으로 정렬...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------------");
		
		
	}
}

/*
 * Member의 num값의 내림차순으로 정렬하는 외부 정렬 기준 클래스를 이용하여 정렬하시오.
 * (클래스명 : SortNumDesc)
 */
class SortNumDesc implements Comparator<Member>{
	
	@Override
	public int compare(Member mem1, Member mem2) {
//		if(mem1.getNum() > mem2.getNum()) {
//			return -1;
//		}else if(mem1.getNum() < mem2.getNum()) {
//			return 1;
//		}else {
//			return 0;
//		}
		
		// Wrapper 클래스를 이용하는 방법1 // 부호 바꾸는 방법 (if문 활용할 수 있지만 ..)
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;

		// Wrapper 클래스를 이용하는 방법2
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
	}
	
}

/*
 * Member클래스의 '회원 이름'을 기준으로 오름차순이 되도록 내부 정렬 기준을 추가하기
 * ==> Comparable 인터페이스를 구현한다.
 */
class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;
	
	// 생성자 alt+shift+s (Constructor using fields)
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 데이터 비교 1개가지고 못하지 2개로 비교해야하는데 
	// 자기 자신과 매개변수 비교 (this와 매개변수?)
	// 회원 이름의 오름차순 정렬 기준
	@Override
	public int compareTo(Member mem) {
//		if(this.getName().compareTo(mem.getName()) > 0) {
//			return 1;
//		}else if(this.getName().compareTo(mem.getName()) < 0) {
//			return -1;
//		}else {
//			return 0;
//		}
		// 위의 것과 같다
		return this.getName().compareTo(mem.getName());
	}
	
}