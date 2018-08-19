package com.nzsoft.olympiad.core;

public class TestUtils {

	public static String[] split(String str) {
		return str.split(" ");
	}
	
	public static String[] splitLines(String str) {
		return str.split(System.lineSeparator());
	}
	
	public static int toInt(String str) {
		return Integer.parseInt(str);
	}

	public static int[] toIntArray(String str) {
		return toIntArray(split(str));
	}

	public static int[] toIntArray(String[] str) {
		int[] result = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			result[i] = toInt(str[i]);
		}
		return result;
	}
}
