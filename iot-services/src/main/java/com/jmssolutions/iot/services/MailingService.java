package com.jmssolutions.iot.services;

/**
 * Created by jakub on 31.01.16.
 */
public interface MailingService {
    boolean sendPlainTextEmail(String subject, String address, String message);
}
