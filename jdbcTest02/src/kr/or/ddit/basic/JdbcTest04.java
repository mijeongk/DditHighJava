package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		// DB의 backinfo 테이블에 새로운 계좌정보를 입력 받아 추가하는 예제
		
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"KMJ97", "java");
			
			System.out.println("추가할 계좌 정보를 입력하세요.");
			System.out.print("계좌번호 입력 >> ");
			String bankNo = scan.next(); 
			System.out.print("은행명 입력 >> ");
			String bankName = scan.next(); 
			System.out.print("예금주명 입력 >> ");
			String userName = scan.next(); 
			
			// -----------------------------------------------
			/*
			// Statement객체를 이용하여 데이터 추가하기
			String sql = 
					"insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) "
					+ " values ('"+ bankNo +"', '" + bankName +"', '"+ userName +"', sysdate)";
			
			System.out.println("SQL문장 >> " + sql);
			stmt = conn.createStatement();
			
			// select문인 SQL문일 때는 executeQuery()메서드를 사용하고,
			// select문이 아닌 SQL문을 실행할 때는 executeUpdate()메서드를 사용한다.
			// excuteUpdate()메서드의 반환값은 작업에  서공한 레코드 수이다.
			
			int cnt = stmt.executeUpdate(sql); // insert 성공시 반환값 1
			 */
			// -----------------------------------------------
			
			// -----------------------------------------------
			// PreparedStatement 객체를 이용하여 데이터 추가하기
			// SQL문을 작성할 때 데이터가 들어갈 자리는 물음표(?)로 표시하여 작성한다.
			String sql = 
					"insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) "
					+ " values (?, ?, ?, sysdate)";
			
			// PreparedStatement 객체 생성
			// ==> 이 때 처리할 SQL문을 매개변수로 넣어준다.
			pstmt = conn.prepareStatement(sql); // sql 넣어야지 ? 값 넣어줌?
			
			// SQL문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식) PreparedStatement객체.set자료형이름(물음표번호, 데이터);
			//		==> 물음표 번호는 1부터 시작한다.
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, userName);
			
			// 데이터의 셋팅이 완료되면 실행한다.
			int cnt = pstmt.executeUpdate();
			
			// -----------------------------------------------
			
			System.out.println("반환값 : " + cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt !=null) try {stmt.close();} catch(SQLException e){}
			if(conn !=null) try {conn.close();} catch(SQLException e){}
			if(pstmt !=null) try {pstmt.close();} catch(SQLException e){}
		}
		
	}

}
