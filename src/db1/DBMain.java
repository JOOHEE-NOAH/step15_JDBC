package db1;

public class DBMain {

	public static void main(String[] args) {
		DBConnection db=new DBConnection();
	
	//������ �����ϱ�
	db.addressDelete(4);	
		
	//---------3------------	
	//������ �����ϱ�
//		db.addressUpdate(2, "000-000-0000", "�Ź���");
//		db.addressUpdate(5, "000-000-0000", "�쵵");
	
		
	//---------------2-------------------------	
		//������ �߰�
	//	db.addressInsert("ddd","010-444-4444","������");
	//	db.addressInsert("eee","010-555-5555","��ɵ�");
		
//		-----------------1--------------------
		// ��ü ��� ��ȸ
		db.addressList();
	}
	
}
