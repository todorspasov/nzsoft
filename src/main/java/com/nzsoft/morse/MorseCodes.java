package com.nzsoft.morse;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;

public class MorseCodes {

	private static final Map<String, String> codes = new HashMap<>();
	private static final Map<String, String> reverseCodes = new HashMap<>();
	static {
		codes.put("a", ".-");
		codes.put("b", "-...");
		codes.put("c", "-.-.");
		codes.put("d", "-..");
		codes.put("e", ".");
		codes.put("f", "..-.");
		codes.put("g", "--.");
		codes.put("h", "....");
		codes.put("i", "..");
		codes.put("j", ".---");
		codes.put("k", "-.-");
		codes.put("l", ".-..");
		codes.put("m", "--");
		codes.put("n", "-.");
		codes.put("o", "---");
		codes.put("p", ".--.");
		codes.put("q", "--.-");
		codes.put("r", ".-.");
		codes.put("s", "...");
		codes.put("t", "-");
		codes.put("u", "..-");
		codes.put("v", "...-");
		codes.put("w", ".--");
		codes.put("x", "-..-");
		codes.put("y", "-.--");
		codes.put("z", "--..");
		codes.put("1", ".----");
		codes.put("2", "..---");
		codes.put("3", "...--");
		codes.put("4", "....-");
		codes.put("5", ".....");
		codes.put("6", "-....");
		codes.put("7", "--...");
		codes.put("8", "---..");
		codes.put("9", "----.");
		codes.put("0", "-----");
		codes.put(" ", " ");
		for (String key : codes.keySet()) {
			reverseCodes.put(codes.get(key), key);
		}
	}
	
	public static String[] convertToMorse(String text) {
		Validate.notBlank(text);
		String[] output = new String[text.length()];
		for (int i = 0; i < text.length(); i++) {
			char currentChar = text.charAt(i);
			output[i] = codes.get(String.valueOf(currentChar));
		}
		return output;
	}
	
	public static String decodeMorse(String[] morse) {
		Validate.notEmpty(morse);
		String output = "";
		for (int i = 0; i < morse.length; i++) {
			output += reverseCodes.get(morse[i]);
		}
		return output;
	}
}
