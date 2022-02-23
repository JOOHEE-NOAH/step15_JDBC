package db3;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}	
