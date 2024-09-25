package com.QuesTyme.service;


import java.util.List;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.lang.Arrays;
import com.QuesTyme.dto.InterviewDto;
import com.QuesTyme.entity.User;
import com.QuesTyme.repository.UserRepository;


@Component
public class EmailService {
	    @Autowired
	    private JavaMailSender javaMailSender;

	   
	    
	    
	    
	    public void sendEmail(String email, String subject,String body) throws MailException {
      SimpleMailMessage mailMessage = new SimpleMailMessage(); 
	        
	        
	        //Sending mail to INTERVIEWER
	        mailMessage.setTo(email);
	        mailMessage.setSubject(subject);
	        mailMessage.setText(body);
	        javaMailSender.send(mailMessage); 
	    }

//		public void sendEmail(List<String> intervieweeEmail, String intervieweeSubject, String intervieweeBody) {
//			List<String> intervieweeEmails = Arrays.asList("interviewee1@example.com", "interviewee2@example.com");
//			String intervieweeSubject = "Interview Invitation";
//			String intervieweeBody = "Dear interviewee, ...";
//			sendEmail(intervieweeEmails, intervieweeSubject, intervieweeBody);
//			 SimpleMailMessage mailMessage = new SimpleMailMessage();
//		        mailMessage.setTo(toEmail);
//		        mailMessage.setSubject(subject);
//		        mailMessage.setText(body);
//		        javaMailSender.send(mailMessage);
//			
//			
//		}
		
		
		public void sendEmail(List<String> toEmails, String subject, String body) throws MailException {
			

		    SimpleMailMessage mailMessage = new SimpleMailMessage();
		    mailMessage.setTo(toEmails.toArray(new String[toEmails.size()]));
		    mailMessage.setSubject(subject);
		    mailMessage.setText(body);
		    javaMailSender.send(mailMessage);

		}

}
