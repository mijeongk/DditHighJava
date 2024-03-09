package kr.or.ddit.basic;


/*
	객체는 참조값있으면 어디서든 가져다 쓸 수 있다. 인스턴스?? 호출 전에??
	쓰레드도 객체 참조값 넣어주면 가져다 쓸 수 있다. ??
	
	***쓰레드에서 객체를 공통으로 사용하는 예제***
	
	원주율을 계산하는 쓰레드와
	계산된 원주율을 출력하는 쓰레드가 있다.
	
	원주율을 저장하는 객체가 필요하다.
	이 객체를 두 쓰레드에서 공통으로 사용해서 처리하면 된다.
	
	데이터 중간에 변경될 경우 -> 그러면 안됨 -> 보호막 (동기화 처리해줘야함)
	
 */
public class ThreadTest14 {

	public static void main(String[] args) {
		// 공통으로 사용할 객체 생성
		ShareData sd = new ShareData();
		
		// 쓰레드 객체를 생성하고
		// 공통으로 사용할 객체를 각각의 쓰레드에 주입한다.
		CalcPIThread cp = new CalcPIThread(sd);
		
		PrintPIThread pp = new PrintPIThread();
		pp.setSd(sd);
		
		cp.start();
		pp.start();

	}

}

// 원주율을 관리하는 클래스(공통으로 사용할 클래스)
class ShareData{
	public double result; // 계산된 원주율이 저장될 변수
	
	public boolean isOk = false; // 계산이 완료되었는지 여부를 나타내는 변수
	
}
// 원주율을 계산하는 쓰레드
class CalcPIThread extends Thread{
	private ShareData sd;

	// 외부에서 데이터 받아와서 주입한다.
	// 생성자 활용 버전
	public CalcPIThread(ShareData sd) {
		super();
		this.sd = sd;
	}
	
	@Override
	public void run() {
		/*
			원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9, ... ) * 4;
					1	-	3 +   5 -	7 +	  9 ...
					
					0		1		2	3		4
		 */
		
		double sum = 0.0;
		for(int i=1; i<1_000_000_000; i+=2) {
			if( (i/2) % 2 == 0) { // 분모를 2로 나눈 몫이 짝수인 경우
				sum += (1.0 / i);
			}else {
				sum -= (1.0 / i);
			}
		}
		sd.result = sum * 4; // 계산 완료
		sd.isOk = true;
	}
}

// 계산이 완료되면 계산된 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread{
	private ShareData sd;

	// 외부에서 데이터 받아와서 주입한다.
	// setter 활용 버전
	public void setSd(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOk==true) {
				break;
			}else {
				Thread.yield();
			}
		}
		
		System.out.println();
		System.out.println("계산 결과 : " + sd.result);
		System.out.println("PI     : " + Math.PI);
		
	}
	
}

