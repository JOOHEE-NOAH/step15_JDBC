package db5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {
	public static Connection getConnection() {
		Connection conn=null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//드라이브 로드 : ojdbc6를 불러오는 것
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "edu", "1234");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static boolean isConnection(Connection conn) {
		boolean valid=true;
		try {
			if(conn==null || conn.isClosed())
				valid=false;
		} catch (SQLException e) {
			valid=true;
		}
		return valid;
	}
	
	//닫아주는 메소드
	public static void close(Connection conn) { //EmpDao로 부터 close(conn) 입려되면 이리로 호출
		if(isConnection(conn)) // 그리고 위의 isConnection을 호출
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void close(ResultSet rs) {
		
			
				try {
					if(rs!=null)	rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	public static void close(Statement stmt) {
		
			try {
				if(stmt!=null)	stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
	public static void commit(Connection conn) {
		if(isConnection(conn)) {// 맨 위의 isConnection을 호출
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}//end of commit	
	public static void rollback(Connection conn) {
		if(isConnection(conn)) {// 맨 위의 isConnection을 호출
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}//end of rollback
	
	
}//end of class
