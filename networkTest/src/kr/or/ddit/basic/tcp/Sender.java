package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// 이 클래스는 소켓을 통해서 메시지를 보내는 역할을 담당하는 쓰레드 클래스이다.
public class Sender extends Thread{

	private Socket socket;
	private DataOutputStream dout;
	
	private String name;
	private Scanner scan;
	
	// 생성자
	public Sender(Socket socket) {
		scan = new Scanner(System.in);
		
		System.out.println("이름 입력 >> ");
		name = scan.nextLine();
		
		// try catch로 만들어서 하기!
		this.socket = socket;
		try {
			dout = new DataOutputStream(this.socket.getOutputStream()); // try catch로 만들어서 하기!
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 실제 스레드가 하는것
	// 상대방에게 보낼 데이터 입력 받아주는 역할? 
	@Override
	public void run() {
		while(dout!=null) {
			try {
				dout.writeUTF(name + " : " + scan.nextLine());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
