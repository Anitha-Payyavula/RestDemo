package com.anitha.demorest;

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

public class SendEmailService {
	
	public  boolean sent(Email email,final String userId,final String pwd) {
		
	    Properties props = new Properties();  
	    props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {  
	       return new PasswordAuthentication(userId,pwd);  
	   }  
	   });
	    try {
	   //session.setDebug(true);  
	   Transport transport = session.getTransport();  
	   InternetAddress addressFrom = new InternetAddress(userId); 
	   
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
