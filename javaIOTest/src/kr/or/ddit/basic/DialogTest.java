package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
		
		// SWING의 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();

		// 선택할 파일의 확장자 설정
		FileNameExtensionFilter txt = 
				new FileNameExtensionFilter("Text파일(*.txt)", "txt");
				// 앞에 글씨 출력할 것. 뒤는 확장자 적기
		
		FileNameExtensionFilter doc = 
				new FileNameExtensionFilter("MS Word File", new String[] {"docx", "doc"});
		
		FileNameExtensionFilter img = 
				new FileNameExtensionFilter("Images File", "png", "jpg", "gif");

		FileNameExtensionFilter pdf = 
				new FileNameExtensionFilter("PDF File", "pdf");
		
		// 선택할 파일 목록 중 '모든 파일' 목록의 표시 여부 설정
		// (true: 표시(기본값), false: 표시하지 않는다.)
		chooser.setAcceptAllFileFilterUsed(false);
		
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
//		int result = chooser.showOpenDialog(new Panel()); // 열기 창 // 파일 선택하는 역할만 함
		int result = chooser.showSaveDialog(new Panel()); // 저장 창 // 저장하려면 직접코딩해야 한다?
		
		// Dialog창에서 선택한 파일 정보 구하기
		// (예-파일 누르고 열기 버튼을 누르는 것 혹은 저장창에서는 저장 누르는 것)
		// '열기'버튼 또는 '저장' 버튼을 눌렀는지 여부 검사
		if(result == JFileChooser.APPROVE_OPTION) { 
			File selectFile = chooser.getSelectedFile(); // 선택한 파일 정보 반환
			System.out.println("선택한 파일 : " + selectFile.getAbsolutePath());
		}
		
	}

}
