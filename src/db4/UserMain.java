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
	}

}
