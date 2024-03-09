package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest {
	
	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // 입력용 
		byte[] outSrc = null; // 출력용
		
		// 입출력을 처리할 스트림 객체 생성
		ByteArrayInputStream bin = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		// *중요! // 스트림 생성
		int data; // 읽어온 자료가 저장될 변수
		
		// 입출력할 때는 while문 많이 쓴다.
		// 읽어온 값이 data에 저장됨. 
		// data 저장된 값이 마지막 9까지 읽어 반복문 처리하면 9 읽어간다음 데이터 없으니 -1 반환한다.
		
		// *중요! //입출력 작업
		// 실제 입출력 작업하는 곳! 이 코드는 read 명령쓰면 거의 이렇게 쓰인다.
		// read()메서드 ==> 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while( (data = bin.read()) != -1) {
			bout.write(data); // 읽어온 자료를 그대로 출력하기 // 마지막만 읽어옴?
		}
		
		// 출력된 스트림값을 배열로 변환해서 저장하기
		outSrc = bout.toByteArray();
		
		// 작업이 완료되면 사용했던 스트림을 닫아준다. (자원  반납) // 닫기! 
		// ...명령어가 cross 라는 것 사용해서.. 닫아준다 표현?
		try {
			bin.close();
			bout.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		System.out.println(" inSrc : " + Arrays.toString(inSrc));
		System.out.println("outSrc : " + Arrays.toString(outSrc));
		
	}
}
