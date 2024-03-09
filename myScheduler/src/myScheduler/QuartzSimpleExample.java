package myScheduler;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzSimpleExample {

   public void run() throws Exception {

      System.out.println("-------------- 프로그램 시작 --------------");

      // SchedulerFactory 및 Scheduler 인스턴스를 생성
      SchedulerFactory sf = new StdSchedulerFactory(); // SchedulerFactory의 인스턴스를 생성합니다
      Scheduler sched = sf.getScheduler(); // SchedulerFactory를 통해 새로운 Scheduler 인스턴스를 받아옵니다

      // 현재 시간에서 반올림한 시간을 runTime으로 선언
      Date runTime = evenMinuteDate(new Date()); // 현재 시간에서 다음 분으로 반올림 한 시간을 계산합니다.

      // HelloJob class를 이름과 그룹을 지정해서 선언한다.

      // JobDetail: 작업에 대한 상세 정보를 저장합니다. Job 인스턴스가 아닌 작업의 정의를 담고 있으며, JobBuilder를 통해
      // 생성됩니다
      JobDetail job = newJob(QuartzHelloJob.class).withIdentity("job1", "group1").build();

      // HelloJob 클래스의 새 작업 인스턴스를 만듭니다
      // withIdentity("job1", "group1") - 작업의 이름과 그룹을 지정합니다

      // runTime을 적용해서 Job이 언제 시작할지 정해주고 , 트리거 이름과 그룹 이름 선언
      // Trigger: 작업이 언제 실행될지 결정하는 메커니즘입니다. TriggerBuilder를 사용하여 생성되며, 
      // 시작 시간, 반복 간격, 반복 횟수 등을 커스터마이징 할 수 있습니다.

      // newTrigger() - 새로운 트리거 인스턴스를 만듭니다
      // startNow() - 트리거가 생성된 직후 작업을 시작하게 합니다. 
      //심플 트리거  - 특정 시간 및 횟수
      Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startNow()
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

      // SimpleScheduleBuilder: 간단한 스케줄을 구성하는데 사용되는 클래스입니다. 반복 주기나 반복 횟수를 쉽게 설정할 수 있게
      // 해줍니다.
      // withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
      // - 작업을 5초 간격으로 무한 반복하도록 스케줄을 설정합니다.

      // scheduler에 있는 job을 trigger 에 따라 실행하도록 추가.
      sched.scheduleJob(job, trigger);

      // 스케줄러 시작
      sched.start();

      System.out.println("-------------- 스케줄러 시작 --------------");

      // 30초동안 메인쓰레드를 중지하여 작업실행
      try {
         Thread.sleep(30 * 1000);
      } catch (Exception e) {
         e.printStackTrace();
      }

      // 스케줄러 종료
      sched.shutdown(true);
      System.out.println("-------------- 스케줄러 종료 --------------");
   }

   public static void main(String[] args) throws Exception {
	   QuartzSimpleExample example = new QuartzSimpleExample();
      example.run();
   }
}