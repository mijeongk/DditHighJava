package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered 스트림을 사용한다.
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");  
			
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for(char ch='1'; ch<='9'; ch++) {
				bout.write(ch);
			}
			// 출력 작업을 마치면 버퍼에 남아 있는 데이터를 모두 출력시켜야 한다. 
			bout.flush(); 
			
			System.out.println("작업 끝...");
			// 결과 5까지나온다 왜? 버퍼 크기가 5이기 때문에.
			// 직접 파일로 안가고 버퍼로 간다. // 버퍼는 해당된 크기만큼만 한꺼번에 파일에 출력함.
			// cpu일 다했다고 생각하지만 버퍼는 4개로 가득 안차있으니 출력X
			// 강제 출력 flush() 사용!
			
			// 보조 스트림을 닫으면 보조 스트림이 사용한 기반 스트림도 같이 닫힌다.
			bout.close(); // 만약 flush 안쓰고 close 하면? 9까지 나옴 왜??
			// close에는 flush 기능까지 있다.
			// 작업하다보면 어딘가 출력하고 이어서 스트림 사용하고싶을 때 있다.
			// close만 사용하면 flush 작업 안되어있으니 남아있을 수 있다.
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
