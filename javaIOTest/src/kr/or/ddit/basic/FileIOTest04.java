package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		try {
			/*
			 	인스턴스 변수 (맴버변수도 사실 포함임) - 정적변수 아닌 것
			 	정적변수 스태틱 변수 - 객체생성없이 사용할수 있는 변수
			 	system클래스라는 정적변수 in(inputStream 이미 들어있다. inputStream은 바이트기반 스트림이지) 
			 */
			
			// System.in ==> 콘솔(표준 입출력 장치)의 입력 장치와 연결된 스트림 객체
//			Scanner scan = new Scanner(System.in); // system(클래스)이라는 객체다. in은 변수다.
			
//			System.out.print("입력 >> ");
//			int c = System.in.read();
//			System.out.println( (char) c );
			
			// reader라고 끝났으니 문자기반 보조? 스트림. input이니 바이트를 문자기반으로 바꿔줌
			// 입력용 바이트 기반 스트림을 문자 기반 스트림으로 변환
			InputStreamReader isr = new InputStreamReader(System.in);// 그래서 여기 바이트 기반 스트림 넣어줘야함
			
			// 파일 출력용 문자 기반 스트림 생성
			FileWriter fw = new FileWriter("d:/d_other/testChar.txt");
			
			System.out.println("아무 내용이나 입력하세요. (입력의 끝은 'Ctrl + Z' 입니다.)");
			
			int c;
			
			// 콘솔에서 입력할 때 입력의 끝은 'Ctrl' + 'Z' 키를 누르면 된다.
			while( (c = isr.read()) != -1) {
				fw.write(c); // 콘솔로 입력 받은 데이터를 파일에 출력한다.
			}
			
			// 스트림 닫기
			isr.close();
			fw.close();
			
			System.out.println();
			System.out.println("작업 끝...");
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
