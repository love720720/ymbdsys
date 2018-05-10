package com.util;

public class PasswordMD5 {

	private final static String SPECIALSYMBOLS = "!@#$%^&*()]";
	
	/*
	 * 密码加密 将MD5加密
	 * password 用户输入密码
	 */
	public static String encryptionStaffPassword(String password){
		password = password + SPECIALSYMBOLS;
		password = MD5Secret.MD5(password);
		password = MD5Secret.KL(password);
		return password;
	}
}