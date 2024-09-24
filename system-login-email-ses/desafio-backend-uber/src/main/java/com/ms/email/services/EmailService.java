package com.ms.email.services;

import com.ms.email.dtos.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSuccessEmail(EmailDto email) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.from());;
        message.setTo(email.to());
        message.setSubject(email.subject());
        message.setText(email.body());

        emailSender.send(message);
    }
}
