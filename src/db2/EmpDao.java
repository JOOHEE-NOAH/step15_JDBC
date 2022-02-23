package db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import static db2.JdbcTemplate.*; 굳이 밑에꺼 안해도됨
import static db2.JdbcTemplate.getConnection; //db1의 DBConnection 임포트 한거
import static db2.JdbcTemplate.close;
import static db2.JdbcTemplate.commit;
import static db2.JdbcTemplate.rollback;
public class EmpDao {

	public void addressList() {
		String sql="select * from addressbook";
		Connection conn=getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		
		//	System.out.println(conn);
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql); //resultset값 나옴
			//if(rs!=null) System.out.println("결과 조회 성공");
		//while 구문 이용하여 결과 출력해주세요
			while(rs.next()) { //rs값 뽑아내기
				System.out.print(rs.getInt("num")+"\t"); //rs.getInt(1) -->0번아님 컬럼 이름 써도 되고 인덱스 번호로 해도 됨.
				System.out.print(rs.getString("name")+"\t");
				System.out.print(rs.getString("phone")+"\t");
				System.out.println(rs.getString("addr"));
				
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(conn);
		}
	
		}// end of addressList
	
	public void addressInsert(String name, String phone, String addr) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		int n=0;
		
		String sql="insert into addressbook(num, name, phone, addr) values(num_seq.nextval, ?, ?, ?)";
		try {
			pstmt=conn.prepareStatement(sql);
			//값 대입
			pstmt.setString(1, name); //1번째 물음표
			pstmt.setString(2, phone); //2번째 물음표
			pstmt.setString(3, addr); //3번째 물음표
			n=pstmt.executeUpdate();
			
			if(n>0) {
				commit(conn);
				System.out.println(n+"건의 데이터가 추가되었습니다");
			}
	
		} catch (SQLException e) {
			System.out.println("데이터 추가에 실패하였습니다");
			rollback(conn);
		}
	}//end of addressInsert
	
	public boolean addressSearch(String name) {
		boolean ck=false;
		Connection conn=getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from addressbook where name='"+name+"'";
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
//			if(rs!=null)
//				ck=true;
			while(rs.next()) { //rs값 뽑아내기
				ck=true;
				System.out.print(rs.getInt("num")+"\t"); //rs.getInt(1) -->0번아님 컬럼 이름 써도 되고 인덱스 번호로 해도 됨.
				System.out.print(rs.getString("name")+"\t");
				System.out.print(rs.getString("phone")+"\t");
				System.out.println(rs.getString("addr"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn);
			close(rs);
			close(stmt);
		}
		
		return ck;
		
	}
	public void addressUpdate(String name, String phone, String addr) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		int n=0;
		
		String sql="update addressbook set phone=?, addr=? where name=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, addr);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			
			if(n>0) {
				conn.commit();
				System.out.println(n+"건의 데이터가 수정되었습니다.");
			}
			
		} catch (SQLException e) {
			rollback(conn);
		}finally {
			close(conn);
			close(pstmt);
		}
		
	}//end of addressUpdate
	public void addressDelete(String name) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		int n=0;
		String sql="delete from addressbook where name=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			n=pstmt.executeUpdate();
			
			if(n>0) {
				commit(conn);
				System.out.println(n+"건의 데이터가 삭제 되었습니다");
			}
		} catch (SQLException e) {
			System.out.println("데이터 삭제에 실패하였습니다");
		}finally {
			close(pstmt);
			close(conn);
			
		}
	}
}//end of class


















