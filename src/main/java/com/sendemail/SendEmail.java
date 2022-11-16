package com.sendemail;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail extends TimerTask{

	long count=0;
	static Timer t = new Timer();
	
	public static void main(String[] args) {
		
		SendEmail.t.schedule(new SendEmail(), 0, 3000);
	
	}
	
	@Override
	public void run() {
	   
        String to = "vipulkaushik1147@gmail.com";

        String from = "kaushikvipul1501@gmail.com";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
      
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true" );
        

        
        Session session = Session.getInstance(properties, new Authenticator() {
            
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kaushikvipul1501@gmail.com", "muikczcdjyfrxrsk");
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
             
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        count++;
        
        if(count>=5)
        {
        	SendEmail.t.cancel();
        	System.out.println("*********************** TASK COMPLETED SUCCESSFULLY ************************");
        }
        	
	}
}
