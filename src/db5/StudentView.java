package db5;
//결과값 출력

import java.util.List;

public class StudentView {
	
	public void insertStudent(String name) {
		if(name==null) {
			System.out.println("점수 입력오류!! 다시 입력하시오");
		}else {
			System.out.println(name+"님이 추가 되었습니다");
		}
			
	}
	
	public void getStudent(StudentEntity entity) {
		if(entity!=null) {
			System.out.print("이름 : "+entity.getName()+"\t");
			System.out.print("총점 : "+entity.getTot()+"\t");
			System.out.printf("평균 : %.2f\n",entity.getAvg());
		}else {
			System.out.println("찾는 사람이 없어요...");
		}
	}
	public void getStudentList(List<StudentEntity> list) {
		if(list!=null) {
		for(StudentEntity entity: list) {
			System.out.print("이름 :"+entity.getName()+"\t");
			System.out.print("국어 :"+entity.getKor()+"\t");
			System.out.print("영어 :"+entity.getEng()+"\t");
			System.out.print("수학 :"+entity.getMat()+"\t");
			System.out.print("총점 :"+entity.getTot()+"\t");
			System.out.printf("평균 : %.2f\n",entity.getAvg());
		}
		
	}else {
		System.out.println("출력할 데이터가 없어요....");
	}
	}
	
}
