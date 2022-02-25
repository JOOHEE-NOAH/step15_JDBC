package db4;

import java.util.List;

public class UserMain {

	public static void main(String[] args) {
		UserDao dao= new UserDao();
		//전체 목록 조회
		List<UserEntity> list=dao.userList();
		for(UserEntity entity:list) {
			System.out.print(entity.getNum()+"\t");
			System.out.print(entity.getId()+"\t");
			System.out.print(entity.getPasswd()+"\t");
			System.out.print(entity.getName()+"\t");
			System.out.print(entity.getScore()+"\n");
			
		}
		System.out.println("======================");
		
		UserEntity entity=dao.selectUser("bb");
		System.out.print(entity.getNum()+"\t");
		System.out.print(entity.getId()+"\t");
		System.out.print(entity.getPasswd()+"\t");
		System.out.print(entity.getName()+"\t");
		System.out.print(entity.getScore()+"\n");		
		
		System.out.println("*************************");
		
//		//추가하기
//		int n=dao.insertUser(new UserEntity("dd","3333","choi",88.63));
//		
//		if(n>0)
//			System.out.println(n+"건의 데이터가 추가되었습니다");
//			else 
//				System.out.println("데이터 추가에 실패하였습니다");
//		
		
	}

}
