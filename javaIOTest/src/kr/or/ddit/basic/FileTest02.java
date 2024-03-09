package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class FileTest02 {

	public static void main(String[] args) {
		File file1 = new File("d:/d_other/test.txt");
		
		System.out.println(file1.getName() + "의 크기 : " 
						+ file1.length() + "byte(s)");
		System.out.println();
		
		System.out.println("path : " + file1.getPath());
		System.out.println("absolutePath : " + file1.getAbsolutePath());
		System.out.println();
		
		// 경로 : 파일을 사용하기 위해 찾아가는 길
		// 절대경로 : 찾아가려면 시작 위치가 한 곳으로 정해져있음. (맨 위부터 출발)
		// 상대경로 : 현재 위치(내가 작업하고 있는 위치)에서부터 출발
		// 상위(부모) 폴더 / 하위(자식) 폴더 / 루트('/' or'\')
		// 현재위치 : . / 상위: .. / 하위: 폴더명(이름)
		// 첫번째 글자가 /가 아니면 상대경로 
		
		// 현재 위치 나타내기
		// 절대경로?
		File file2 = new File("."); // 현재 위치 = 현재 디렉토리 . 표시
		
		System.out.println("path : " + file2.getPath());
		System.out.println("absolutePath : " + file2.getAbsolutePath());
		System.out.println();
		
		if(file1.isFile()) {
			System.out.println(file1.getName() + "은(는) 파일입니다.");
		}else if(file1.isDirectory()) {
			System.out.println(file1.getName() + "은(는) 디렉토리입니다.");
		}else {
			System.out.println(file1.getName() + "은(는) 뭘까요?");
		}
		
		System.out.println();
		
		// 상대 경로?
		if(file2.isFile()) {
			System.out.println(file2.getName() + "은(는) 파일입니다.");
		}else if(file2.isDirectory()) {
			System.out.println(file2.getName() + "은(는) 디렉토리입니다.");
		}else {
			System.out.println(file2.getName() + "은(는) 뭘까요?");
		}
		
		System.out.println();
		
		// 존재하지 않는 파일 지정
		File file3 = new File("d:/d_other/sample.exe");
		
		if(file3.isFile()) {
			System.out.println(file3.getName() + "은(는) 파일입니다.");
		}else if(file3.isDirectory()) {
			System.out.println(file3.getName() + "은(는) 디렉토리입니다.");
		}else {
			System.out.println(file3.getName() + "은(는) 뭘까요?");
		}
		
		System.out.println();
		
		if(file3.exists()) {
			System.out.println(file3.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(file3.getAbsolutePath() + "은 존재하지 않습니다.");
			
			try {
				if (file3.createNewFile()) {
					System.out.println("파일생성 완료...");
				}else {
					System.out.println("파일 생성 실패!!!");
				}
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
	}

}
