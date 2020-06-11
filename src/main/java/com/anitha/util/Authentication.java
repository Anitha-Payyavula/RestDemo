package com.anitha.util;

import java.io.IOException;

import com.anitha.DAO.Email_DAO;

import sun.misc.BASE64Decoder;
public class Authentication {
	public static String[] isUserAuthenticated(String authString){
        
        String decodedAuth = "";
        // Header is in the format "Basic 5tyc0uiDat4"
        // We need to extract data before decoding it back to original string
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string
        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        decodedAuth = new String(bytes);
        String[] list=decodedAuth.split(":");
        return list;
         
         
    }

}
