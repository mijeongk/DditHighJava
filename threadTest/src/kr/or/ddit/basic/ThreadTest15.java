package kr.or.ddit.basic;

public class ThreadTest15 {

	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		TestThread th1 = new TestThread("1번 쓰레드", sObj);
		TestThread th2 = new TestThread("2번 쓰레드", sObj);
		
		th1.start();
		th2.start();
	}

}

class ShareObject{
	private int sum = 0;
	/*	동기화 처리
		방법 1 ==> 메서드에 동기화 설정하기
	 */
	// 어떤 쓰레드 사용하던지 값 10씩 증가 잘 된다.
//	public synchronized void add() { // 방법 1 ==> 메서드에 동기화 설정하기
	public void add() { // 방법 1 ==> 메서드에 동기화 설정하기
		
		synchronized (this) { // 방법2 
			// 이건 전체 묶은 거다. 객체 메소드 안에서 특정 부분만 동기화할 때 이런거 쓰면 된다.

			int n = sum;

			n += 10; // 10씩 증가

			sum = n;

			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}	
	
}

class TestThread extends Thread {
	private ShareObject sObj;

	// 생성자
	public TestThread(String name, ShareObject sObj) {
		// 위getName 활용하려고 쓴 것임
		super(name); // 쓰레드의 name 설정 (super은 부모클래스 아래 있는 것들 사용하려고)  
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			sObj.add();
		}
	}
	
}