package javaBasic;

import java.util.Scanner;

public class Topic_07_For_ForEach {
	public static void main(String[] arg) {
		// Declaration
		Scanner sc = new Scanner(System.in);
		int n, a, b, total, factorial;

		// 1. Insert integer n. Print list of number from 1 to n
		System.out.println("1. Input integer n. Print list of number from 1 to n");
		System.out.print("n: ");
		n = sc.nextInt();
		for (int i = 1; i <= n; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		// 2. Insert integer a and b. Print list of number from a to b
		System.out.println("2. Input integer a and b. Print list of number from a to b");
		System.out.print("a: ");
		a = sc.nextInt();
		System.out.print("b: ");
		b = sc.nextInt();
		for (int i = a; i <= b; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		// 3. Print total of even number from 1 to 10
		System.out.println("3. Print total of even number from 1 to 10");
		total = 0;
		for (int i = 1; i <= 10; i++) {
			if (i % 2 == 0)
				total += i;
		}
		System.out.println("Total = " + total);

		// 4. Insert integer a and b. Print total of number from a to b
		System.out.println("4. Insert integer a and b. Print total of number from a to b");
		System.out.print("a: ");
		a = sc.nextInt();
		System.out.print("b: ");
		b = sc.nextInt();
		total = 0;
		for (int i = a; i <= b; i++) {
			total += i;
		}
		System.out.println("Total = " + total);

		// 5. Insert integer n. Print total of odd number from 0 to n
		System.out.println("5. Insert integer n. Print total of odd number from 0 to n");
		System.out.print("n: ");
		n = sc.nextInt();
		total = 0;
		for (int i = 0; i <= n; i++) {
			if (i % 2 != 0)
				total += i;
		}
		System.out.println("Total = " + total);

		// 6. Insert integer a and b. Print number that is divisible by 3 from a to b
		System.out.println("6. Insert integer a and b. Print number that is divisible by 3 from a to b");
		System.out.print("a: ");
		a = sc.nextInt();
		System.out.print("b: ");
		b = sc.nextInt();
		for (int i = a; i <= b; i++) {
			if (i % 3 == 0)
				System.out.print(i + " ");
		}
		System.out.println();

		// 7. Insert integer n. Print factorial of n (n!)
		System.out.println("7. Insert integer n. Print factorial of n (n!)");
		System.out.print("n: ");
		n = sc.nextInt();
		factorial = 1;
		for (int i = 1; i <= n; i++) {
			factorial *= i;
		}
		System.out.println("Factorial = " + factorial);
		
		sc.close();
	}
}
