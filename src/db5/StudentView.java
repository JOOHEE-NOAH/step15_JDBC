package db5;
//����� ���

import java.util.List;

public class StudentView {
	
	public void insertStudent(String name) {
		if(name==null) {
			System.out.println("���� �Է¿���!! �ٽ� �Է��Ͻÿ�");
		}else {
			System.out.println(name+"���� �߰� �Ǿ����ϴ�");
		}
			
	}
	
	public void getStudent(StudentEntity entity) {
		if(entity!=null) {
			System.out.print("�̸� : "+entity.getName()+"\t");
			System.out.print("���� : "+entity.getTot()+"\t");
			System.out.printf("��� : %.2f\n",entity.getAvg());
		}else {
			System.out.println("ã�� ����� �����...");
		}
	}
	public void getStudentList(List<StudentEntity> list) {
		if(list!=null) {
		for(StudentEntity entity: list) {
			System.out.print("�̸� :"+entity.getName()+"\t");
			System.out.print("���� :"+entity.getKor()+"\t");
			System.out.print("���� :"+entity.getEng()+"\t");
			System.out.print("���� :"+entity.getMat()+"\t");
			System.out.print("���� :"+entity.getTot()+"\t");
			System.out.printf("��� : %.2f\n",entity.getAvg());
		}
		
	}else {
		System.out.println("����� �����Ͱ� �����....");
	}
	}
	
}
