package com.main.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	public void sendSimpleEmail(String toEmail,String body,String subject) {
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom("mahendrareddyambavarapu7@gmail.com");
			message.setTo(toEmail);
			message.setText(body);
			message.setSubject(subject);
			
			mailSender.send(message);
			System.out.println("Mail Sent.....");
			}
			catch (Exception e) {
				System.out.println("Error while sending the mail");
			}
			
		}
		
	}

