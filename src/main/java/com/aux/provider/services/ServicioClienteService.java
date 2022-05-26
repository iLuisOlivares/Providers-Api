package com.aux.provider.services;

import com.aux.provider.services.interfaces.ServicioClienteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ServicioClienteService implements ServicioClienteInterface {
    //Inyección de dependencia de JavaMailSender:
    @Autowired
    private JavaMailSender mailSender;

    //Logica para enviar email
    @Override
    public void sendEmail(String from, String to, String subject, String content) {

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject(subject);
        email.setText(content);

        mailSender.send(email);
    }
}

