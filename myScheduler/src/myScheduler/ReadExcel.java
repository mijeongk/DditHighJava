package myScheduler;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {

	public static void main(String[] args) throws IOException {
		// 읽을 엑셀 파일 경로
		String filePath = "your_excel_file_path.xlsx";

		// FileInputStream을 이용하여 엑셀 파일을 읽습니다.
		try (FileInputStream fis = new FileInputStream(filePath)) {

			// Workbook 객체를 생성합니다.
			Workbook workbook = WorkbookFactory.create(fis);

			// 첫 번째 Sheet을 가져옵니다.
			Sheet sheet = workbook.getSheetAt(0);

			// 모든 행을 반복합니다.
			for (Row row : sheet) {

				// 모든 셀을 반복합니다.
				for (Cell cell : row) {

					// 셀의 값을 출력합니다.
					System.out.print(cell.toString() + "\t");
				}

				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}