package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {

	public static void main(String[] args) {
		// 문자 기반의 Buffered 스트림 사용 예제
		try {
			// 이클립스에서 자바 프로그램이 실행되는 현재 위치는
			// 해당 프로그램이 속한 프로젝트 폴더가 현재 위치가 된다.
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileIOTest01.java"); // 현재 위치부터적으면 내용이 줄어
			BufferedReader br = new BufferedReader(fr);
			// 한줄단위로 읽어오기
			String temp = "";
			
			// 가운데는 조건식이다. 
			//br.readLine() ==> 한 줄 단위로 자료를 읽어온
			for(int i=1; (temp=br.readLine()) != null; i++) {
				System.out.printf("%4d : %s\n", i, temp);
			}
			
			br.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
