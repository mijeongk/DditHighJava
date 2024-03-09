package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC(Java DataBase Connectivity) 라이브러리를 이용한 DB자료 처리하기 

public class JdbcTest01 {
	/*
		JDBC를 이용한 db자료 처리 순서
		
		1. 드라이버 로딩 --> 라이브러리를 사용할 수 있게 메모리로 읽어 들이는 작업
		   Class.forName ("oracle.jdbc.driver.OracleDriver");
		
		2. DB시스템에 접속하기 --> 접속이 완료 되면 Connection 객체가 반환된다.
		   DriverManager.getConnection() 메서드를 이용
		
		3. 질의 ==> SQL문장을 DB서버로 보내서 처리한 결과를 얻어온다 .
		   (Statement 객체나 PreparedStatement 객체를 이용하여 작업한다.)
		
		4. 얻어온 결과 처리하기
		   1) SQL문이 select문일 경우에는 select한 결과가 ResultSet객체에 저장되어 반환된다. 
		   2) SQL문이 select문이 아닐 경우 (insert , delete, update 등) 에는 정수값이 반환.
		      (이 정수값은 실행에 성공한 레코드 수를 말한다.)
		
		5. 사용한 자원 반납하기 
		   (Connection 객체 , Statement객체 , PreparedStatement객체, ResultSet객체 등)
		   --> 각 객체의 close() 메서드를 사용한다. 
	 */
	
	public static void main(String[] args) {
		//JDBC 작업에 필요한 변수 선언
		Connection conn = null; 
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. 드라이버 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 대소문자 맞춰 작성
			
			//2. DB시스템에 접속하기 --> 접속 성공시Connection 객체가 생성됨
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"KMJ97", "java");
			//3. 질의 
			//3-1. SQL문 작성 
			String sql = "select*from lprod";
			
			//3-2. Statement객체 생성 ==> Connection 객체를 이용하여 생성
			stmt = conn.createStatement();
			
			//3-3. SQL문을 DB서버로 보내서 결과를 얻어온다.  
			//	  (실행할 SQL문이 select문이기 때문에 결과가 ResultSet객체에 저장되어 반환됨)
			rs = stmt.executeQuery(sql); // 쿼리 실행
			
			//4. 결과 처리 하기 ==> 한 레코드씩 화면에 출력하기 
			//	 ResultSet 객체에 저장된 데이터를 차례로 꺼내오려면 반복문과 next()메서드를 이용한다.
			
			//ResultSet 객체.next() ==> ResultSet객체에서 데이터를 가리키는 포인터를 다음번째
			//				      위치로 이동시키고 그 곳에 데이터가 있으면 true, 없으면 false반환.
			System.out.println("=================");
			while(rs.next()) {
				//현재 포인터가 가리키는 ResultSet 객체의 데이터를 가져와서 처리
				//데이터를 가져오는 방법
				//형식1) ResultSet객체.get자료형이름("컬럼명" 또는 "alias명");
				//형식2) ResultSet객체.get자료형이름(컬럼번호); --> 컬럼번호는 1부터 시작
				
				System.out.println("LPROD_ID : " + rs.getInt(1));
				System.out.println("LPROD_ID : " + rs.getString("LPROD_ID"));
//				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID")); // 데이터 가져오는것이기 때문에 string,int 둘다 괜찮음?
				System.out.println();
				System.out.println("LPROD_GU : " + rs.getString(2));
				System.out.println("LPROD_GU : " + rs.getString("LPROD_gu"));
				System.out.println();
				System.out.println("LPROD_NM : " + rs.getString(3));
				System.out.println("LPROD_NM : " + rs.getString("LPROD_nm"));
				System.out.println("--------------------------------------");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			//5. 자원 반납 
			if(rs!= null) try {rs.close();}catch (SQLException e2) {}
			if(stmt!= null) try {stmt.close();}catch (SQLException e2) {}
			if(conn!= null) try {conn.close();}catch (SQLException e2) {}
		}
	}
}
