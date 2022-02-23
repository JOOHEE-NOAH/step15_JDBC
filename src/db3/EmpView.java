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
}
