package db4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static db2.JdbcTemplate.close;
import static db3.JdbcTemplate.getConnection;
import static db4.JdbcTemplate.*;

public class UserDao {
	public List<UserEntity> userList(){
		List<UserEntity> list=new ArrayList<UserEntity>();
		Connection conn= getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		UserEntity entity=null;
		
		String sql="select * from userinfo order by num";
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				entity=new UserEntity();
				entity.setNum(rs.getInt("num"));
				entity.setId(rs.getString("id"));
				entity.setPasswd(rs.getString("passwd"));
				entity.setName(rs.getString("name"));
				entity.setScore(rs.getDouble("score"));
				list.add(entity);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn);
			close(rs);
			close(stmt);
		}
		
		return list;
	}//end of userList
	
//	--selectUser Statement ����
	public UserEntity selectUser(String id) {
		Connection conn=getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		UserEntity entity=null;
		
		String sql="select * from userinfo where id='"+id+"'";
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql); // statement ����� �Ÿ� (sql);, preparedstatement ����� �Ÿ�();
			
			if(rs.next()) {
				entity=new UserEntity();
				
				entity.setNum(rs.getInt("num"));
				entity.setId(rs.getString("id"));
				entity.setPasswd(rs.getString("passwd"));
				entity.setName(rs.getString("name"));
				entity.setScore(rs.getDouble("score"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn);
			close(rs);
			close(stmt);
		}
		return entity;
	}
//	--selectUser PreparedStatment ����
//	public UserEntity selectUser(String id) {
//	      
//	      Connection conn=getConnection();
//	      PreparedStatement pstmt=null;
//	      ResultSet rs = null;
//	      UserEntity entity = null;
//	      
//	      String sql="select * from userinfo where id=?";
//	      
//	      try {
//	         pstmt=conn.prepareStatement(sql);
//	         pstmt.setString(1, id);
//	         rs = pstmt.executeQuery(); // ()�ȿ� sql ������ statement ������ prepareStatement
//	         
//	         
//	         if (rs.next()) {
//	            entity = new UserEntity();
//	            
//	            entity.setNum(rs.getInt("num")); 
//	            entity.setId(rs.getString("id"));
//	            entity.setPasswd(rs.getString("passwd"));
//	            entity.setName(rs.getString("name"));
//	            entity.setScore(rs.getDouble("score"));
//	         }
//	         
//	         
//	      } catch (SQLException e) {
//	         // TODO Auto-generated catch block
//	         e.printStackTrace();
//	      }
//	      
//	      return entity;
//	   }
	
}
