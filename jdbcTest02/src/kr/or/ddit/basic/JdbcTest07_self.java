package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	회원을 관리하는 프로그램을 작성하시오.
	(mymember 테이블 이용)
	
	아래 메뉴의 기능을 모두 구현하시오. (CRUD 기능 구현하기) insert select update delete
	
	메뉴 예시)
	== 작업 선택 ==
	1. 자료 추가		==> insert (C)
	2. 자료 삭제		==> delete (D)
	3. 자료 수정		==> update (U)
	4. 전체 자료 출력	==> select (R)
	0. 작업 끝.
	
	조건)
	1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력 받는다.)
	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	3) 자료 수정에서 '회원ID'는 변경되지 않는다.
	
 */
public class JdbcTest07_self {
	private HashMap<String, Member> memberMap;
	private Scanner scan;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String memId;
	String memPass;
	String memName;
	String memTel;
	String memAddr;
	
	// 생성자
	public JdbcTest07_self() {
		memberMap = new HashMap<String, Member>();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new JdbcTest07_self().memberStart();
	}
	
	// 시작 메서드
	public void memberStart() {
		System.out.println("♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println("\t회 원 관 리 프 로 그 램\t");
		System.out.println("♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡");
		System.out.println();

		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: // 추가
				insert();
				break;
			case 2: // 삭제
				delete();
				break;
			case 3: // 수정
				update();
				break;
			case 4: // 전체 자료
				research();
				break;
			case 0: // 종료
				System.out.println();
				System.out.println("프로그램을 종료합니다...");
				return;
			default:
				System.out.println();
				System.out.println("작업 번호를 잘못 입력 했습니다.");
				System.out.println("다시 선택하세요...");
			}
		}
	}

	private void delete() {
		try {
			conn = DBUtil.getConnection();
			
			int count = 0;
			
			String sql1 = "select count(*) from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql1);
			
			do {
				System.out.print("정보 수정할 아이디 입력 >> ");
				memId = scan.next();

				// SQL문의 물음표(?)자리에 데이터 셋팅하기
				pstmt.setString(1, memId);

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}

				if (count == 0) {
					System.out.println("입력한 아이디 " + memId + "는 존재하지 않는 아이디입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}

			} while (count == 0);
			
			String sql2 = "delete from mymember where mem_id = ?";

			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memId);

			System.out.print("입력한 아이디 " + memId + "를 삭제하시겠습니까? (y/n) >> ");
			String answer = scan.next();

			if (answer.equalsIgnoreCase("y")) {
				int result = pstmt.executeUpdate();

				if (result > 0) {
					System.out.println("아이디 " + memId + "가 삭제되었습니다.");
				} else {
					System.out.println("아이디 " + memId + " 삭제에 실패했습니다.");
				}
			} else if (answer.equalsIgnoreCase("n")) {
				System.out.println("아이디 " + memId + " 삭제를 취소합니다.");
			} else {
				System.out.println("잘못된 입력입니다. 아이디 " + memId + " 삭제를 취소합니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
	}

	private void update() {
		try {
			
			conn = DBUtil.getConnection();
			
			int count = 0;
			
			String sql1 = "select count(*) from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql1);
			
			do {
				System.out.print("정보 수정할 아이디 입력 >> ");
				memId = scan.next();

				// SQL문의 물음표(?)자리에 데이터 셋팅하기
				pstmt.setString(1, memId);

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}

				if (count == 0) {
					System.out.println("입력한 아이디 " + memId + "는 존재하지 않는 아이디입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}

			} while (count == 0);
			
			System.out.print("새로운 비밀번호 입력 >> ");
	        memPass = scan.next();
	        System.out.print("새로운 이름 입력 >> ");
	        memName = scan.next();
	        System.out.print("새로운 전화번호 입력 >> ");
	        memTel = scan.next();
	        System.out.print("새로운 주소 입력 >> ");
	        memAddr = scan.next();
			
			String sql2 = "UPDATE MYMEMBER SET MEM_PASS=?, MEM_NAME=?, MEM_TEL=?, MEM_ADDR=? WHERE MEM_ID=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memPass);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			pstmt.setString(5, memId);
			
			pstmt.executeUpdate();
			
			System.out.println("정보가 수정되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}

	}

	private void research() {
		try {
			conn = DBUtil.getConnection();
			
			System.out.println();
			System.out.println("====== 회원 정보 전체 자료 ======");
			
			String sql = "select * from mymember";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("아이디 : " + rs.getString("MEM_ID"));
				System.out.println("비밀번호 : " + rs.getString("MEM_PASS"));
				System.out.println("회원명 : " + rs.getString("MEM_NAME"));
				System.out.println("전화번호 : " + rs.getString("MEM_TEL"));
				System.out.println("주소 : " + rs.getString("MEM_ADDR"));
				System.out.println("-----------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
	}

	private void insert() {
		try {
			conn = DBUtil.getConnection();
			int count = 0;
			
			System.out.println("== 계좌 생성하기 ==");
			
			// 아이디 중복 확인
			String checkIdSql = "select count(*) from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(checkIdSql);
			
			do {
				System.out.print("등록할 아이디 입력 >> ");
				memId = scan.next();

				// SQL문의 물음표(?)자리에 데이터 셋팅하기
				pstmt.setString(1, memId);

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);

				}

				if (count > 0) {
					System.out.println("입력하신 '" + memId + "' 아이디는 이미 등록된 아이디입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}

			} while (count>0);
			
			System.out.print("등록할 비밀번호 입력 >> ");
			memPass = scan.next();
			System.out.print("등록할 회원명 입력 >> ");
			memName = scan.next();
			System.out.print("등록할 전화번호 입력 >> ");
			memTel = scan.next();
			System.out.print("등록할 주소 입력 >> ");
			memAddr = scan.next();
			
			
			String sql = "insert into mymember (MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR) "
					+ " values (?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("등록 성공!!!");
			}else {
				System.out.println("등록 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
	}

	// 메뉴를 출력하고 작업 번호를 입력 받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("== 작업 선택 ==");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("========= ==");
		System.out.print("번호 입력 >> ");
		return scan.nextInt();
	}

}


class Member{
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_tel;
	private String addr;
	
	public Member(String mem_id, String mem_pass, String mem_name, String mem_tel, String addr) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
		this.addr = addr;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pass() {
		return mem_pass;
	}

	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "회원 정보 \t 아이디 : " + mem_id + "\t 비밀번호 : " + mem_pass + "\t 회원명 : " + mem_name + "\t 전화번호 : " + mem_tel
				+ "\t 주소 : " + addr;
	}
	
}