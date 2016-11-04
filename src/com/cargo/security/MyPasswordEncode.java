package com.cargo.security;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class MyPasswordEncode extends MessageDigestPasswordEncoder {

	public MyPasswordEncode(String algorithm) {
		super(algorithm);
		// TODO Auto-generated constructor stub
	}
	public boolean isPasswordValid(String savePass, String submitPass, Object salt){
		 Md5PasswordEncoder md5 = new Md5PasswordEncoder(); 
		  return savePass.equalsIgnoreCase(md5.encodePassword(submitPass,  
		            salt.toString()));  
	}
}
