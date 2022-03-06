package tn.spring.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service

public class EmailService implements IEmailSender {
@Autowired
JavaMailSender JMS;
private final static Logger LOGGER = org.slf4j.LoggerFactory
.getLogger(EmailService.class);
	@Override
	 @Async
	    public void send(String to, String email) {
	        try {
	            MimeMessage mimeMessage = JMS.createMimeMessage();
	            MimeMessageHelper helper =
	                    new MimeMessageHelper(mimeMessage, "utf-8");
	            helper.setText(email, true);
	            helper.setTo(to);
	            helper.setSubject("aaaaaaaaaa ");
	            JMS.send(mimeMessage);
	        } catch (MessagingException e) {
	            LOGGER.error("failed to send email", e);
	            throw new IllegalStateException("failed to send email");
	        }
	    }

}
