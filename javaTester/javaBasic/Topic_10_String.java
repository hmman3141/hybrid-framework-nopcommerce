package javaBasic;

import java.util.Scanner;

public class Topic_10_String {
	public static void main(String[] arg) {
		// Declaration
		Scanner sc = new Scanner(System.in);
		String text, character, reverseString;
		String ex_2 = "Automation Testing 345 Tutorials Online 789";
		int count;

		// 1. Insert string. Count total uppercase characters
		System.out.println("1. Insert string. Count total uppercase characters");
		System.out.print("String: ");
		text = sc.nextLine();
		count = 0;
		for (int i = 0; i < text.length(); i++) {
			character = text.substring(i, i + 1);
			if (character.matches("[A-Z]"))
				count++;
		}
		System.out.println("Total: " + count);

		// 2. Giving a string "Automation Testing 345 Tutorials Online 789".
		System.out.println("2. Giving a string 'Automation Testing 345 Tutorials Online 789'");
		System.out.print("- Count total character 'a' in string: ");
		count = 0;
		for (int i = 0; i < ex_2.length(); i++) {
			character = ex_2.substring(i, i + 1);
			if (character.equals("a"))
				count++;
		}
		System.out.println(count);
		
		// Check if string contains 'Testing'
		System.out.print("- Check if string contains 'Testing': ");
		System.out.println(ex_2.contains("Testing") ? "True" : "False");
		
		// Check if string starts with 'Automation'
		System.out.print("- Check if string starts with 'Automation': ");
		System.out.println(ex_2.startsWith("Automation") ? "True" : "False");
		
		// Check if string ends with 'Online'
		System.out.print("- Check if string ends with 'Online': ");
		System.out.println(ex_2.endsWith("Online") ? "True" : "False");

		// Print position of 'Tutorials'
		System.out.print("- Print position of 'Tutorials': ");
		System.out.println(ex_2.indexOf("Tutorials"));
		
		// Replace 'Online' with 'Offline'
		System.out.print("- Replace 'Online' with 'Offline': ");
		System.out.println(ex_2.replace("Online", "Offline"));
		
		// Count total numbers in string
		System.out.print("- Count total numbers in string: ");
		count = 0;
		for (int i = 0; i < ex_2.length(); i++) {
			character = ex_2.substring(i, i + 1);
			if (character.matches("[0-9]"))
				count++;
		}
		System.out.println(count);
		
		// 3. Insert string. Reverse the string
		System.out.println("3. Insert string. Reverse the string");
		System.out.print("String: ");
		text = sc.nextLine();
		reverseString = "";
		for (int i = text.length()-1; i >= 0; i--) {
			reverseString += text.substring(i, i + 1);
		}
		System.out.println(reverseString);
		
		sc.close();
	}
}
