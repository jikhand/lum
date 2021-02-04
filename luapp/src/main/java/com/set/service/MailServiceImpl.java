package com.set.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import com.set.model.Mail;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;
    private Logger logger = Logger.getLogger("MailServiceImpl.class");
    
	@Override
    public void sendEmail(Mail object) {
		 
        MimeMessagePreparator preparator = getMessagePreparator(object);
        try {
            mailSender.send(preparator);
            logger.info("Message Send...Hurrey");
        } catch (MailException ex) {
        	logger.error(ex.getMessage());
        }
    }
 
    private MimeMessagePreparator getMessagePreparator(Mail object) {
        
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(object.getMailFrom());
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(object.getMailTo()));
                //mimeMessage.setText(object.getMailContent());
                mimeMessage.setContent(object.getMailContent(), "text/html");
                mimeMessage.setSubject(object.getMailSubject());
            }
        };
        return preparator;
    }


//	@Autowired
//	JavaMailSender 
 
//    @Autowired
//    JavaMailSender mailSender;
// 
//    @Override
//    public void sendEmail(Mail mail) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(mail.getMailFrom());
//        message.setTo(mail.getMailTo());
//        message.setSubject(mail.getMailSubject());
//        message.setText(mail.getMailContent());
//        mailSender.send(message);
//    }
    
 
}
