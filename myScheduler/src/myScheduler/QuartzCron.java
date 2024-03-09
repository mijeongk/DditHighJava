package myScheduler;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.TimeZone;

import org.quartz.Trigger;

public class QuartzCron {

	// Quartz라는 Java 기반의 스케줄링 라이브러리를 사용하여 
	// Cron 표현식을 이용해 작업을 스케줄링하는 예제
	// Cron 표현식 : 스케줄링을 위해 사용되는 표현식, 특정 작업을 언제 실행할지를 나타냄, 각 필드는 공백으로 구분
	// 초 (0-59) 분 (0-59) 시 (0-23) 일 (1-31) 월 (1-12) 요일 (0-7, 일요일=0 또는 7)
	
    public static void main(String[] args) {
    	// Trigger 객체를 생성하여 작업을 스케줄링하는 부분

        // 매일 8 ~ 17시 사이 2분마다 작동
        Trigger trigger1 = newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0 0/2 8-21 * * ?")).forJob("myJob", "group1").build();

        // 매일 10시 42분에 작동
        Trigger trigger2 = newTrigger().withIdentity("trigger2", "group1")
                .withSchedule(cronSchedule("0 42 10 * * ?")).forJob("myJob", "group1").build();

        // 매주 수요일 10시 42분에 작동
        Trigger trigger3 = newTrigger().withIdentity("trigger3", "group1")
                .withSchedule(cronSchedule("0 42 10 ? * WED").inTimeZone(TimeZone.getTimeZone("America/Los_Angeles")))
                .forJob("myJob", "group1").build();

    }

}

/*
	Cron 표현식은 스케줄러 시간을 세팅할 때 사용된다.
	필드는 총 7개이며, 연도는 생략가능하다.
	<초> <분> <시> <일> <월> <요일> <년>
	
	필드					허용되는 값				허용되는 특수문자
	초	(Seconds)		0~59				, - * /
	분	(Minutes)		0~59				, - * /
	시	(Hours)			0~23				, - * /
	일	(Day of month)	1~31				, - * / L W
	월	(Month)			1~12 또는 JAN ~ DEC	, - * /
	요일	(Day of week)	0 ~ 6 또는 SUN ~ SAT	, - * / L #
	년	(Year)			1970 ~ 2099			, -  

	특수문자
	* :  모든 값
	? :  특정한 값이 없음
	- :  범위 지정
	, :  여러 값을 지정
	/ :  증가하는 값을 지정  (초기값/증가값)
	L :  마지막 값 지정 
	W :  가장 가까운 평일
	# :  몇 번째 무슨 요일인지 지정
	
	
	참고사항
	- 월과 요일의 이름은 대소문자를 구분하지 않는다. ex) MON / mon
	- 연도를 설정하지 않은 경우 연도가 자동으로 결정된다.
	  현재 날짜와 비교하여 지정 날짜가 아직 지나지 않은 경우에는 현재 연도가 설정되며,
	  날짜가 이미 지난 경우에는 다음 연도로 설정된다.
	- 일(Day of month)과 요일(Day of week) 필드는 동시에 지정될 수 없다.
	  지정하지 않는 필드에는 "?"를 세팅한다.
	- 초(Seconds) 필드에 0/15가 세팅되어 있으면 0, 15, 30, 45초를 의미하며, 
	  3/15가 세팅되어 있으면 3, 18, 33, 48초를 의미한다.
	- 일(Day of month) 필드에서 L은 해당 월의 마지막 날을 의미하며, 
	  요일 (Day of week) 필드에서 L은 해당 주의 마지막 날인 토요일을 의미한다. 
	  단, 앞에 숫자가 붙을 경우 해당 월의 마지막 요일을 뜻한다.
	  ex) 6L : 해당 월의 마지막 금요일
	- 15W : 해당 월의 15일에서 가장 가까운 평일
	- FRI#3 또는 6#3 : 매월 3번째 금요일
	
	예시)
	Cron 표현식			의미
	0 10 * * * ?		매 시각 10분
	0 0/10 * * * ?		매 10분마다
	0 0 12 * * ?		매일 오후 12시
	0 15 10 * * ?		매일 오전 10시 15분
	0 15 10 * * ? 2021	2021년 동안 매일 오전 10시 15분
	0 * 14 * * ?		매일 오후 2시부터 2시 59분까지 1분마다
	0 0/5 14 * * ?		매일 오후 2시부터 2시 55분까지 5분마다
	0 0/5 14, 18 * * ?	매일 오후 2시부터 2시 55분까지 5분마다,
						매일 오후 6시부터 6시 55분까지 5분마다
	0 10,44 14 ? 3 WED	3월 매주 수요일마다 오후 2시 10분, 오후 2시 44분
	0 15 10 ? * MON-FRI	매주 월요일부터 금요일까지 오전 10시 15분
	0 15 10 15 * ?		매월 15일 오전 10시 15분
	0 15 10 L * ?		매월 말일 오전 10시 15분
	0 15 10 ? * 6L 2010-2021	2010년 ~ 2021년 동안 매월 마지막주 금요일 오전 10시 15분
	0 15 10 ? * 6#3		매월 셋째주 금요일 오전 10시 15분
	0 0 12 1/5 * ?		매월 1일부터 매 5일마다 오후 12시
	0 11 11 11 11 ?		매년 11월 11일 오전 11시 11분
	

*/