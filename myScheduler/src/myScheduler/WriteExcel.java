package myScheduler;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

  public static void main(String[] args) throws IOException {
    // 새 Workbook 객체를 생성합니다.
    Workbook workbook = new XSSFWorkbook();

    // 새 Sheet 객체를 생성합니다.
    Sheet sheet = workbook.createSheet("새로운 Sheet");

    // 첫 번째 행에 데이터를 입력합니다.
    Row row = sheet.createRow(0);
    Cell cell = row.createCell(0);
    cell.setCellValue("이름");

    cell = row.createCell(1);
    cell.setCellValue("나이");

    // 두 번째 행에 데이터를 입력합니다.
    row = sheet.createRow(1);
    cell = row.createCell(0);
    cell.setCellValue("홍길동");

    cell = row.createCell(1);
    cell.setCellValue(20);

    // 엑셀 파일을 저장합니다.
    String filePath = "your_excel_file_path.xlsx";
    try (FileOutputStream fos = new FileOutputStream(filePath)) {
      workbook.write(fos);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}