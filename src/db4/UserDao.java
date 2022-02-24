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
	
//	--selectUser Statement 사용시
	public UserEntity selectUser(String id) {
		Connection conn=getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		UserEntity entity=null;
		
		String sql="select * from userinfo where id='"+id+"'";
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql); // statement 사용할 거면 (sql);, preparedstatement 사용할 거면();
			
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
//	--selectUser PreparedStatment 사용시
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
//	         rs = pstmt.executeQuery(); // ()안에 sql 있으면 statement 없으면 prepareStatement
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
	
//end of select User

	public int insertUser(UserEntity entity) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		int n=0;
		
		String sql="insert into userinfo(num, id, passwd, name, score) values(num_seq.nextval, ?,?,?,?)";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, entity.getId());
			pstmt.setString(2, entity.getPasswd());
			pstmt.setString(3, entity.getName());
			pstmt.setDouble(4, entity.getScore());
			n=pstmt.executeUpdate();
			
			if(n>0) {
				commit(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return n;
		
	}// end of insertUser
	
}
