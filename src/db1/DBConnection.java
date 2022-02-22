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
		Class.forName("oracle.jdbc.driver.OracleDriver"); //1. ����̹� �ε�.Ŭ������ ������ �������ִ� �Լ�
		conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "edu", "1234"); //2. ���� ��ü ����
		//���� ��μ���. db���� edu ���콺 ������ properties-> connection url
		//System.out.println("���Ἲ��");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		//System.out.println("�������");
		e.printStackTrace();
	}
}
	public void addressList() {
		PreparedStatement pstmt=null;
		ResultSet rs=null; //4. ����� ��ȯ
		
		try {
			String sql="SELECT * FROM ADDRESSBOOK ORDER BY NUM DESC"; //3. sql ���� ����
			pstmt=conn.prepareStatement(sql);// sql ������ ������ ������ ���̽� ������ �����ϰ�, ������� preparedStatement��ü�� ������
			rs=pstmt.executeQuery(); // ����� result set�� ������. // executeQuery: select ���忡�� ��� 
			
		//	if(rs!=null)
		//		System.out.println("������ �������� ����");
			while(rs.next()) { //rs�� �̾Ƴ���
				System.out.print(rs.getInt("num")+"\t"); //rs.getInt(1) -->0���ƴ� �÷� �̸� �ᵵ �ǰ� �ε��� ��ȣ�� �ص� ��.
				System.out.print(rs.getString("name")+"\t");
				System.out.print(rs.getString("phone")+"\t");
				System.out.println(rs.getString("addr"));
			}
			
		} catch (SQLException e) {
		//	System.out.println("������ �������� ����");
			e.printStackTrace();
		}finally {
				try {if(rs!=null)		rs.close(); //5.���ᰴü ����
					if(pstmt!=null)		pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}//end of addressList()
	
	
//	//PreparedStatement ��ü ���			
//		public void addressInsert(String name, String phone, String addr) {
//			PreparedStatement pstmt=null; 
//			int n=0;
//			
//			String sql="insert into addressbook(num, name, phone, addr) values(num_seq.nextval, ?, ?, ?)";
//		//	System.out.println(sql);
//			try {
//				pstmt=conn.prepareStatement(sql);
//				pstmt.setString(1, name); //1��° ����ǥ
//				pstmt.setString(2, phone); //2��° ����ǥ
//				pstmt.setString(3, addr); //3��° ����ǥ
//				n=pstmt.executeUpdate(); //������ ���̽� ������ ����
//				
//				if(n>0) {
//					conn.commit();
//					System.out.println(n+"���� �����Ͱ� �߰��Ǿ����ϴ�");
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
//// Statement ��ü ���-- statement�� ����ǥ��� x �� �� �Է� ('') �Ǵ� '"+ +"' ��� 
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
//					System.out.println(n+"���� �����Ͱ� �߰��Ǿ����ϴ�");
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
	//PreparedStatement ���
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
				System.out.println(n+"���� �����Ͱ� �����Ǿ����ϴ�");
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
					System.out.println("pstmt��ü �ݴٰ� ����");
					e.printStackTrace();
				}
		}
	}
	
//	//Statement ��ü ���
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
//					System.out.println(n+"���� �����Ͱ� �����Ǿ����ϴ�");
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
//						System.out.println("stmt��ü �ݴٰ� ����");
//						e.printStackTrace();
//					}
//				}
//	}
}// end of class


	
	

