package com.example.demo.service;

import com.example.demo.model.EmailModel;
import com.example.demo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    final JavaMailSender mailSender;
    private EmailRepository emailRepository;

    public EmailService(JavaMailSender mailSender, EmailRepository emailRepository) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }

    @Value("${spring.mail.username}")
    private String emailFrom;

    public EmailModel sendEmail(EmailModel emailModel) {
        try{
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            System.out.println(">>> INICIOU ENVIO");
            mailSender.send(message);
            System.out.println(">>> ENVIOU");

        } catch (MailException e){
            e.printStackTrace();
        }
            return emailRepository.save(emailModel);
    }
    }

