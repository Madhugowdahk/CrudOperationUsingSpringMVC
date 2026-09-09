package com.unibrain.validator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Password hashing utility in Java. It salts automatically and hashes passwords
 * with PBKDF2 using 1,000 iterations of SHA512.
 * 
 * @author VIGNESH
 *
 */

@Component
public class PBKDF2HMACSHA512 {

	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
	private static final int KEY_LENGTH = 128;
	private static final int ITERATION_COUNT = 1000;

	/**
	 * Generates a random salt and performs password hashing.
	 * 
	 * @param password
	 * @return encoded password
	 */
	public String getEncodedHash(String password) {

		char[] charPass = password.trim().toCharArray();
		byte[] salt = getSalt();

		byte[] hash = generateHash(charPass, salt);

		String encodedPass = null;

		if (hash != null && hash.length > 0) {
			encodedPass = encodeBase64(salt) + ":::" + encodeBase64(hash);
		}

		return encodedPass;

	}

	/**
	 * Creates a Hash from the given char array using the specified algorithm.
	 * 
	 * @param password
	 * @param salt
	 * @return byte array hash
	 */
	public byte[] generateHash(char[] password, byte[] salt) {

		byte[] hash = null;

		try {
			final PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH * 8);
			final SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
			hash = skf.generateSecret(spec).getEncoded();

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

		return hash;
	}

	/**
	 * Compares two byte arrays.
	 *
	 * @param currentPassword
	 * @param storedPassword
	 * @return true if they are equivalent.
	 */
	public boolean validatePassword(char[] currentPassword, String storedPassword) {

		String[] params = storedPassword.split(":::");

		byte[] salt = decodeBase64(params[0].trim());

		byte[] currentPasswordHash = generateHash(currentPassword, salt);

		if (currentPasswordHash == null) {
			return false;
		}

		byte[] storedPasswordHash = decodeBase64(params[1].trim());

		int currPassSize = currentPasswordHash.length;
		int storedPassSize = storedPasswordHash.length;

		int diff = currPassSize ^ storedPassSize;
		for (int i = 0; i < currPassSize && i < storedPassSize; i++) {
			diff |= currentPasswordHash[i] ^ storedPasswordHash[i];
		}

		return diff == 0;
	}

	public byte[] getSalt() {
		Random rnd = new SecureRandom();
		byte[] salt = new byte[16];
		rnd.nextBytes(salt);
		return salt;
	}

	public byte[] decodeBase64(String string) {
		return Base64.decodeBase64(string);
	}

	public String encodeBase64(byte[] array) {
		return StringUtils.newStringUtf8(Base64.encodeBase64(array));
	}

}
