package pl.poznan.put.cs.ify.webify.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {

	/**
	 * Tworzenie sumy kontrolnej MD5 ze stringa
	 * 
	 * 
	 * @param input
	 * @return
	 */
	public static String md5(String input) {
		String md5 = null;
		if (null == input)
			return null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

	public static String ALGO = "SHA1";

	private static String hash(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance(ALGO);
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}

	private static String getHash(String user, String pass) {
		try {
			return hash(user + pass);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(ALGO + " not supported");
		}
	}

	/**
	 * 
	 * @param user
	 * @param pass
	 * @return
	 */
	public static String sh1(String user, String pass) {
		return getHash(user, pass);
	}
}
