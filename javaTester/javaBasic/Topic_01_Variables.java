package javaBasic;

public class Topic_01_Variables {
	static int number;
	static final int BROWSER_NUMBER = 1;
	int number2;
	private int number3;
	
	public static void main(String[] args) {
		int number = 0;
		Topic_01_Variables topic1 = new Topic_01_Variables();
		topic1.number2 = 1;
		topic1.setNumber3(2);
		System.out.println(Topic_01_Variables.BROWSER_NUMBER);
		System.out.println(Topic_01_Variables.number);
		System.out.println(number);
	}
	
	public int getNumber3() {
		return number3;
	}
	
	public void setNumber3(int number3) {
		this.number3 = number3;
	}
}
