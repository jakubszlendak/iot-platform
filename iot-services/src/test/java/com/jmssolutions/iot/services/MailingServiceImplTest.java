package com.jmssolutions.iot.services;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jakub on 31.01.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/services-context.xml")
public class MailingServiceImplTest extends TestCase {

    @Autowired
    MailingService service;


    @Test
    public void testSendPlainTextEmail() throws Exception {
        assertTrue(service.sendPlainTextEmail("test", "szlendak.jakub@gmail.com", "test"));

    }
}