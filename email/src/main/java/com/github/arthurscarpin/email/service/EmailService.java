package com.github.arthurscarpin.email.service;

import com.github.arthurscarpin.email.enums.EmailStatus;
import com.github.arthurscarpin.email.model.Email;
import com.github.arthurscarpin.email.repository.EmailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final EmailRepository repository;

    @Value("${email.from}")
    private String emailFrom;

    @Transactional
    public void send(Email email) {
        try {
            email.setEmailFrom(emailFrom);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            mailSender.send(message);
            email.setStatus(EmailStatus.SENT);
            email.setSendDate(LocalDateTime.now());
        } catch (Exception e) {
            email.setStatus(EmailStatus.FAILED);
            System.out.println("Error sending email: " + e.getMessage());
        }
        repository.save(email);
    }
}
