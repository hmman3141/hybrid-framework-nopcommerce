package javaBasic;

public class Topic_04_Casting {
	public int num = 5;
	public static void main(String[] arg) {
		// Primitive type
		// implicit -> No data losing
		byte bNum = 127;
		short sNum = bNum;
		int iNum = sNum;
		long lNum = iNum;
		// explicit -> missing data
		iNum = (int) lNum;
		sNum = (short) iNum;
		bNum = (byte) sNum;
		
		// Reference type
		Topic_04_Casting topic1 = new Topic_04_Casting();
		Topic_04_Casting topic2 = new Topic_04_Casting();
		topic1 = topic2;
	}
}
