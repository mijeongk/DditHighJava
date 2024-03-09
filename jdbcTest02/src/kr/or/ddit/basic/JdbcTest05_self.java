package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
	문제) LPROD 테이블에 새로운 데이터 추가하기
	
	LPROD_GU와 LPROD_NM은 직접 입력 받아서 처리하고,
	LPROD_ID는 현재의 LPROD_ID 중에서 제일 큰 값보다 1 크게 한다. 
	
	입력 받은 LPROD_GU가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
 */
public class JdbcTest05_self {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("상품분류코드 기입 >> ");
			String lprodGu = scan.next().toUpperCase(); // db에서 pk다. 입력 값이 table에 중복 검사 여부 해야함
			
			System.out.println("상품명 기입 >> ");
			String lprodNm = scan.next();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
						"KMJ97", "java");
				
				String scanLprodGuCheck = "select count(*) from lprod where lprod_gu = ?";
				pstmt = conn.prepareStatement(scanLprodGuCheck);
				
				pstmt.setString(1, lprodGu);
				
				rs = pstmt.executeQuery();
				
				int count = 0;
				
				//select count(*) from lprod where lprod_gu = 'P101'
				//1
				//rs : resultSet(커서, 화살표)
				if(rs.next()) {//커서를 다음 행(가로)으로 이동
					count = rs.getInt(1);
				}
				
				if(count == 1) {
					System.out.println("이미 등록된 상품분류코드입니다. 다시 입력해주세요.");
				} else {
					String sql = "INSERT INTO lprod (lprod_id, lprod_gu, lprod_nm) "
							+ "VALUES ((SELECT MAX(lprod_id) + 1 FROM lprod), ?, ?)";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, lprodGu);
					pstmt.setString(2, lprodNm);
					
					int cnt = pstmt.executeUpdate();
					
					System.out.println("반환값 : " + cnt);

					if(cnt == 1) {
						break;
					}
					
				}
						
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if(conn !=null) try {conn.close();} catch(SQLException e){}
				if(pstmt !=null) try {pstmt.close();} catch(SQLException e){}
				if(rs !=null) try {rs.close();} catch(SQLException e){}
			}
		}
		
	}

}
