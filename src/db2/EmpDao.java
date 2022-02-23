package db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import static db2.JdbcTemplate.*; ���� �ؿ��� ���ص���
import static db2.JdbcTemplate.getConnection; //db1�� DBConnection ����Ʈ �Ѱ�
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
			rs=stmt.executeQuery(sql); //resultset�� ����
			//if(rs!=null) System.out.println("��� ��ȸ ����");
		//while ���� �̿��Ͽ� ��� ������ּ���
			while(rs.next()) { //rs�� �̾Ƴ���
				System.out.print(rs.getInt("num")+"\t"); //rs.getInt(1) -->0���ƴ� �÷� �̸� �ᵵ �ǰ� �ε��� ��ȣ�� �ص� ��.
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
			//�� ����
			pstmt.setString(1, name); //1��° ����ǥ
			pstmt.setString(2, phone); //2��° ����ǥ
			pstmt.setString(3, addr); //3��° ����ǥ
			n=pstmt.executeUpdate();
			
			if(n>0) {
				commit(conn);
				System.out.println(n+"���� �����Ͱ� �߰��Ǿ����ϴ�");
			}
	
		} catch (SQLException e) {
			System.out.println("������ �߰��� �����Ͽ����ϴ�");
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
			while(rs.next()) { //rs�� �̾Ƴ���
				ck=true;
				System.out.print(rs.getInt("num")+"\t"); //rs.getInt(1) -->0���ƴ� �÷� �̸� �ᵵ �ǰ� �ε��� ��ȣ�� �ص� ��.
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
				System.out.println(n+"���� �����Ͱ� �����Ǿ����ϴ�.");
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
				System.out.println(n+"���� �����Ͱ� ���� �Ǿ����ϴ�");
			}
		} catch (SQLException e) {
			System.out.println("������ ������ �����Ͽ����ϴ�");
		}finally {
			close(pstmt);
			close(conn);
			
		}
	}
}//end of class


















