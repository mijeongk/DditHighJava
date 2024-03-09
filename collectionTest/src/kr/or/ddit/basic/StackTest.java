package kr.or.ddit.basic;

import java.util.Stack;

public class StackTest {
	public static void main(String[] args) {
		Browser b = new Browser();
		
		b.history();
		
		System.out.println("사이트 방문하기...");
		b.goURL("1.네이버");
		b.history();
		
		b.goURL("2.야후");
		b.history();
		
		b.goURL("3.구글");
		b.goURL("4.다음");
		b.history();
		
		System.out.println("뒤로 가기 후...");
		b.goBack();
		b.history();
		
		System.out.println("뒤로 가기 후...");
		b.goBack();
		b.history();

		System.out.println("앞으로 가기 후...");
		b.goForward();
		b.history();
		
		System.out.println("새로운 사이트 접속하기 ...");
		b.goURL("5.네이트");
		b.history();
		
	}
}


// 웹 브라우저의 앞으로 가기, 뒤로가기 기능 구현하기 (스택 이용)
class Browser{
	private Stack<String> back;			// 이전 방문 내역이 저장될 Stack
	private Stack<String> forward;		// 이전 방문 내역이 저장될 Stack
	private String currentURL;			// 현재 페이지
	
	// 생성자
	public Browser() {
		back = new Stack<String>();
		forward = new Stack<String>();
		currentURL = "";
	}
	
	// 주소 쳤을 때 사이트 방문하는 메서드 ==> 매개변수에는 방문할 URL이 저장되어 호출된다.
	public void goURL(String url) {
		System.out.println(url + " 사이트에 접속합니다.");
		
		// 현재 주소가 널이 아니고 공백이 아니면 이전 방문 페이지 (현재 페이지 보고 있는 주소 있을  경우)
		if(currentURL !=null && !"".equals(currentURL)) { // 현재 페이지가 있으면..
			back.push(currentURL);// 현재 페이지를 back 스택에 추가
		}
		currentURL = url; // 현재 페이지를 '방문할 URL'로 변경한다.
		
	}

	// 뒤로가기
	public void goBack() {
		// isEmpty() ==> List, Stack 등이 비어 있으면 true, 그렇지 않으면 false 반환
		if(!back.isEmpty()) {
			forward.push(currentURL);		// 현재 페이지를 forward스택에 추
			currentURL = back.pop();		// back 스택에서 1개의 요소를 꺼내와 현재 페이지로 한다.
		}
	}
	
	// 앞으로 가기
	public void goForward() {
		if(!forward.isEmpty()) {
			back.push(currentURL);			// 현재 페이지를 back 스택에 추가
			currentURL = forward.pop();		// forward 스택에서 1개의 요소를 꺼내와 현재 페이지로 한다.
		}
	}
	
	// 방문 기록 확인하기
	public void history() {
		System.out.println();
		System.out.println("--------------------------");
		System.out.println("방\t문\t기\t록");
		System.out.println("--------------------------");
		System.out.println("back\t\t=> " + back);
		System.out.println("현재\t\t=> " + currentURL);
		System.out.println("forward\t\t=> " + forward);
		System.out.println("--------------------------");
		System.out.println();
	}
	
	
}