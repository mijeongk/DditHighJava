package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {
	public static void main(String[] args) {
		
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; 
		byte[] outSrc = null; 
	
		byte[] temp = new byte[4]; // 입출력에 사용할 배열 생성
		
		try {
			// 입출력을 처리할 스트림 객체 생성
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ByteArrayInputStream bin = new ByteArrayInputStream(inSrc);
			
			// 읽어올 데이터가 있는지 확인
			// available() ==> 읽어올 수 있는 데이터의 byte개수를 반환
			while(bin.available() > 0) {

//				bin.read(temp);
//				bout.write(temp);
				// 이렇게하면 남은 공간에 이전 데이터가 남아있음 // 아래와 같이 해야함
				
				// read(배열) ==> 반환값: 실제 읽어온 데이터의 개수  
				int len = bin.read(temp);
				// temp배열의 데이터들 중에서 0번째부터 len개수 만큼 출력한다.
				bout.write(temp, 0, len);
				
				System.out.println("반복문 안에서 temp : " + Arrays.toString(temp));
				
			}
			
			outSrc = bout.toByteArray();
			
			bin.close();
			bout.close();
			
			System.out.println(" inSrc : " + Arrays.toString(inSrc));
			System.out.println("outSrc : " + Arrays.toString(outSrc));
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
}
