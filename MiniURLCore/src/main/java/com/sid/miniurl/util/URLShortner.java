package com.sid.miniurl.util;

public final class URLShortner {
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int ALPHABET_LENGTH = CHARACTERS.length();
	private static final char[] CHARSET = CHARACTERS.toCharArray();

	public static String encode(int number) {
		int i = number;
		if (i == 0) {
			return Character.toString(CHARSET[0]);
		}
		StringBuilder stringBuilder = new StringBuilder();
		while (i > 0) {
			int remainder = i % ALPHABET_LENGTH;
			i /= ALPHABET_LENGTH;
			stringBuilder.append(CHARSET[remainder]);
		}
		return stringBuilder.reverse().toString();
	}

	public static int decode(String str) {
		int i = 0;
		char[] chars = str.toCharArray();
		for (char c : chars) {
			i = i * ALPHABET_LENGTH + CHARACTERS.indexOf(c);
		}
		return i;
	}
}
