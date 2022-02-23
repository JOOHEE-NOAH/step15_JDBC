package db3;

import java.util.List;

public class EmpBiz {
	public void addressList() {
		//dao의 addressList()호출
		EmpDao dao=new EmpDao();
		List<EmpEntity> list=dao.addressList(); //그림의 2번
		
		EmpView view=new EmpView();
		view.addressList(list);
	}

}
