package db5;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static db5.JdbcTemplate.*;


//DB¿¬µ¿
public class StudentDao {
	public String insertStudent(StudentEntity entity) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		String sql="insert into student(num,name,kor,eng,mat) values(n_sql.nextval,?,?,?,?)";
		int n =0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getKor());
			pstmt.setInt(3, entity.getEng());
			pstmt.setInt(4, entity.getMat());
			n = pstmt.executeUpdate();
			
			if(n>0) {
				commit(conn);
			}
		
		} catch (SQLException e) {
			rollback(conn);
		}finally {
			close(conn);
			close(pstmt);
		}
		
		return entity.getName();
	}//end of insertStudent
	
    public StudentEntity getStudent(String name) {
    	Connection conn=getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		StudentEntity entity=null;
		
		String sql="select * from student where name='"+name+"'";
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				entity= new StudentEntity();
				
				entity.setNum(rs.getInt("num"));
				entity.setName(rs.getString("name"));
				entity.setKor(rs.getInt("kor"));
				entity.setEng(rs.getInt("eng"));
				entity.setMat(rs.getInt("mat"));
			}	
		} catch (SQLException e) {
			rollback(conn);
		}finally {
			close(conn);
			close(rs);
			close(stmt);
		}
		
		return entity;
		
		
    }//end of getStudent
    
    public List<StudentEntity> getStudentList(){
    	Connection conn=getConnection();
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	List<StudentEntity> list=new ArrayList<StudentEntity>();
    	StudentEntity entity=null;
    	
    	String sql="select * from student order by num";
    	
    	try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				entity=new StudentEntity();
				entity.setNum(rs.getInt("num"));
				entity.setName(rs.getString("name"));
				entity.setKor(rs.getInt("kor"));
				entity.setEng(rs.getInt("eng"));
				entity.setMat(rs.getInt("mat"));
				entity.setTot(entity.getKor()+entity.getEng()+entity.getMat());
				entity.setAvg(entity.getTot()/3.0);
				
				list.add(entity);
			}
		} catch (SQLException e) {
			rollback(conn);
		}finally {
			close(conn);
			close(rs);
			close(pstmt);
		}
    	
    	return list;
    }//end of getStudentList
}
