package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;


public class MD5Utils {
	public static String md5(String message){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("md5");
			byte[] md5 = md.digest(message.getBytes());
			
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(md5);
		} catch (NoSuchAlgorithmException e) {
			
			throw new RuntimeException(e);
		}
		
		
	}
}
