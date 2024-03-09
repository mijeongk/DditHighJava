package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		
		/*
			File 객체 만들기 연습
			
			1. new File(String 파일 또는 경로)
			   ==> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분 문자는 
			              역슬래쉬('\')를 사용하거나 슬래쉬('/')를 사용할 수 있다.
		 */

//		File file1 = new File("d:/D_Other/test.txt"); // 구분문자를 '/'로 사용
		File file1 = new File("d:\\D_Other\\test.txt"); // 구분문자를 '\'로 사용 (역슬래쉬는 2개 사용)
		
		System.out.println("파일명 : " + file1.getName());
		System.out.println("파일일까? : " + file1.isFile());
		System.out.println("디렉토리일까? : " + file1.isDirectory());
		System.out.println();
		
		File file2 = new File("d:/D_Other");
		System.out.println("디렉토리명 : " + file2.getName());
		System.out.println("파일일까? : " + file2.isFile());
		System.out.println("디렉토리일까? : " + file2.isDirectory());
		System.out.println();
		
		/*
			2. new File(File parent, String child)
			   ==> 'parent' 디렉토리 안에 있는 'child' 파일을 갖는다.
		 */
		
//		File file1 = new File("d:\\D_Other\\test.txt"); //이것과 같은 파일 지정하는 것임
		File file3 = new File(file2, "test.txt");
		
		System.out.println("파일명 : " + file3.getName());
		System.out.println("파일일까? : " + file3.isFile());
		System.out.println("디렉토리일까? : " + file3.isDirectory());
		System.out.println();
		
		/*
			3. new File(Stirng parent, String child)
			   ==> 'parent' 디렉토리 안에 있는 'child' 파일을 갖는다.
		 */
		File file4 = new File("d:/D_Other", "test.txt");
		
		System.out.println("파일명 : " + file4.getName());
		System.out.println("파일일까? : " + file4.isFile());
		System.out.println("디렉토리일까? : " + file4.isDirectory());
		System.out.println();
		
		// -------------------------------------------------------
		
		/*
			디렉토리(폴더) 생성하기
			- mkdir() ==> File 객체의 경로 중 마지막 위치의 디렉토리를 만든다.
					  ==> 반환값 : 만들기 성공(true), 실패(false)
					  ==> 중간의 경로가 모두 이미 만들어져 있어야 
					  	    마지막 위치의 경로를 만들 수 있다.
			- mkdir() ==> 
		 */
		File file5 = new File("d:/D_Other/연습용");
		
		System.out.println(file5.getName() + "의 존재 여부 : " + file5.exists());
		// 존재(true)하면 만들기 실패, 존재안(false)하면 만들기 성공 
		
		if (!file5.exists()) { // 파일 있을 때는 실행 안함

			if (file5.mkdir()) { // 만들기 성공하면...
				System.out.println(file5.getName() + " 만들기 성공~~~");
			} else {
				System.out.println(file5.getName() + " 만들기 실패~~~");
				// 다시 실행하면 만들기 실패다. 똑같은 폴더 못만들어.
			}

		}
		
		System.out.println();
		
		File file6 = new File("d:/d_other/test/java/src");

//		if (file6.mkdir()) {
		if (file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공!!!");
		} else {
			System.out.println(file6.getName() + " 만들기 실패!!!");
		}
		
	}
	
}
