package javaBasic;

import java.util.Scanner;

public class Topic_08_Break_Continue {
	public static void main(String[] arg) {
		// Declaration
		Scanner sc = new Scanner(System.in);
		String input = "";
		int month;

		// 1. Insert name of browser. Print browser's name and skip if its name is IE
		System.out.println("1. Insert name of browser. Print browser's name and skip if its name is IE");
		while (true) {
			System.out.print("name: ");
			input = sc.nextLine();
			if (!input.contains("IE"))
				System.out.println(input);
			else
				break;
		}

		// 2. Insert month. Print the name of month, press 0 to exit
		System.out.println("2. Insert month. Print the name of month, press 0 to exit");
		while (true) {
			System.out.print("month: ");
			month = sc.nextInt();
			if (month < 0 && month > 12)
				continue;
			if (month == 0)
				break;
			switch (month) {
			case 1:
				System.out.println("JAN");
				break;
			case 2:
				System.out.println("FEB");
				break;
			case 3:
				System.out.println("MAR");
				break;
			case 4:
				System.out.println("APR");
				break;
			case 5:
				System.out.println("MAY");
				break;
			case 6:
				System.out.println("JUN");
				break;
			case 7:
				System.out.println("JUL");
				break;
			case 8:
				System.out.println("AUG");
				break;
			case 9:
				System.out.println("SEP");
				break;
			case 10:
				System.out.println("OCT");
				break;
			case 11:
				System.out.println("NOV");
				break;
			case 12:
				System.out.println("DEC");
				break;
			}
		}

		sc.close();
	}
}
