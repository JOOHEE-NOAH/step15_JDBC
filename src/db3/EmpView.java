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
			System.out.println("����� �����Ͱ� �����....");
		}
	}
	
	public void addressInsert(int n) {
		if(n>0)
		System.out.println(n+"���� �����Ͱ� �߰��Ǿ����ϴ�");
		else System.out.println("������ �߰��� �����Ͽ����ϴ�");
	}
	public void addressSearch(EmpEntity entity) {
		if(entity!=null) {
			System.out.print(entity.getNum()+"\t");
			System.out.print(entity.getName()+"\t");
			System.out.print(entity.getPhone()+"\t");
			System.out.print(entity.getAddr()+"\n");
		}else {
			System.out.println("ã�� ����� �����...");
		}
	}
}
