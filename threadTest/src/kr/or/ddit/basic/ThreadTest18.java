package kr.or.ddit.basic;

/*
	wait(), notify()를 이용해서 두 쓰레드가 번갈아 한번씩 실행되는 예제
 */
public class ThreadTest18 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();

		ThreadA th1 = new ThreadA(workObj);
		ThreadB th2 = new ThreadB(workObj);
		
		th1.start();
		th2.start();
	}

}

// 공통으로 사용할 class
class WorkObject{
	public synchronized void methodA() {
		System.out.println("methodA() 메서드 실행...");
		
		// wait 먼저 들어오면 a 메소드 먼저 실행될 때 wait 들어오고 b도 wait로 들어가면 깨워줄 애가 없다.
		// 그러니 notify먼저 쓰기다.
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB() 메서드 작업 중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
	}
	
}

// WorkObject의 methodA() 메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodA();
		}
		// 여기 안에 있는 웨이팅풀 // 이 명령 넣으면 b wait 풀릴 수 있지만 오류 난다.
		// 왜? 동기화 영역에서만 활용될 수 있는 notify다.
		// 그래서 동기화 영역 만들어주기
		synchronized (workObj) {
			workObj.notify();
		}
		
	}
	
}

// WorkObject의 methodB() 메서드만 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	// 마지막 b i가 10일때 a깨워주고 락 풀어주고 b가 wait에 들어갔는데 안깨워줘서 아직 실행중이다. 
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodB();
		}
		synchronized (workObj) {
			workObj.notify();
		}
	}
	
}
