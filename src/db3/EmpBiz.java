package db3;

import java.util.List;

public class EmpBiz {
	public void addressList() {
		//dao�� addressList()ȣ��
		EmpDao dao=new EmpDao();
		List<EmpEntity> list=dao.addressList(); //�׸��� 2��
		
		EmpView view=new EmpView();
		view.addressList(list);
	}

}
