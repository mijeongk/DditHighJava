package kr.or.ddit.basic;

// yield()메서드 연습

public class ThreadTest10 {

	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 쓰레드");
		YieldThread th2 = new YieldThread("2번 쓰레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println("-----------------111111-----------------");
		
		th1.work = false;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		System.out.println("-----------------222222-----------------");
		
		th1.work = true;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println("-----------------333333-----------------");
		
		th1.stop = true;
		th2.stop = true;
		
		
		
	}

}

// yield()메서드 연습용 쓰레드
class YieldThread extends Thread{ // String 받는 쓰레드? ㅎ.ㅎ?
	
	public boolean stop = false;
	public boolean work = true;
	
	// 생성자
	public YieldThread(String name) {
		// super 부모의 생성자 호출 
		super(name); // 쓰레드의 이름을 설정한다. 
	}
	
	@Override
	public void run() {
		while(!stop) {
			
			if(work) {
				System.out.println( getName() + "작업 중...");
			}else {
				// 조건 만족할때 일하지만 조건 불만족하면 일안할때 (ex: 공회전 확률 높아질때) 이렇게 양보하기
				System.out.println( getName() + "양보...");
				Thread.yield();
			}
		}
	}
	
}