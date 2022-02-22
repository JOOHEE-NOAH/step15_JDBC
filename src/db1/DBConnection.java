package db1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	Connection conn=null;
public DBConnection() {
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver"); //1. 드라이버 로드.클래스의 정보를 리턴해주는 함수
		conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "edu", "1234"); //2. 연결 객체 생성
		//접속 경로설정. db에서 edu 마우스 오른쪽 properties-> connection url
		//System.out.println("연결성공");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		//System.out.println("연결실패");
		e.printStackTrace();
	}
}
	public void addressList() {
		PreparedStatement pstmt=null;
		ResultSet rs=null; //4. 결과값 반환
		
		try {
			String sql="SELECT * FROM ADDRESSBOOK ORDER BY NUM DESC"; //3. sql 문장 실행
			pstmt=conn.prepareStatement(sql);// sql 문장을 가지고 데이터 베이스 쪽으로 접근하고, 결과값을 preparedStatement객체로 내놓음
			rs=pstmt.executeQuery(); // 결과로 result set을 내놓음. // executeQuery: select 문장에만 사용 
			
		//	if(rs!=null)
		//		System.out.println("데이터 가져오기 성공");
			while(rs.next()) { //rs값 뽑아내기
				System.out.print(rs.getInt("num")+"\t"); //rs.getInt(1) -->0번아님 컬럼 이름 써도 되고 인덱스 번호로 해도 됨.
				System.out.print(rs.getString("name")+"\t");
				System.out.print(rs.getString("phone")+"\t");
				System.out.println(rs.getString("addr"));
			}
			
		} catch (SQLException e) {
		//	System.out.println("데이터 가져오기 실패");
			e.printStackTrace();
		}finally {
				try {if(rs!=null)		rs.close(); //5.연결객체 해제
					if(pstmt!=null)		pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}//end of addressList()
	
	
//	//PreparedStatement 객체 사용			
//		public void addressInsert(String name, String phone, String addr) {
//			PreparedStatement pstmt=null; 
//			int n=0;
//			
//			String sql="insert into addressbook(num, name, phone, addr) values(num_seq.nextval, ?, ?, ?)";
//		//	System.out.println(sql);
//			try {
//				pstmt=conn.prepareStatement(sql);
//				pstmt.setString(1, name); //1번째 물음표
//				pstmt.setString(2, phone); //2번째 물음표
//				pstmt.setString(3, addr); //3번째 물음표
//				n=pstmt.executeUpdate(); //데이터 베이스 쪽으로 전달
//				
//				if(n>0) {
//					conn.commit();
//					System.out.println(n+"건의 데이터가 추가되었습니다");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} try {
//				conn.rollback();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}//end of addressInsert()
//
//// Statement 객체 사용-- statement는 물음표사용 x 값 다 입력 ('') 또는 '"+ +"' 사용 
//	public void addressInsert(String name, String phone, String addr) {
//		Statement stmt=null;
//		int n=0;
//		String sql="insert into addressbook(num, name, phone, addr) values(num_seq.nextval,'"+name+"','"+phone+"','"+addr+"')";
//		//		System.out.println(sql);
//		try {
//				stmt=conn.createStatement();
//				n=stmt.executeUpdate(sql);
//				
//				if(n>0) {
//					conn.commit();
//					System.out.println(n+"건의 데이터가 추가되었습니다");
//				}
//		} catch (SQLException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}finally {
//			if(stmt!=null)
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
//	}
	//PreparedStatement 사용
	public void addressUpdate(int num, String phone, String addr) {
		PreparedStatement pstmt=null;
		int n=0;
		
		String sql="update addressbook set phone=?, addr=? where num=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, addr);
			pstmt.setInt(3, num );
			n=pstmt.executeUpdate();
			
			if(n>0) {
				conn.commit();
				System.out.println(n+"건의 데이터가 수정되었습니다");
			}
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			
				try {if(pstmt!=null)		pstmt.close();
				} catch (SQLException e) {
					System.out.println("pstmt객체 닫다가 실패");
					e.printStackTrace();
				}
		}
	}
	
//	//Statement 객체 사용
//	public void addressUpdate(int num, String phone, String addr) {
//		Statement stmt=null;
//		int n=0;
//		
//		String sql="update addressbook set phone='"+phone+"',addr='"+addr+"' where num=5";
//		//		System.out.println(sql);
//			try {
//				stmt=conn.createStatement();
//				n=stmt.executeUpdate(sql);
//				
//				if(n>0) {
//					conn.commit();
//					System.out.println(n+"건의 데이터가 수정되었습니다");
//				}
//			} catch (SQLException e2) {
//				try {
//					conn.rollback();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				e2.printStackTrace();
//			}finally {
//					try {if(stmt!=null)		stmt.close();
//					} catch (SQLException e) {
//						System.out.println("stmt객체 닫다가 실패");
//						e.printStackTrace();
//					}
//				}
//	}
}// end of class


	
	

