package db2;

import java.util.Scanner;

/* 1.��ü 2.�߰� 3.���� 4.���� 5.�˻� 6.����
* �����ϼ���(1-6):1
* aaa 010-111-1111 ����
*/
public class EmpMain {
	EmpDao dao=null;
	Scanner sc=null;
	
	public EmpMain() {
	sc=new Scanner(System.in);
	dao=new EmpDao();
	}
	
	public void menu() {
		int n=0;
		String name=null, phone=null, addr=null;
		
		while(true) {
			System.out.print("1.��ü 2.�߰� 3.���� 4.���� 5.�˻� 6.����\n�����ϼ���(1-6):");
			n=sc.nextInt();
			
			switch(n) {
			case 1 :
				dao.addressList();
				break;
			case 2 :
				
				System.out.print("�̸� �Է�: ");		name=sc.next();
				System.out.print("��ȭ �Է�: ");		phone=sc.next();
				System.out.print("�ּ� �Է�: ");		addr=sc.next();
				
				dao.addressInsert(name,phone,addr);
				break;
				
			case 6 :
				sc.close();
				System.out.println("***** �����մϴ� *****");
				System.exit(0);
				
			default : System.out.println("�Է°��� ������ �ֱ���...");	
				
			}
		}
	}
	
	public static void main(String[] args) {
		new EmpMain().menu();

	}

}
