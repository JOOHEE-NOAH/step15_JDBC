package db3;

import java.util.List;

public class EmpView {
	public void addressList(List<EmpEntity> list) {
		if(list!=null) {
			for(EmpEntity entity: list) {
				System.out.print(entity.getNum()+"\t");
				System.out.print(entity.getName()+"\t");
				System.out.print(entity.getPhone()+"\t");
				System.out.print(entity.getAddr()+"\n");
			}
		}else {
			System.out.println("출력할 데이터가 없어요....");
		}
	}
	
	public void addressInsert(int n) {
		if(n>0)
		System.out.println(n+"건의 데이터가 추가되었습니다");
		else System.out.println("데이터 추가에 실패하였습니다");
	}
	public void addressSearch(EmpEntity entity) {
		if(entity!=null) {
			System.out.print(entity.getNum()+"\t");
			System.out.print(entity.getName()+"\t");
			System.out.print(entity.getPhone()+"\t");
			System.out.print(entity.getAddr()+"\n");
		}else {
			System.out.println("찾는 사람이 없어요...");
		}
	}
	public void addressUpdate(int n) {
		if(n>0)
		System.out.println(n+"건의 데이터가 수정되었습니다");
		else System.out.println("데이터 수정에 실패하였습니다");
	}
	
	public void addressDelete(int n) {
		if(n>0)
		System.out.println(n+"건의 데이터가 삭제되었습니다");
		else System.out.println("데이터 삭제에 실패하였습니다");
	}
	
}
