package db3;

import java.util.List;
//계산 또는 유효성 검사 수행 영역
public class EmpBiz {
	public void addressList() {
		//dao의 addressList()호출
		EmpDao dao=new EmpDao();
		List<EmpEntity> list=dao.addressList(); //그림의 2번
		
		EmpView view=new EmpView();
		view.addressList(list);
	}
	
	public void addressInsert(EmpEntity entity) {
		EmpDao dao=new EmpDao();
		int n=dao.addressInsert(entity);
		
		EmpView view=new EmpView();
		view.addressInsert(n);
	}
	public boolean addressSearch(String name) {
		boolean ck=false;
		
		EmpDao dao=new EmpDao();
		EmpEntity entity=dao.addressSearch(name); //--> 리턴된 entity가 와있는거랑 같음
		
		EmpView view= new EmpView();
		view.addressSearch(entity);
		ck=(entity!=null)? true:false;
		
		
		return ck;
	}

}
