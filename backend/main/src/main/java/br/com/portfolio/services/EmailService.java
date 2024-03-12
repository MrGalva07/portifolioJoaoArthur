package br.com.portfolio.services;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import br.com.portfolio.enums.StatusEmail;
import br.com.portfolio.models.EmailModel;
import br.com.portfolio.repositories.EmailRepository;

@Service
public class EmailService{
    @Autowired
    EmailRepository emailRepository;
    
    @Autowired
    JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            emailSender.send(message);
        }catch(MailException e){
            emailModel.setStatusEmail((StatusEmail.ERROR));
        }finally{
            return emailRepository.save(emailModel);
        }
    }
    
}