package com.service.highspeedtrain.service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class MailService {

	public void send(String konu) {

		System.out.println("E-Mail Gonderiliyor");

		final String username = "melih.demircan@fmsstech.com";
		final String password = "245623mel";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("melih-demircan@hotmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("melih-demircan@hotmail.com"));
			message.setSubject(konu);
			message.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(ZonedDateTime.now()));

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("E-Mail Gonderilemedi");
		}
	}
}
