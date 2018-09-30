package com.zhidisoft.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5DB {
	public static void main(String[] args) {
		
		//System.out.println(getMD5("aaaaaa"));
	}

	public static String getMD5(String str, String charest) {
		 String newStr = "";
		 String newStr1 = "";
		try {
			//
			byte[] bs = str.getBytes(charest);
			//获取MD5对象
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			
			BASE64Encoder baEncoder = new BASE64Encoder();
			newStr = baEncoder.encode(md5.digest(bs))+"sss";
			newStr1 = baEncoder.encode(md5.digest(newStr.getBytes(charest)));
			
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
		return newStr1;
	}
	public static String getMD5(String str) {
		 return getMD5(str, "utf-8");
	}
}
