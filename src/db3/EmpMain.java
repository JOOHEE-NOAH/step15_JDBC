package db3;

import java.util.Scanner;

public class EmpMain {
	Scanner sc=null;
	EmpBiz biz=null;
	public EmpMain() {
	sc=new Scanner(System.in);
	biz=new EmpBiz();
}	
	
	public void menu() {
		int n=0;
		
		while(true) {
		System.out.print("1.��ü 2.�߰� 3.���� 4.���� 5.�˻� 6.����\n�����ϼ���(1-6):");
		n=sc.nextInt();
		
		switch(n) {
		case 1 : biz.addressList();
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
