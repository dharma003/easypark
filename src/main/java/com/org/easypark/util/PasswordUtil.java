package com.org.easypark.util;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {
	
	private static final SecureRandom RAND = new SecureRandom();
	private static final int SALT_LENGTH=16;
	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 512;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
	
	public static void main(String ...String) {
		
		String saltValue = generateSalt(SALT_LENGTH);
		
		String hashValue =  hashPassword("DHARMA",saltValue);
		
		
		System.out.println(verifyPassword("DHAMA",hashValue.toString(),saltValue));
		
	}
	
	public static String generateSalt (final int length) {

	    if (length < 1) {
	      System.err.println("error in generateSalt: length must be > 0");
	      return null;
	    }

	    byte[] salt = new byte[length];
	    RAND.nextBytes(salt);

	    return Base64.getEncoder().encodeToString(salt);
	  }
	
	
	public static String hashPassword (String password, String salt) {

	    char[] chars = password.toCharArray();
	    byte[] bytes = salt.getBytes();

	    PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

	    Arrays.fill(chars, Character.MIN_VALUE);

	    try {
	      SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
	      byte[] securePassword = fac.generateSecret(spec).getEncoded();
	      return Base64.getEncoder().encodeToString(securePassword);

	    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
	      System.err.println("Exception encountered in hashPassword()");
	      return null;

	    } finally {
	      spec.clearPassword();
	    }
	  }
	
	
	public static boolean verifyPassword (String password, String key, String salt) {
	    String optEncrypted = hashPassword(password, salt);
	    
	    if(optEncrypted.equals(key)) {
	    	return true;
	    }
	    return false;
	  }

}
