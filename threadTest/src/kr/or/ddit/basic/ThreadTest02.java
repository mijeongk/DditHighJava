package kr.or.ddit.basic;

public class ThreadTest02 {
	// 멀티 쓰레드 프로그램
	
	/*
		Thread를 사용하는 방법
		
		방법1
		1) Thread클래스를 상속한 class를 작성한다.
		2) 작성된 class의 인스턴스를 생성한다.(객체 생성)
		3) 생성된 인스턴스 의 start()메서드를 호출해서 실행한다.
	
		방법2-1
		1) Runnable인터페이스를 구현한 class를 작성한다.
		2) 작성된 class의 인스턴스를 생성한다.(객체 생성)
		3) Thread클래스의 인스턴스를 생성할 때 인자값으로
		   2번에서 생성한 인스턴스를 주입한다.
		4) 3번에서 생성된 Thread클래스의 인스턴스의 start()메서드를 호출해서 실행한다.
		
	 */
	
	// 현재 스레드 1개 쓴개 아니라 메인메소드도 활용했기 때문에 스레드 2개 작동중이다.
	public static void main(String[] args) {
		// 방법1-2)
		MyThread01 th1 = new MyThread01();
		// 방법1-3)
		th1.start(); // start 활용해야만 스레드가 작동된다.
//		th1.run(); // 별표 먼저 다 찍힌 후에 
		
		// 방법2-1-2)
		MyRunner01 r1 = new MyRunner01();
		// 방법2-1-3)
		Thread th2 = new Thread(r1);
		// 방법2-1-4)
		th2.start();
//		th2.run(); // 하트 찍힌다. 이건 싱글스레드처럼 작동이 되는 것이다. 그래서 run 호출x
		
		/*
			방법 2-2
			1) Runnable인터페이스의 익명 구현체를 작성한다.
			2) Thread클래스의 인스턴스를 생성할 때 1번의 익명 구현체를 주입한다.
			3) 2번의 인스턴스의 start()메서드를 호출해서 실행한다.
		 */
		
		// 원래 인터페이스 new 해서 객체 생성 못하데 할 수 있음 
		// 어떻게? 아래와 같이 중괄호 하고 안에 interface에서 선언한 메소드 구현하도록 한다
		// 익명 구현체다.
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for(int i=1; i<200; i++) {
					System.out.print("♥");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
			}
		};
		// --------------------------------------------------
		Thread th3 = new Thread(r2);
		th3.start();
		
		System.out.println("main()메서드 끝..."); // 이게 먼저 나오는데..?
		// 보통 메인메서드 끝나면 끝인데 프로그램 작동중이다.
		// 메인메소드도 스레드인데
		// 멀티스레드에서는 모든 스레드가 끝나야지 프로그램이 끝난다.
	}
	
	
}

// 방법 1-1)
class MyThread01 extends Thread{
	// run ctrl+space
	@Override
	public void run() {
		// 이 run()메서드에서는 쓰레드가 처리할 내용을 작성하면 된다.
		for(int i=1; i<200; i++) {
			System.out.print("*");
			
			// 속도 늦워서 쓰레드 과정 어떻게 되는지 보기
			try {
				// Thread.sleep(시간); ==> 주어진 시간 동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}


// 방법 2-1-1)
class MyRunner01 implements Runnable{
	@Override
	public void run() {
		// 이 run()메서드에서는 쓰레드가 처리할 내용을 작성하면 된다.
		for(int i=1; i<200; i++) {
			System.out.print("♡");

			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
}