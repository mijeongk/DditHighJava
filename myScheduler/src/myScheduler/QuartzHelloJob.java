package myScheduler;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzHelloJob implements Job {

//	public HelloJob() {
//	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Hello World! - " + new Date());
	}
	// Quartz는 특정 작업(Job)을 예약하고 실행하기 위한 기능을 제공하는데, 
	// JobExecutionContext는 이 작업 실행 컨텍스트에 대한 정보를 담고 있습니다.

}

