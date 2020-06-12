package com.anitha.demorest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.anitha.model.Email;
import com.anitha.model.Sender;
import com.anitha.util.Authentication;
import com.anitha.util.DbConnectionDetails;

import sun.misc.BASE64Decoder;

public class SendEmailService {
	
	public  boolean sent(Email email,final Sender sender) {
		try {
		final String decryptedPwd = Authentication.decrypt(sender.getPassword());
		FileInputStream input = new FileInputStream( "C:\\Users\\vijay\\eclipse-workspace\\demorest\\props\\server.props" );
		Properties props = new Properties();
		props.load(input);
	    Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {  
	       return new PasswordAuthentication(sender.getUserId(),decryptedPwd);  
	   }  
	   });
	      
	   Transport transport = session.getTransport();  
	   InternetAddress addressFrom = new InternetAddress(sender.getUserId()); 
	   
	    MimeMessage message = new MimeMessage(session);  
		message.setSender(addressFrom);
		message.setSubject(email.getSubject());  
		message.setContent(email.getBody(), "text/plain"); 
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo_address()));
	   
	   transport.connect();  
	   
	   Transport.send(message);
	   transport.close();
	   return true;
		   
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    	return false;
	    }
	   
	}
	

}
