package db3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db2.JdbcTemplate.close;
import static db3.JdbcTemplate.*;

public class EmpDao {
	public List<EmpEntity> addressList() { // �׸��� 3
		Connection conn=getConnection();
		Statement stmt=null;
		ResultSet rs=null; //null�� ����� ��
		EmpEntity entity=null;//null�� ����� ��
		List<EmpEntity> list=null;
		String sql="select * from addressbook order by num";
		
		
			try {
				stmt=conn.createStatement();
				rs=stmt.executeQuery(sql);
//			if(rs!=null) 
//				System.out.println("������ �������� ����");
			list=new ArrayList<EmpEntity>();	
			
			while(rs.next()) {
				//��ƼƼ�� ����-> ����Ʈ�� ��Ƽ� ����
				entity=new EmpEntity();
				entity.setNum(rs.getInt("num"));
				entity.setName(rs.getString("name"));
				entity.setPhone(rs.getString("phone"));
				entity.setAddr(rs.getString("addr"));
				
				list.add(entity);
			}
			
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
	
}//end of addressList
	
	public int addressInsert(EmpEntity entity) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		int n=0;
		
		String sql="insert into addressbook(num, name, phone, addr) values(num_seq.nextval, ?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getPhone());
			pstmt.setString(3, entity.getAddr());
			n=pstmt.executeUpdate();
			
			if(n>0) {
				commit(conn);
			//	System.out.println(n+"���� �����Ͱ� �߰��Ǿ����ϴ�");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}//end of addressInsert
	
	public EmpEntity addressSearch(String name) {
		Connection conn=getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		EmpEntity entity=null;
		
		String sql="select * from addressbook where name='"+name+"'";
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				entity= new EmpEntity();
				
				entity.setNum(rs.getInt("num"));//rs���� ������ �����͸� entity�� ����
				entity.setName(rs.getString("name"));
				entity.setPhone(rs.getString("phone"));
				entity.setAddr(rs.getString("addr"));
				
			//	System.out.println(rs.getInt("num"));
			
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn);
			close(rs);
			close(stmt);
		}
		return entity; //-------����
	}//end of addressSearch
	
	public int addressUpdate(String name, String phone, String addr) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		int n=0;
		
		String sql="update addressbook set phone=?, addr=? where name=?"; //where ���� ,���� �ʵ��� ����
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, addr);
			pstmt.setString(3, name);
			n=pstmt.executeUpdate();
			
			if(n>0) {
				commit(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn);
			close(pstmt);
		}
		
		return n;
	}//end of addressUpdate
	
	public int addressDelete(String name) {
		int n=0;
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		
		String sql="delete from addressbook where name=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			n=pstmt.executeUpdate();
			
			if(n>0) {
			commit(conn);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		
		return n;
	}



}	
