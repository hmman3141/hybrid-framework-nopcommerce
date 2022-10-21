package javaBasic;

import java.util.Scanner;

public class Topic_07_While_DoWhile {
	public static void main(String[] arg) {
		// Declaration
		Scanner sc = new Scanner(System.in);
		int n, a, b, i, total, factorial;

		// 1. Insert integer n. Print even number from n to 100
		System.out.println("1. Input integer n. Print even number from n to 100");
		do {
			System.out.print("n: ");
			n = sc.nextInt();
			if (n > 100)
				System.out.println("Invalid input");
		} while (n > 100);

		while (n <= 100) {
			if (n % 2 == 0)
				System.out.print(n + " ");
			n++;
		}
		System.out.println();

		// 2. Insert integer a and b. Print number that is divisible by 3 and 5 from a
		// to b
		System.out.println("2. Insert integer a and b. Print number that is divisible by 3 and 5");
		System.out.print("a: ");
		a = sc.nextInt();
		System.out.print("b: ");
		b = sc.nextInt();
		while (a <= b) {
			if (a % 3 == 0 && a % 5 == 0)
				System.out.print(a + " ");
			a++;
		}
		System.out.println();

		// 3. Insert integer n. Print total of odd numbers from 0 to n
		System.out.println("3. Insert integer n. Print total of odd numbers from 0 to n");
		System.out.print("n: ");
		n = sc.nextInt();
		i = total = 0;
		while (i <= n) {
			if (i % 2 != 0)
				total += i;
			i++;
		}
		System.out.println("Total = " + total);

		// 4. Insert integer a and b. Print numbers that divisible by 3 from a to b
		System.out.println("4. Insert integer a and b. Print numbers that divisible by 3 from a to b");
		System.out.print("a: ");
		a = sc.nextInt();
		System.out.print("b: ");
		b = sc.nextInt();
		while (a <= b) {
			if (a % 3 == 0)
				System.out.print(a + " ");
			a++;
		}
		System.out.println();

		// 5. Insert integer n. Print factorial of n (n!)
		System.out.println("5. Insert integer n. Print factorial of n (n!)");
		System.out.print("n: ");
		n = sc.nextInt();
		i = factorial = 1;
		while (i <= n) {
			factorial *= i;
			i++;
		}
		System.out.println("Factorial of " + n + " = " + factorial);

		// 6. Print total of even number from 1 to 10
		System.out.println("6. Print total of even number from 1 to 10");
		i = 1;
		total = 0;
		while (i <= 10) {
			if (i % 2 == 0)
				total += i;
			i++;
		}
		System.out.println("Total = " + total);

		sc.close();
	}
}
