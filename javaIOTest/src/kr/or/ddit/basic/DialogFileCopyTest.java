package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogFileCopyTest {

	public static void main(String[] args) {
		DialogFileCopyTest test = new DialogFileCopyTest();
		
//		File file = new File("d:/d_other/차은우.jpg");
		File file = test.DialogOpen("OPEN");
		
		if(file == null) {
			System.out.println("원본 파일 선택에 실패했습니다...");
			System.out.println("복사 작업을 중지합니다...");
			return;
		}

		if (!file.exists()) {
			System.out.println(file.getAbsolutePath() + " 파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다...");
			return;
		}

		FileInputStream fin = null;
		FileOutputStream fout = null;
		

		try {
			// 원본 파일을 읽어올 스트림 객체 생성
			fin = new FileInputStream(file);
			

			// 대상 파일에 출력할 스트림 객체 생성
			File targetFile = test.DialogOpen("SAVE");
			if(targetFile == null) {
				System.out.println("대상 파일 선택에 실패했습니다.");
				System.out.println("복사 작업을 중지합니다...");
				return;
			}
			
//			fout = new FileOutputStream("d:/d_other/연습용/차은우_복사본.jpg");
			fout = new FileOutputStream(targetFile);

			System.out.println("복사 작업 시작...");

			int data; // 읽어온 자료가 저장될 변수

			while ((data = fin.read()) != -1) {
				fout.write(data);
			}
			System.out.println("복사 작업 완료...");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 만약 위 중간에서 오류 나면 사용했던 자원 제대로 반납 못할수도 있다.
			// 그래서 finally에 넣어주고
			// 그냥은 넣지 못하니 if문으로 null이 아니며 try catch로 묶어주기
			if (fin != null)
				try { fin.close(); } catch (IOException e) { }
			if (fout != null)
				try { fout.close(); } catch (IOException e) { }

		}

	}

	
	private File DialogOpen(String type) {
		// SWING의 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();

		// 선택할 파일의 확장자 설정
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text파일(*.txt)", "txt");
		// 앞에 글씨 출력할 것. 뒤는 확장자 적기

		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word File", new String[] { "docx", "doc" });

		FileNameExtensionFilter img = new FileNameExtensionFilter("Images File", "png", "jpg", "gif");

		FileNameExtensionFilter pdf = new FileNameExtensionFilter("PDF File", "pdf");

		// 선택할 파일 목록 중 '모든 파일' 목록의 표시 여부 설정
		// (true: 표시(기본값), false: 표시하지 않는다.)
		chooser.setAcceptAllFileFilterUsed(true);

		// 등록 순서대로 파일유형 보기 나온다.
		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(pdf);

		// 선택할 파일 목록 중 처음부터 선택되게 하고 싶은 파일 목록 설정
		chooser.setFileFilter(img);

		// '찾는 위치'(처음부터 작업할 폴더) 설정하기
		chooser.setCurrentDirectory(new File("d:/d_other"));

		// Dialog창 띄우기
		int result;
		if("OPEN".equals(type.toUpperCase())) {
			result = chooser.showOpenDialog(new Panel()); // 열기창
		}else if("SAVE".equals(type.toUpperCase())) {
			result = chooser.showSaveDialog(new Panel()); // 저장창
		}else {
			return null;
		}

		// Dialog창에서 선택한 파일 정보 구하기
		// '열기'버튼 또는 '저장' 버튼을 눌렀는지 여부 검사
		File selectFile = null;
		if (result == JFileChooser.APPROVE_OPTION) {
			selectFile = chooser.getSelectedFile(); // 선택한 파일 정보 반환
			System.out.println("선택한 파일 : " + selectFile.getAbsolutePath());
		}
		
		return selectFile;
	}
}
