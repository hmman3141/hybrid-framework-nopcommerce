package javaBasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class Topic_02_Data_Types {
	public static void main(String[] arg) {
		// Primitive type
		// byte
		byte bNum = 127;
		// short
		short sNum = 10000;
		// int
		int iNum = 1000000000;
		// long
		long lNum = 1000000000;
		// float
		float fNum = 1.23f;
		// double
		double dNum = 1.23d;
		// char
		char cValue = 'a';
		// boolean
		boolean bValue = true;

		// Reference type
		// String
		String sValue = "abc";
		// Array
		Integer[] iArray = { 1, 2, 3 };
		Float[] fArray = { 1.3f, 2.2f, 3.5f };
		// Class
		Topic_02_Data_Types topic;
		// Interface
		WebDriver driver;
		// Object
		Object oValue;
		// Collection: List, Set, Queue, Map
		List<Integer> iList = new ArrayList<Integer>();
		Set<Integer> iSet = Set.of(1, 2, 3, 4, 5);
	}
}
