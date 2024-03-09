package kr.or.ddit.basic;

// 쓰레드의 상태를 출력하는 예제

public class ThreadTest09 {

	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());
		
		th.start();
	}

}

// 쓰레드 상태의 감시 대상이 되는 쓰레드
class TargetThread extends Thread{
	
	@Override
	public void run() {
		// 반복문으로 어떤 지 확인?
		for(long i=1L; i<=20_000_000_000L; i++) {
			long k = i+1;
		}
		// 일시정지 상태 됐다가
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		// 얘 작업
		for(long i=1L; i<=20_000_000_000L; i++) {
			long k = i+1;
		}
		// 이과정을 1번 진행? ㅎ.ㅎ?
	}
}

// TargerThread의 상태값을 구해서 출력하는 쓰레드
class StatePrintThread extends Thread{
	// 참조값을 알면 된다.
	// 참조변수를 만들어서 참조하게 하면 된다.
	
	private TargetThread target;

	// 생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) {
			// TargetThread의 상태값 구하기
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);
			
			// 쓰레드의 상태가 NEW상태이면...
			if(state == Thread.State.NEW) { 
				target.start();
			}
			// 쓰레드의 상태가 종료 상태이면...
			if(state == Thread.State.TERMINATED) {
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
	
}