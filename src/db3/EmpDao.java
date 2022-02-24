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
	public List<EmpEntity> addressList() { // 그림의 3
		Connection conn=getConnection();
		Statement stmt=null;
		ResultSet rs=null; //null을 사용할 것
		EmpEntity entity=null;//null을 사용할 것
		List<EmpEntity> list=null;
		String sql="select * from addressbook order by num";
		
		
			try {
				stmt=conn.createStatement();
				rs=stmt.executeQuery(sql);
//			if(rs!=null) 
//				System.out.println("데이터 가져오기 성공");
			list=new ArrayList<EmpEntity>();	
			
			while(rs.next()) {
				//엔티티에 저장-> 리스트에 담아서 리턴
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
			//	System.out.println(n+"건의 데이터가 추가되었습니다");
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
				
				entity.setNum(rs.getInt("num"));//rs에서 추출한 데이터를 entity에 대입
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
		return entity; //-------수행
	}//end of addressSearch
	
	public int addressUpdate(String name, String phone, String addr) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		int n=0;
		
		String sql="update addressbook set phone=?, addr=? where name=?"; //where 전에 ,찍지 않도록 주의
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
