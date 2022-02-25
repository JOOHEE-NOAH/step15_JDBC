package db5;

import java.util.List;

//들어온 kor,eng,mat값중 0~100사이의 점수가 아니면 오류리턴 
public class StudentBiz {
	public void insertStudent(StudentEntity entity) {
		int kor=entity.getKor();
		int eng=entity.getEng();
		int mat=entity.getMat();
		String name=null;
		StudentDao dao=new StudentDao();
		StudentView view=new StudentView();

//----------or 연산
//		if(kor>100 || kor<0|| eng>100 ||eng<0 ||mat>100||mat<0) {
//			view.insertStudent(name);
//		}else {
//			name=dao.insertStudent(entity);
//			view.insertStudent(name);
//		}
		
		
		
// ---and연산		
		if(kor<=100 && kor>=0 && eng<=100 && eng>=0 && mat<=100 && mat>=0){
			name=dao.insertStudent(entity);
		}	
		 	view.insertStudent(name);
	
//----나의 답-----		
//		StudentDao dao=new StudentDao();
//		StudentView view=new StudentView();
//		
//		if(entity.getKor()>=0 && entity.getKor()<=100
//			&& entity.getEng()>=0&&entity.getEng()<=100
//			&& entity.getMat()>=0&&entity.getMat()<=100){
//			dao.insertStudent(entity);
//			view.insertStudent(entity.getName());
//		}else {
//			view.insertStudent(null);
//		}
			
		}
	
    public void getStudent(String name) {
    	
    	StudentDao dao=new StudentDao();
    	StudentEntity entity=dao.getStudent(name);
    	
    	StudentView view=new StudentView();
    	view.getStudent(entity);
    }
    public void getStudentList() {
    	StudentDao dao=new StudentDao();
    	List<StudentEntity> list=dao.getStudentList();
    	
    	StudentView view=new StudentView();
    	view.getStudentList(list);
    	}
    }
    
