package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {

	public static void main(String[] args) {
		// 사용자로부터 데이터 입력 받기
		String str = JOptionPane.showInputDialog("아무거나 입력하세요...");
		// 입력 안하고 취소하면 null로 나옴
		System.out.println("입력한 값 : " + str);
		// 만약 입력 시간 설정하고싶어 -> 

		for(int i = 10; i>=1; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000); // 1초멈췄다 출력하고
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}

}
