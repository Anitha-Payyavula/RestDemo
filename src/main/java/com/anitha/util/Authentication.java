package com.anitha.util;

import java.io.IOException;

import com.anitha.DAO.Email_DAO;

import sun.misc.BASE64Decoder;
public class Authentication {
	public static String[] isUserAuthenticated(String authString){
        
        String decodedAuth = "";
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        decodedAuth = new String(bytes);
        String[] list=decodedAuth.split(":");
        return list;
         
         
    }
	public static String decrypt(String pwd) throws IOException {
		byte[] bytes=new BASE64Decoder().decodeBuffer(pwd);
		String decryptedPwd = new String(bytes);
		return decryptedPwd;
	}

}
