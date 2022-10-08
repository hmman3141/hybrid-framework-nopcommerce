package javaBasic;

import java.util.Scanner;

public class Topic_03_Operator {
	public static void main(String[] arg) {
		// Declaration
		String input;
		String[] strArr;
		int a, b;
		Scanner sc = new Scanner(System.in);

		// Exercise
		// 1. Input name and age. Print "After 15 years, age of {name} will be {age}"
		System.out.println("1. Input name and age. Print \"After 15 years, age of {name} will be {age}\"");
		System.out.print("Name and age: ");
		input = sc.nextLine();
		strArr = input.split(" ");
		System.out.println("After 15 years, age of " + strArr[0] + " will be " + (Integer.parseInt(strArr[1]) + 15));

		// 2. Input a and b. Swap a and b
		// Example: a = 5, b = 6. After swapping: a = 6, b = 5
		System.out.println("2. Input a and b. Swap a and b");
		System.out.print("Input: ");
		input = sc.nextLine();
		strArr = input.split(" ");
		a = Integer.parseInt(strArr[0]);
		b = Integer.parseInt(strArr[1]);
		System.out.println("Before swapping:");
		System.out.println("a = " + a + " / b = " + b);
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println("After swapping:");
		System.out.println("a = " + a + " / b = " + b);

		// 3. Input a and b. Print true if a greater than b and false if b greater than
		// a
		System.out.println("3. Input a and b. Print true if a greater than b and false if b greater than a");
		System.out.print("Input: ");
		input = sc.nextLine();
		strArr = input.split(" ");
		a = Integer.parseInt(strArr[0]);
		b = Integer.parseInt(strArr[1]);
		System.out.print("Result: ");
		if (a > b)
			System.out.println("true");
		else
			System.out.println("false");
		sc.close();
	}
}
