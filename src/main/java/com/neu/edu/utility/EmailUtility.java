package com.neu.edu.utility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
public class EmailUtility {

		public static boolean sendEmail(String from, String to, String msgBody, String title) {
			try {
				Email email = new SimpleEmail();
				email.setHostName("smtp.googlemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("bshivani.iot@gmail.com", "irme jnkn shrw ptod"));
				email.setSSLOnConnect(true);
				email.setFrom("employer@company.edu"); 
				email.setSubject(title);
				email.setMsg(msgBody); 
				email.addTo(to);
				email.send();
				return true;
			} catch (EmailException e) {
				System.out.println("Issue:Email cannot be sent");
			}
			return false;
		}

}


