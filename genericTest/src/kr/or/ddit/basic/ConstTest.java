package kr.or.ddit.basic;

// 상수만 관리하는 클래스
public class ConstTest {
	/*
		static 안붙이면 post라는 객체 사용해야만 사용 가능. 보통 static 많이 씀.
		final 없으면 변수명이다. 있어서 상수명이다. RED
		상수는 반드시 초기화를 해야한다. 이 값은 더이상 변동이 안된다.
		쓰는 이유
		예로 색깔 관련된 상수 쓴다고 치자.
		
		상수 쓰는 목적
		위 색깔 할 때 쓰는것
		아래 카운트 할 때 쓰는 것
		
	 */
	public final static int RED = 1;
	public final static int GREEN = 2;
	public final static int BLUE = 3;
	
	public final static int ONE = 1;
	public final static int TWO = 2;
	public final static int THREE = 3;
	
	
}
