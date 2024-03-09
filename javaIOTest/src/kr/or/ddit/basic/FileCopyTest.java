package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTest {
	/*
	 문제) 'd:/d_other' 폴더에 있는 '차은우.jpg'파일을 'd:/d_other/연습용' 폴더에 '차은우_복사본.jpg'파일로
	 복사하는 프로그램을 작성하시오.
	 */

	public static void main(String[] args) {
		File file = new File("d:/d_other/차은우.jpg");

		if (!file.exists()) {
			System.out.println(file.getAbsolutePath() + " 파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다...");
			return;
		}

		FileInputStream fin = null;
		FileOutputStream fout = null;

		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;

		try {
			// 원본 파일을 읽어올 스트림 객체 생성
			fin = new FileInputStream(file);
			bin = new BufferedInputStream(fin);
			

			// 대상 파일에 출력할 스트림 객체 생성
			fout = new FileOutputStream("d:/d_other/연습용/차은우_복사본.jpg");
			bout = new BufferedOutputStream(fout);

			System.out.println("복사 작업 시작...");

			int data; // 읽어온 자료가 저장될 변수

//			while ((data = fin.read()) != -1) {
//				fout.write(data);
//			}
			System.out.println("복사 작업 완료...");
			while ((data = bin.read()) != -1) {
				bout.write(data);
			}
			bout.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 만약 위 중간에서 오류 나면 사용했던 자원 제대로 반납 못할수도 있다.
			// 그래서 finally에 넣어주고
			// 그냥은 넣지 못하니 if문으로 null이 아니며 try catch로 묶어주기
//			if (fin != null) try { fin.close(); } catch (IOException e) { }
//			if (fout != null) try { fout.close(); } catch (IOException e) { }
			if (bin != null) try { fin.close(); } catch (IOException e) { }
			if (bout != null) try { fout.close(); } catch (IOException e) { }

		}

	}

}
