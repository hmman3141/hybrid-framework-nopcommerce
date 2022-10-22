package javaBasic;

public class Topic_09_Array {
	public static void main(String[] arg) {
		// Declaration
		int max, total;
		float average;
		int[] ex_1_2 = { 2, 7, 6, 8, 9 };
		int[] ex_3 = { 2, 7, 6, 8, 9, 16, 17, 20 };
		int[] ex_4_5 = { 3, -7, 2, 5, 9, -6, 10, 12 };
		int[] ex_6 = { 3, 5, 7, 30, 10, 5, 8, 23, 0, -5 };

		// 1. Give an array of integer { 2, 7, 6, 8, 9 }. Print the maximum value of
		// array
		System.out.println("1. Give an array of integer { 2, 7, 6, 8, 9 }. Get maximum value of array");
		max = ex_1_2[0];
		for (int i = 1; i < ex_1_2.length; i++) {
			if (max < ex_1_2[i])
				max = ex_1_2[i];
		}
		System.out.println(max);

		// 2. Give an array of integer { 2, 7, 6, 8, 9 }. Print the total of first value
		// and
		// last value
		System.out.println("2. Give an array of integer { 2, 7, 6, 8, 9 }. Get total of first value and last value");
		total = ex_1_2[0] + ex_1_2[ex_1_2.length - 1];
		System.out.println(total);

		// 3. Give an array of integer { 2, 7, 6, 8, 9, 16, 17, 20 }. Print the even
		// number of array
		System.out.println("3. Give an array of integer { 2, 7, 6, 8, 9, 16, 17, 20 }. Print the even number of array");
		for (int i = 0; i < ex_3.length; i++) {
			if (ex_3[i] % 2 == 0)
				System.out.print(ex_3[i] + " ");
		}
		System.out.println();

		// 4. Give an array of integer { 2, 7, 6, 8, 9, 16, 17, 20 }. Print total of odd
		// number that greater than 0
		System.out.println(
				"4. Give an array of integer { 2, 7, 6, 8, 9, 16, 17, 20 }. Print total of odd number that greater than 0");
		total = 0;
		for (int i = 0; i < ex_4_5.length; i++) {
			if (ex_3[i] % 2 != 0 && ex_3[i] > 0)
				total += ex_3[i];
		}
		System.out.println(total);

		// 5. Give an array of integer { 2, 7, 6, 8, 9, 16, 17, 20 }. Print number
		// between 0 and 10
		System.out.println("5. Give an array of integer { 2, 7, 6, 8, 9, 16, 17, 20 }. Print number between 0 and 10");
		for (int i = 0; i < ex_4_5.length; i++) {
			if (ex_4_5[i] >= 0 && ex_4_5[i] <= 10)
				System.out.print(ex_4_5[i] + " ");
		}
		System.out.println();

		// 6. Give an array of integer { 3, 5, 7, 30, 10, 5, 8, 23, 0, -5 }. Print total
		// and average
		System.out.println("6. Give an array of integer { 3, 5, 7, 30, 10, 5, 8, 23, 0, -5 }. Print total and average");
		total = 0;
		for (int i = 0; i < ex_6.length; i++) {
			total += ex_6[i];
		}
		average = total / ex_6.length;
		System.out.println("Total: " + total);
		System.out.println("Average: " + average);

		// 7. Print student properties: ID, Name, Age, Score. Define a class and its
		// constructor. Write a function displayed() to show properties
		System.out.println(
				"7. Print student properties: ID, Name, Age, Score. Define a class and its constructor. Write a function displayed() to show properties");
		Student[] students = new Student[1];
		for (Student student : students) {
			student = new Student();
			student.displayed();
		}
	}

	public static class Student {
		String ID;
		String Name;
		int Age;
		int Score;

		public void displayed() {
			System.out.println("ID: " + ID);
			System.out.println("Name: " + Name);
			System.out.println("Age: " + Age);
			System.out.println("Score: " + Score);
		}

		Student() {
			ID = Name = "Abc";
			Age = Score = 10;
		}
	}
}
