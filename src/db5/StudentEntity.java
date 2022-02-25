package db5;

public class StudentEntity {
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private double avg;
	
	public StudentEntity() {
		// TODO Auto-generated constructor stub
	}

	public StudentEntity(String name, int kor, int eng, int mat) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}	
	public StudentEntity(int num, String name, int kor, int eng, int mat) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}

	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public int getKor() {
		return kor;
	}

	public int getEng() {
		return eng;
	}

	public int getMat() {
		return mat;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}
	
	public int getTot() {
		tot=(kor+ eng+ mat);
		return tot;
	}
	
	public double getAvg() {
		avg=(double)(kor+ eng+ mat)/3.0;
		return avg;
	}
	public void setTot(int tot) {
		this.tot= tot;
	}
		public void setAvg(double tot) {
		this.avg= avg;
	}
	
	
}
