package com.jmssolutions.iot.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by jakub on 31.01.16.
 */
@Service
public class MailingServiceImpl implements MailingService {

    private String mailSmtpAuth;
    private String mailSmtpHost;
    private String mailSmtpPort;
    private String mailSmtpSocketFactoryClass;
    private String mailSmtpSocketFactoryPort;
    private String mailEmailFrom;
    private String username;
    private String password;

    private final static Logger logger = Logger.getLogger(MailingServiceImpl.class);

    @Override
    public boolean sendPlainTextEmail(String subject, String address, String message) {
        Session session = openSession();
        openSession();

        try{
            Message m = new MimeMessage(session);
            m.setFrom(new InternetAddress(mailEmailFrom));
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
            m.setSubject(subject);
            m.setText(message);
            Transport.send(m);
        } catch (MessagingException e){
            logger.error("Unable to send email: "+e.getMessage());
            return false;
        }
        return true;
    }

    private Session openSession(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", mailSmtpAuth);
        props.put("mail.smtp.host", mailSmtpHost);
        props.put("mail.smtp.port", mailSmtpPort);
        props.put("mail.smtp.socketFactory.port", mailSmtpSocketFactoryPort);
        props.put("mail.smtp.socketFactory.class", mailSmtpSocketFactoryClass);
        Authenticator authenticator = new Authenticator() {
            private PasswordAuthentication passwordAuthentication = new PasswordAuthentication(username, password);
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return passwordAuthentication;
            }
        };

        /// Open session
        return Session.getInstance(props, authenticator);
    }


    public String getMailSmtpAuth() {
        return mailSmtpAuth;
    }

    public void setMailSmtpAuth(String mailSmtpAuth) {
        this.mailSmtpAuth = mailSmtpAuth;
    }

    public String getMailSmtpHost() {
        return mailSmtpHost;
    }

    public void setMailSmtpHost(String mailSmtpHost) {
        this.mailSmtpHost = mailSmtpHost;
    }

    public String  getMailSmtpPort() {
        return mailSmtpPort;
    }

    public void setMailSmtpPort(String mailSmtpPort) {
        this.mailSmtpPort = mailSmtpPort;
    }

    public String getMailSmtpSocketFactoryClass() {
        return mailSmtpSocketFactoryClass;
    }

    public void setMailSmtpSocketFactoryClass(String mailSmtpSocketFactoryClass) {
        this.mailSmtpSocketFactoryClass = mailSmtpSocketFactoryClass;
    }

    public String  getMailSmtpSocketFactoryPort() {
        return mailSmtpSocketFactoryPort;
    }

    public void setMailSmtpSocketFactoryPort(String  mailSmtpSocketFactoryPort) {
        this.mailSmtpSocketFactoryPort = mailSmtpSocketFactoryPort;
    }

    public String getMailEmailFrom() {
        return mailEmailFrom;
    }

    public void setMailEmailFrom(String mailEmailFrom) {
        this.mailEmailFrom = mailEmailFrom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
