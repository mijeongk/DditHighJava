package myScheduler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerSchedule {

	public static void main(String[] args) {
		ScheduledJob job = new ScheduledJob();
		Timer jobScheduler = new Timer();

		// 지정한 작업, 지정한시간부터, 일정간격
		jobScheduler.schedule(job, 1000, 3000);

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jobScheduler.cancel();
	}

}

class ScheduledJob extends TimerTask {
	@Override
	public void run() {
		System.out.println("문자전송 - " + new Date());
	}
}