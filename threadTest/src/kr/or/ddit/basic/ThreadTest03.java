package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		// 스레드가 수행되는 시간을 체크해 보자...
		// 스레드가 시작하는 시간과 끝나는 시간을 적어서 빼준다.
		Thread th = new Thread(new MyRunner02());
		
		// 1970년 1월 1일 0시 0분 0초부터 경과한 시간을 밀리세컨드(1/1000)단위로 반환
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join(); // 현재 위치에서 대상이 되는 쓰레드(변수 th)가 끝날 때까지 기다린다.
		} catch (InterruptedException e) {
			
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간 : " + (endTime - startTime));
		// 스레드 작동 먼저 작동 되고 경과시간 나와야하는데 반대네?
		// 엄밀히 얘기하면 th.start(); 처리한 시간밖에 안된다.
		// start 스레드는 실행할 환경 콜스택 만들고 만들어진 콜스택에서 런 실행할수있도록 함
		// 우리가 원하는 시간 (위와 같은 실행이다)은 스레드가 다 처리가된 후의 시간이다.
		// 그러면 start 시작하고 스체드 끝날때까지 기다려줘야함 끝나면 그떄 endTime 실행되어야 한다.
		// 사이에 th.join(); 명령어 추가되어야함
	}

}


class MyRunner02 implements Runnable{
	@Override
	public void run() {
		long sum = 0L;
		
		for(long i=1L; i <=1_000_000_000L; i++) { // 자바의 _(언더바는) 숫자 ,와 같은 것 | 값에 영향주지 않음
			sum += i;
		}
		
		System.out.println("합계 : " + sum);
		
	}
}