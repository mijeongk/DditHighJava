package kr.or.ddit.homework;

import java.util.Timer;
import java.util.TimerTask;

public class QuartzExample {
	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					System.out.println("Hello!");
				}
			}
		};
		
		timer.schedule(tt, 1000, 3000);
		
		try {
			Thread.sleep(10000); // 20초 동안 대기 - 위 스케쥴러가 돌아감
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		timer.cancel();
	}

}
