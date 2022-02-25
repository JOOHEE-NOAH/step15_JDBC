package db5;

import java.util.Scanner;

public class StudentMain {
	Scanner sc=null;
	StudentBiz biz=null;
	StudentEntity entity=null;
	
	public StudentMain() {
		sc=new Scanner(System.in);
		biz=new StudentBiz();
		entity= new StudentEntity();
	}
	
	public void menu() {
		int n=0;
		String name=null;
		int num=0, kor=0, eng=0, mat=0;
		
		while(true) {
			System.out.print("1.�߰� 2.�˻� 3.��ü���� 4.����\n����(1-4) : ");
			n=sc.nextInt();
			
			switch(n) {
			case 1 : System.out.print("�̸��� �Է� : "); 	this.entity.setName(sc.next());
					System.out.print("�������� �Է� : ");	this.entity.setKor(sc.nextInt());
					System.out.print("�������� �Է� : ");	this.entity.setEng(sc.nextInt());
					System.out.print("�������� �Է� : ");	this.entity.setMat(sc.nextInt());
					
					biz.insertStudent(entity);
					break;
			case 2 :System.out.print("�̸��� �Է��Ͻÿ� : ");	name=sc.next();
					biz.getStudent(name);
					break;
			case 3 : biz.getStudentList();
					break;
						
			case 4 :sc.close();
					System.out.println("** ��� �ϼ̽��ϴ� **");
					System.exit(0);
					break;
			default : System.out.println("�Է°��� ������ �ֱ���..."); break;
				
			}
			
		}//while
	}//menu
	
	public static void main(String[] args) {
		new StudentMain().menu();
	}

}
