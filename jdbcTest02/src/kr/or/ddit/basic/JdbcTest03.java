package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) LPROD_ID 값을 2개 입력 받아서 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력하시오.

public class JdbcTest03 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("첫번째 LPROD_ID 값을 입력하세요.");
		int num1 = scan.nextInt();
		System.out.println("두번째 LPROD_ID 값을 입력하세요.");
		int num2 = scan.nextInt();
		
		if(num1 > num2) {
			int temp = num1;
			num1 = num2;
			num2 = temp;
		}
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"KMJ97", "java");
			// ---------------------------------------
			/*
//			String sql = "select * from lprod where lprod_id >= " + num1 + 
//					" and lprod_id <= " + num2;
			
			String sql = "select * from lprod where lprod_id between " + num1 + 
					" and " + num2;
			
			
//			String sql; 
//			if(num1 > num2) {
//				sql = "select * from lprod where lprod_id between " + num2  +" and " 
//						+ num1;
//			}else {
//				sql = "select * from lprod where lprod_id between " + num1  +" and " 
//						+ num2;
//			}
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			*/
			// ---------------------------------------
			
//			String sql = "select * from lprod where lprod_id >= ? and lprod_id <= ?";
			String sql = "select * from lprod where lprod_id between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num1);
			pstmt.setInt(2, num2);
			
			rs = pstmt.executeQuery();//쿼리를 실행
			// ---------------------------------------
			
			System.out.println("========= 결과 출력 =========");
			
			//select * from lprod where lprod_id between 1 and 5
			/*    lprod_id   lprod_gu  lprod_nm
			커서->
					1	       P101	          컴퓨터제품 : rs
				->	2	       P102	          전자제품
					3	       P201	          여성캐주얼
					4	       P202	          남성캐주얼
					5	       P301	          피혁잡화
			 */
			while(rs.next()) { //rs : 1행(현재 커서가 위치한)
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));//1
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));//P101
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));//컴퓨터제품
				System.out.println("-------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e2) {}
			if(stmt!=null) try{stmt.close();} catch(SQLException e2) {}
			if(conn!=null) try{conn.close();} catch(SQLException e2) {}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e2) {}
		}
		
	}

}
