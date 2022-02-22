package db1;

public class DBMain {

	public static void main(String[] args) {
		DBConnection db=new DBConnection();
	
	//데이터 삭제하기
//	db.addressDelete(4);	
		
	//---------3------------	
	//데이터 수정하기
		db.addressUpdate(2, "000-000-0000", "매물도");
//		db.addressUpdate(5, "000-000-0000", "우도");
	
		
	//---------------2-------------------------	
		//데이터 추가
	//	db.addressInsert("ddd","010-444-4444","거제도");
	//	db.addressInsert("eee","010-555-5555","백령도");
		
//		-----------------1--------------------
		// 전체 목록 조회
		db.addressList();
	}
	
}
