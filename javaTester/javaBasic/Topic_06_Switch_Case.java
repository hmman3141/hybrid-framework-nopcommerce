package javaBasic;

import java.util.Scanner;

public class Topic_06_Switch_Case {
	public static void main(String[] arg) {
		// Declaration
		Scanner sc = new Scanner(System.in);
		int num, num1, num2, month, year;
		String operator;

		// 1. Insert number between 1 and 10, print number in English
		System.out.println("1. Input number between 1 and 10, print number in English");
		System.out.print("Number: ");
		num = sc.nextInt();
		switch (num) {
		case 1:
			System.out.println("One");
			break;
		case 2:
			System.out.println("Two");
			break;
		case 3:
			System.out.println("Three");
			break;
		case 4:
			System.out.println("Four");
			break;
		case 5:
			System.out.println("Five");
			break;
		case 6:
			System.out.println("Six");
			break;
		case 7:
			System.out.println("Seven");
			break;
		case 8:
			System.out.println("Eight");
			break;
		case 9:
			System.out.println("Nine");
			break;
		case 10:
			System.out.println("Ten");
			break;

		default:
			System.out.println("This is not a valid number");
			break;
		}

		// 2. Insert 2 number and an operator (+,-,*,/,%). Print result
		System.out.println("2. Input 2 number and an operator (+,-,*,/,%). Print result");
		System.out.print("Number 1: ");
		num1 = sc.nextInt();
		System.out.print("Number 2: ");
		num2 = sc.nextInt();
		System.out.print("Operator: ");
		sc.nextLine();
		operator = sc.nextLine();
		switch (operator) {
		case "+":
			System.out.println(num1 + num2);
			break;
		case "-":
			System.out.println(num1 - num2);
			break;
		case "*":
			System.out.println(num1 * num2);
			break;
		case "/":
			if (num2 == 0)
				System.out.println("Denominator cannot be 0");
			else
				System.out.println(num1 / num2);
			break;
		case "%":
			System.out.println(num1 % num2);
			break;

		default:
			System.out.println("This is not a valid operator");
			break;
		}
		
		// 3. Insert month and year. Print total days of month
		System.out.println("3. Input month and year. Print total days of month");
        System.out.print("Month: ");
        month = sc.nextInt();
        System.out.print("Year: ");
        year = sc.nextInt();
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.print("31");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.print("30");
                break;
            case 2:
                if (checkYear(year)) {
                    System.out.print("29");
                } else {
                    System.out.print("28");
                }
                break;
            default:
                System.out.print("Invalid month.");
        }
        sc.close();
	}
	
	public static boolean checkYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
