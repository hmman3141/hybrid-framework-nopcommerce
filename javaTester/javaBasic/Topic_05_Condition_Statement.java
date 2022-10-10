package javaBasic;

import java.util.Scanner;

public class Topic_05_Condition_Statement {
	public static void main(String[] arg) {
		// Declaration
		int n, a, b, max;
		String name1, name2;
		Scanner sc = new Scanner(System.in);

		// 1. Input integer n. Print even if n is even, odd if n is odd
		System.out.println("1. Input integer n. Print even if n is even, odd if n is odd");
		System.out.print("n: ");
		n = sc.nextInt();
		if (n % 2 == 0) {
			System.out.println("Even");
		} else {
			System.out.println("Odd");
		}

		// 2. Input integer a and b. Print 'a is greater than or equal to b' if a > b, 'a is lower than b' if a < b
		System.out.println("2. Input integer a and b. Print 'a is greater than or equal to b' if a > b, 'a is lower than b' if a < b");
		System.out.print("a: ");
		a = sc.nextInt();
		System.out.print("b: ");
		b = sc.nextInt();
		System.out.println((a >= b) ? "a is greater than or equal to b" : "a is lower than b");
		
		// Clear enter press
		sc.nextLine();

		// 3. Input name of two people. Print true if their name is the same
		System.out.println("3. Input name of two people. Print true if their name is the same");
		System.out.print("Name 1: ");
		name1 = sc.nextLine();
		System.out.print("Name 2: ");
		name2 = sc.nextLine();
		if (name1.equalsIgnoreCase(name2)) {
			System.out.println("Their name is the same");
		} else {
			System.out.println("Their name is not the same");
		}

		// 4. Input 3 integer. Print the highest
		System.out.println("4. Input 3 integer. Print the highest");
		System.out.print("Number 1: ");
		n = sc.nextInt();
		System.out.print("Number 2: ");
		a = sc.nextInt();
		System.out.print("Number 3: ");
		b = sc.nextInt();
		max = n;
		max = (max < a) ? a : max;
		max = (max < b) ? b : max;
		System.out.println(max);

		// 5. Input integer a. Check if a is between 10 and 100
		System.out.println("5. Input integer a. Check if a is between 10 and 100");
		System.out.print("a: ");
		a = sc.nextInt();
		System.out.println((10 <= a && a <= 100) ? "a is between 10 and 100" : "a is not between 10 and 100");

		// 6. Input score of a student. Print type of score corresponding to score
		System.out.println("6. Input score of a student. Print type of score corresponding to score");
		System.out.print("Score: ");
		n = sc.nextInt();
		if (n < 5) {
			System.out.println("D");
		} else if (n < 7.5) {
			System.out.println("C");
		} else if (n < 8.5) {
			System.out.println("B");
		} else {
			System.out.println("A");
		}

		// 7. Input month. Print total days of month
		System.out.println("7. Input month. Print total days of month");
		System.out.print("Month: ");
		n = sc.nextInt();
		if (n == 2) {
			System.out.println("28 or 29");
		} else if (n == 1 || n == 3 || n == 5 || n == 7 || n == 8 || n == 10 || n == 12) {
			System.out.println("31");
		} else {
			System.out.println("30");
		}

		sc.close();
	}
}
