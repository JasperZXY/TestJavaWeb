package zxy.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DigestUtils {

	private static final Logger logger = LoggerFactory.getLogger(DigestUtils.class);
	
	// 标准MD5
	
	public static String md5(String input) {
		return digest("MD5", input, null);
	}

	public static String md5Secure(String input, String salt) {
		return digest("MD5", input, salt);
	}

	// 标准SHA
	
	public static String sha256(String input) {
		return digest("SHA-256", input, null);
	}
	
	public static String sha256Secure(String input, String salt) {
		return digest("SHA-256", input, salt);
	}
	
	private static String digest(String algorithm, String input, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			if (salt != null) md.update(salt.getBytes());
			byte[] bytes = md.digest(input.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

}