package com.javapractice.Eamil_Sending;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        String message=" Test Mail Api";
        String subject="Test Mail Api ";
        String to="anuj.pilania@motherson.com";
        String from="abhishek.2023mca1136@kiet.edu";
        
       // sendEami(message,subject,to,from);
        sendAttch(message,subject,to,from);
    }

    // This function send Attachament in mail
    
    private static void sendAttch(String message, String subject, String to, String from) {
		// Variable for gmail
    	String[] ccEmails = {"abhishektiwari.nrp@gmail.com"};
    	
		String host="smtp.gmail.com";
		
		// get system properties
	Properties properties=System.getProperties();
	System.out.println("Propeties:"+properties);
	
	//set host
	properties.put("mail.smtp.host",host);
	properties.put("mail.smtp.port", "465");
	properties.put("mail.smtp.ssl.enable", "true");
	properties.put("mail.smtp.auth", "true");	
	
	// Step 1: To get session object
     Session session=Session.getInstance(properties,new Authenticator() {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			
			return new PasswordAuthentication("abhishek.2023mca1136@kiet.edu", "Abhi@1998");
		}
		
	});
     session.setDebug(true);
     
     // Step 2: Compose Mail[text,multimedia,attachment]
     MimeMessage m=new MimeMessage(session);
     
     // Form mail
     try {
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	//	  m.setRecipients(Message.RecipientType.TO, InternetAddress.parse("recipient@example.com"));
		
		InternetAddress[] ccRecipients = InternetAddress.parse(String.join(",", ccEmails));
        m.setRecipients(Message.RecipientType.CC, ccRecipients);
		
		
		// Adding Subject in message
		m.setSubject(subject);
		
		// adding Attachment 
		// file path
		String path ="C:\\Mail\\Lambda Expression.pdf";
		MimeMultipart mimeMultipart =new MimeMultipart();
		
		// text
		// file
		MimeBodyPart textMime= new MimeBodyPart();
		
		MimeBodyPart fileMime=new MimeBodyPart();
		
		try {
			
		textMime.setText(message);
		
		File file=new File(path);
		
		fileMime.attachFile(file);
		
		mimeMultipart.addBodyPart(textMime);
		mimeMultipart.addBodyPart(fileMime);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.setContent(mimeMultipart);
		
		
		
//		Step 3: Send message using  Transport class
		Transport.send(m);
		
		System.out.println("Send Mail Successfully...................");
		
	} catch (MessagingException e) {
	
		e.printStackTrace();
	}
		
	}

	// this is mail sending mathod
	private static void sendEami(String message, String subject, String to, String from) {
		// Variable for gmail
		String host="smtp.gmail.com";
		
		// get system properties
	Properties properties=System.getProperties();
	System.out.println("Propeties:"+properties);
	
	//set host
	properties.put("mail.smtp.host",host);
	properties.put("mail.smtp.port", "465");
	properties.put("mail.smtp.ssl.enable", "true");
	properties.put("mail.smtp.auth", "true");	
	
	// Step 1: To get session object
     Session session=Session.getInstance(properties,new Authenticator() {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			
			return new PasswordAuthentication("abhishek.2023mca1136@kiet.edu", "Abhi@1998");
		}
		
	});
     session.setDebug(true);
     
     // Step 2: Compose Mail[text,multimedia,attachment]
     MimeMessage m=new MimeMessage(session);
     
     // Form mail
     try {
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		
		// Adding Subject in mesage
		m.setSubject(subject);
		
		// adding text to meesage
		m.setText(message);
		
//		Step 3: Send message using  Transport class
		Transport.send(m);
		
		System.out.println("Send Mail Successgully...................");
		
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
	}
    
} 
