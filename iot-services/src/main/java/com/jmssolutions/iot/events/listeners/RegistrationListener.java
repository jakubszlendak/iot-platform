package com.jmssolutions.iot.events.listeners;

import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.events.OnRegisterCompleteEvent;
import com.jmssolutions.iot.services.MailingService;
import com.jmssolutions.iot.services.UserAuthenticationDetailsServiceImpl;
import com.jmssolutions.iot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.UUID;

/**
 * Created by jakub on 30.01.16.
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegisterCompleteEvent> {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private MailingService mailingService;


    @Override
    public void onApplicationEvent(OnRegisterCompleteEvent event) {
        this.confirmRegistration(event);

    }

    private void confirmRegistration(OnRegisterCompleteEvent event) {
        User user = userService.getUserByUsername(event.getUser().getUsername());
        String token = UUID.randomUUID().toString();

        userService.createVerificationToken(user, token);

        String recipentAddress = user.getEmail();
        String subject = "IoT Platform Account Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/register/confirm?token="+token;
        String message ="Welcome to IoT Platform! Paste following link into your browser: \n" + "http://localhost:8080"+confirmationUrl;
        mailingService.sendPlainTextEmail(subject,recipentAddress,message);
    }
}
