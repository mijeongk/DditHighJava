package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		// FileInputStream객체를 이용한 파일 내용 읽기
		
		try {
			// 읽어올 파일 정보를 갖는 파일 입력용 스트림 객체를 생성한다.
			
			// 방법1) 읽어올 파일 정보를 문자열로 지정하는 방법
//			FileInputStream fin = new FileInputStream("d:/d_other/test.txt");
			
			// 방법1) 읽어올 파일 정보가 저장된 File객체를 지정하는 방법
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);
			
			// 데이터 하나씩 읽어온다.
			int c; // 읽어온 데이터가 저장될 변수 선언
			while ( (c = fin.read()) != -1) { // 만약 데이터중 -1이 있다면??
				// 컴퓨터에서 정수 표기법
				// 만약 25 저장하려면 2진수로 나눔. 11001 만약 +라면 [00011001] 맨앞은 부호비트 0=+ 1=-
				// 만약 25 저장하려면 2진수로 나눔. 11001 만약 -라면 [10011001] 맨앞은 부호비트 0=+ 1=-
				// ==> 근데 이거 중복될 수 있다. 00000000 => 10진수로 나누면 +0
				// ==> 근데 이거 중복될 수 있다. 10000000 => 10진수로 나누면 -0 => 못쓰는것과 같다 이런 단점 있다.
				// 해결법 : 컴퓨터 음수 나타낼 때 2의 보수법 활용 => 1의 보수 +1
				// 1의 보수: 0은 1로 바꾸고 1은 0으로 바꿈
				// 2의 보수 : 1의 보수에 +1 
				// 만약 -25 구해라 하면 부호 없이 25라고 생각하고  [00011001]2진수 만든다.
				// 1의 보수 구하면 0은 1로 1은 0으로 바꾸면 [11100110] +1
				// 결과: [11100111] => 다시 거꾸로하면 
				// => 1의 보수법 [00011000] => +1 하면 [00011001] => 2진수 하면 25
				
				// -1 
				// +1 => [00000001] => 2의 보수법 [11111111] -1임
				// int형은 4바이트 이다. [ _ _ _ _ ] -1 [1111] => 0...0 0...0 0...0 11111111
				// -> -1 아니니까 쓰겠지 ㅇ.ㅇ???
				
				// char 2바이트만 쓴다.
				
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char)c);
				
			}
			
			fin.close(); // 작업 완료 후 스트림 닫기
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다...");
		}

	}

}
