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

public class SendEmailService {
	public List<MimeMessage> formMsg(Email[] emails,Session session,InternetAddress addressFrom) throws MessagingException {
		List<MimeMessage> list=new ArrayList<MimeMessage>();  
		for(int i=0;i<emails.length;i++) {
			MimeMessage message = new MimeMessage(session);  
			message.setSender(addressFrom);
			message.setSubject(emails[i].getSubject());  
			message.setContent(emails[i].getBody(), "text/plain"); 
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emails[i].getTo()));
			list.add(message);
		}
		
		return list;
	}
	public  void sent(Email[] emails) {
		
	    Scanner sc = new Scanner(System.in); 
        System.out.println("Enter your email address:");
        final String from = sc.nextLine(); 
        System.out.println("Enter your password: ");
        final String password = sc.nextLine(); 


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
	       return new PasswordAuthentication(from,password);  
	   }  
	   });
	    try {
	   //session.setDebug(true);  
	   Transport transport = session.getTransport();  
	   InternetAddress addressFrom = new InternetAddress(from); 
	   
	   List<MimeMessage> list=new ArrayList<MimeMessage>();
	   list=formMsg(emails,session,addressFrom);
	   
	   transport.connect();  
	   for(MimeMessage msg:list) {
		   Transport.send(msg);
		   
	   }
	   transport.close();
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	   
	}

}
