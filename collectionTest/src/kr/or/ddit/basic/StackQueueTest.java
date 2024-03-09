package kr.or.ddit.basic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueueTest {
/*
 * - Stack ==> 후입선출(LIFO Last In First Out)의 자료 구조
 * - Queue ==> 선입선출(FIFO First In First OUT)의 자료 구조
 * 
 * Stack과 Queue는 LinkedList를 이용하여 사용할 수 있다.
 * Link 참조값으로 연결
 */
	public static void main(String[] args) {

		/*
		 * - Stack의 명령
		 * 1. 자료 입력 : push(입력값)
		 * 2. 자료 출력 : pop() ==> 자료를 꺼내온 후 꺼내온 자료를 Stack에서 삭제한다.
		 * 			   peek() ==> 삭제없이 자료를 꺼내온다.(꺼내지 않고 보기만 하는 것)
		 *  + 가장 마지막에 삽입된 자료를 top
		 */
		
		Stack<String> stack = new Stack<String>();
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("변학도");
		stack.push("강감찬");

		// 앞 입구, 뒤 출구
		System.out.println("현재 stack => " + stack.toString());
		
		String data = stack.pop();
		System.out.println("꺼내온 값 : " + data);
		System.out.println("꺼내온 값 : " + stack.pop());
		System.out.println("현재 stack => " + stack.toString());
		
		stack.push("성춘향");
		System.out.println("현재 stack => " + stack.toString());
		
		System.out.println("꺼내온 값 : " + stack.pop());
		System.out.println("현재 stack => " + stack.toString());
		
		System.out.println("삭제 없이 꺼내온 값 : " + stack.peek());
		System.out.println("현재 stack => " + stack);
		
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println();

		/*
		 * - Queue의 명령
		 * 1. 자료 입력 : offer(입력값)
		 * 2. 자료 출력 : poll() ==> 자료 꺼내오고 꺼내온 자료를 Queue에서 삭제
		 * 			   peek() ==> 자료를 꺼내오고 보여주기만 함
		 * 
		 * 큐 클래스 제공안하지만 인터페이스 있음?
		 * 실제 객체 생성할 때는 linkedList
		 */
		
		Queue<String> queue = new LinkedList<String>();
		
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("변학도");
		queue.offer("강감찬");
		
		// 콘솔에 출력될 때는 앞과 같아보이지만 뒤 입구, 앞 출구 
		System.out.println("현재 queue => " + queue);
		
		String temp = queue.poll();
		System.out.println("꺼내온 값 : " + temp);
		System.out.println("꺼내온 값 : " + queue.poll());
		System.out.println("현재 queue => " + queue);
		
		queue.offer("성춘향");
		System.out.println("추가 후의 queue => " + queue);
		
		System.out.println("꺼내온 값 : " + queue.poll());
		System.out.println("현재 queue => " + queue);
		
		System.out.println("삭제없이 꺼내오기 >>" + queue.peek());
		
		
		/*
		 * 이제 큐를 어떻게 사용하나?
		 *  
		 * 콜 스택(call stack)이란 
		 * 컴퓨터 프로그램에서 현재 실행 중인 서브루틴에 관한 정보를 (메모리에) 저장하는 스택 자료구조이다.
		 * 
		 * 메소드 실행하려면 호출해야지.
		 * 영역 실행한다. 호출한 곳의 메소드 실행 끝나면?? 호출했었던 곳으로 돌아감. 주소값을 알아야함.
		 * 그 주소를 스택에 저장함. 그 저장된 주소로 가는 것이다. 이때 사용하는 것이 콜스택이다.
		 * 
		 * 이것 말고도 또 있다. eclipse?에도 있다.
		 * 뭐 잘못해서 지웠다. ctrl+z 이것이 스택을 이용한 것이다.
		 * 뭔가 작업하면 스택에 저장한다. 이것을 잘못지웠을때 작업 되돌리기 하면 저장된것 꺼내오는 것이다.
		 * 
		 * 웹브라우저. 뒤로가기 이전에 갔던 사이트 보인다. 이것도 스택이야.
		 * 
		 * 큐는 어디서 쓰나?
		 * 프린터기. 프린터기로 5장 출력 명령 내리면 데이터가 프린터기로 간다. 느림
		 * 프린트 도중에 엑셀문서 열어서 프린트 날릴 수 있음
		 * 3번째장 출력중인데 엑셀 프린트 누르면 프린터로 가야하는데 워드 출력 중이니까 
		 * 어디로 가나? 프린터에넌 큐 기억장치 있다. 큐처럼 작동한다.
		 * 프린터 큐에 데이터 저장함
		 * 워드/엑셀 > 큐 > 프린터기 (순서 유지하면서 출력함)
		 * 
		 *  스택 활용해서 뒤로 앞으로 페이지 가기 흉내 내보기 (이동  과정만)
		 */
		
		
		
	}

}
