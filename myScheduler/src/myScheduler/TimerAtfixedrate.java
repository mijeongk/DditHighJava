package myScheduler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerAtfixedrate {

   public static void main(String[] args) {
      ScheduledJob1 job = new ScheduledJob1();
      // Timer는 TimerTask를 사용하여 작업을 스케줄링하고 실행하는 역할
      Timer jobScheduler = new Timer();

      //  Timer 클래스의 schedule() 메서드를 사용하여 작업을 스케줄링할 수 있습니다. 
      // 지정한 작업, 일정 시간이 지난 후, 일정 간격
      
      // 지금 -> 1초 후에 작업을 시작하고, 3초 간격으로 작업을 반복 실행하는 스케줄링
      jobScheduler.scheduleAtFixedRate(job, 1000L, 3000);
      //시작점 : 그 시간(1초 후) 시작, 3초 간격으로 작업을 반복 실행하는 스케줄링
//      jobScheduler.schedule(job, 1000L, 3000);
      
      // Timer는 주어진 시간 간격으로 작업을 실행하며, TimerTask에서 정의한 작업은 run() 메서드 내부에서 수행됩니다. 
      // Timer를 사용하여 주기적인 작업을 스케줄링할 수 있습니다.

      // 20초가 지난 후에 jobScheduler.cancel()이 호출되어 작업 스케줄링이 취소되면서 작업이 멈추게 됩니다.
      try {
         Thread.sleep(20000); // 20초 동안 대기 - 위 스케쥴러가 돌아감
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      
      // jobScheduler.cancel()은 Thread.sleep(20000) 이후에 호출되고 있기 때문에, 
      // 20초가 지난 후에 작업 스케줄링이 취소됩니다.
      jobScheduler.cancel();

   }

}

// TimerTask는 추상 클래스로, 반복적으로 실행할 작업을 정의하는 역할
class ScheduledJob1 extends TimerTask {
   // TimerTask를 상속받은 클래스에서는 run() 메서드를 오버라이드하여 원하는 작업을 구현합니다. 
   // 이 작업은 Timer에 의해 주기적으로 실행
   @Override
   public void run() {
      System.out.println("메일 전송 - " + new Date());
   }
}