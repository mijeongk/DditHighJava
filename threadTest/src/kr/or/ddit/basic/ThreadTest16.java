package kr.or.ddit.basic;
/*
계좌관리 프로그램 있다.
통장있으면 거기에 예금을 입출금 기능있다 치자.
여러사람 동시에 사용할 수 있으니 쓰레드 사용한다.
통장 하나 있는데 가족들이 같이 쓴다 치자
형동생이 대전, 서울 각자 사는데 예로 동시에 통장의 돈을 출금했다.
통장 = 10,000원 | 동시에 6,000원 출금 버튼 눌렀다. 그 중 미세하게 빠른 사람 먼저 처리
동생이 빨랐다. 출금 처리 메소드 있겠지.
출금 처리 메소드 만들 때 출금 금액을 가지고 메서드 들어온것이다.
잔액과 비교해야한다. 
if문으로 출금금액, 잔액 비교해서 크거나 같으면 if 실행하고
잔액 - 출금 => 잔액 성공하면 돈 나오게 만듦.
만약 이 작업 전에 제어가 형에게 넘어갔다. 
그러면 똑같이 출금 금액 가져와서 if 비교하면 참이 된다. 10,000원 그대로다.
둘다 if문 안으로 들어온다. 다시 동생으로 가면 10,000-6,000 되었으니 4,000원 되고 나갔다.
-통장아니면 출금 되면 안되지.
이것 방지하는 것이 동기화처리이다.  
 
*/

// 은행의 입출금을 쓰레드로 처리하는 예제

public class ThreadTest16 {
	private int balance; // 잔액이 저장될 변수
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금을 처리하는 메서드
	public void deposit(int money) {
		// 입금 금액 받기 = 잔액에 추가하기
		balance += money;
	}
	
	// 출금을 처리하는 메서드(반환값 : 출금 성공(true), 출금 실패(false)
//	public boolean withdraw(int money) { // 이걸로 하면 동시에 빼갈 수 있음
//	public synchronized boolean withdraw(int money) { // 방법1 이걸로 출금시 if문 조건에 부합하지 않으면 동시에 불가능하게함
	public boolean withdraw(int money) { // 이걸로 출금시 if문 조건에 부합하지 않으면 동시에 불가능하게함
		
		synchronized (this) { // 방법 2 객체에...

			if (balance >= money) {

				// 다른 스레드 넘어가기 쉽게 하려고??
				for (int i = 1; i < 1000000000; i++) {
					int k = i + 1;
				} // 시간 지연용

				balance -= money;
				System.out.println("메서드 안에서 balance = " + balance);
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadTest16 acount = new ThreadTest16();
		acount.setBalance(10000); // 잔액을 10000원으로 설정
		
		// 익명 구현체로 쓰레드 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				boolean result = acount.withdraw(6000); // 6000원 출금하기
				System.out.println("쓰레드에서 result = " + result
						+ ", balance = " + acount.getBalance());
			}
		};
		// -------------------------------------------------------------
		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r);
		
		th1.start();
		th2.start();
		
	}

}
