package com.omalakhov.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CodeGenerator {
	public static final String alphanumericSymbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	public static final int LOBBY_JOIN_CODE_LENGTH = 6;

	public static String generateLobbyJoinCode() {
		return generateAlphanumericCode(LOBBY_JOIN_CODE_LENGTH);
	}

	public static String generateAlphanumericCode(int length) {
		Random random = ThreadLocalRandom.current();
		char[] code = new char[length];
		for (int i = 0; i < length; i++) {
			code[i] = alphanumericSymbols.charAt(random.nextInt(alphanumericSymbols.length()));
		}
		return new String(code);
	}
}
