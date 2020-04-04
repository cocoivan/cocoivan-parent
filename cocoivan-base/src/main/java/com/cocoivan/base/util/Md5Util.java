package com.cocoivan.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Md5Util {
	
	private static Logger logger = LoggerFactory.getLogger(Md5Util.class);
	
	private static String securityKey = "eKQmkdW8Q7WO5IOtffWYTUBUCIH4zkXNnssIWxUv5Kc";
	public static String getMd5Cookie(String name,String expireDate){
		String cookieInput = name+"zaozuo"+expireDate+"works"+securityKey;
		return getMd5String(cookieInput);
	}
	
	public static String generatePWD(String pwd,String salt){
		String pwd_generate = "";
		String slatPWD = salt + pwd;
		pwd_generate = getMd5String(slatPWD);
		return pwd_generate;
	}
	
	public static String getMd5String(String input){
		try{
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(input.getBytes());
			byte[] m = md5.digest();   
	        String ms = byte2hex(m);
			return ms;
		}catch(Exception e){
			logger.error("getMd5String error", e);
		}
		return input;
	}
	
	public static String getMd5String(byte[] input){
		try{
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(input);
			byte[] m = md5.digest();   
	        String ms = byte2hex(m);
			return ms;
		}catch(Exception e){
			logger.error("getMd5String error", e);
		}
		return null;
	}
	public static String getValidURLCode(String input){
		try{
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(input.getBytes());
			byte[] m = md5.digest();   
			String ms = byte2hex(m);
			return ms;
		}catch(Exception e){
			logger.error("getValidURLCode error", e);
		}
		return input;
	}

	private static String byte2hex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            String temp = Integer.toHexString(((int) data[i]) & 0xFF);
            for(int t = temp.length();t<2;t++)
            {
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }
	
	public static String getSalt(){
	    //Always use a SecureRandom generator
	    SecureRandom sr = null;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			logger.error("SecureRandom.getInstance(\"SHA1PRNG\") error", e);
		}
	    //Create array for salt
	    byte[] salt = new byte[16];
	    //Get a random salt
	    sr.nextBytes(salt);
	    //return salt
	    return salt.toString();
	}
	
	public static void main(String[] args) {
		
	}
}
