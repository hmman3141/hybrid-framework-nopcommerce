package javaBasic;

public class Topic_02_Exercise {
	public int number = 5;

	public static void main(String[] arg) {
		int x = 10;
		int y = x;
		System.out.println("Before assign");
		System.out.println("x = " + x);
		System.out.println("y = " + y);

		y = 12;
		System.out.println("After assign");
		System.out.println("x = " + x);
		System.out.println("y = " + y);

		Topic_02_Exercise topic1 = new Topic_02_Exercise();
		Topic_02_Exercise topic2 = topic1;
		System.out.println("Before assign");
		System.out.println("Number of topic 1 = " + topic1.number);
		System.out.println("Number of topic 2 = " + topic2.number);

		topic2.number = 10;
		System.out.println("Before assign");
		System.out.println("Number of topic 1 = " + topic1.number);
		System.out.println("Number of topic 2 = " + topic2.number);

		// Exercise
		// 1. a = 6, b = 2. Find sum, sub, mul, div
		System.out.println("1. a = 6, b = 2. Find sum, sub, mul, div");
		int a = 6, b = 2;
		System.out.println("Sum: " + (a + b));
		System.out.println("Sub: " + (a - b));
		System.out.println("Mul: " + (a * b));
		System.out.println("Div: " + ((float) a / b));
		
		// 2. Length = 7.5, width = 3.8, find area
		System.out.println("2. Length = 7.5, width = 3.8, find area");
		float length = 7.5f, width = 3.8f;
		System.out.println("Area: " + (length * width));
		
		// 3. name = "Automation Testing". Print "Hello Automation Testing" using name
		System.out.println("3. name = \"Automation Testing\". Print \"Hello Automation Testing\" using name");
		String name = "Automation Testing";
		System.out.println("Hello " + name);
	}
}
