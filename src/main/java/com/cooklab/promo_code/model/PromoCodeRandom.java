package com.cooklab.promo_code.model;

import java.security.SecureRandom;

public class PromoCodeRandom {
	public static void main(String[] args) {
		String randomString = generateRandomString(10);
		System.out.println("Random String: " + randomString);
	}
	public static String generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomCharIndex = random.nextInt(characters.length());
			sb.append(characters.charAt(randomCharIndex));
		}

		return sb.toString();
	}
}



