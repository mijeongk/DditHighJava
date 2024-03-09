package myScheduler;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("Hello!");
				}
			}
		};
		timer.schedule(tt, 5000, 5000);
	}

	
	// 모듈화 특정기능별로 묶은 것이다.
}

