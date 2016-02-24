package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Device;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by jakub on 24.02.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/repositories-context.xml")
public class AbstractJpaDAOTest {

    @Autowired
    private DeviceDAO deviceDAO;

    @Autowired
    private UserDAO userDAO;

    private static final long TEST_USER_ID = 36;
    private Device mockDevice1;
    private Device mockDevice2;


    @After
    public void tearDown(){
        try {
            deviceDAO.remove(mockDevice1.getID());
            deviceDAO.remove(mockDevice2.getID());
        } catch (Exception e)
        {
            System.out.print("cyka");
        }

    }

    @Before
    public void setUp(){
        mockDevice1 = new Device();
        mockDevice1.setAesKey("xx");
        mockDevice1.setName("uu");
        mockDevice1.setProducerName("zz");
        mockDevice1.setUuid(UUID.randomUUID().toString());
        mockDevice1.setDeviceClass(deviceDAO.findDeviceClass("PHONE"));
        mockDevice1.setOwner(userDAO.getUserById(TEST_USER_ID));
    }

    @Test
    public void testCreate() throws Exception {
        mockDevice1 = deviceDAO.create(mockDevice1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void ifExist_CreateShouldThrow() throws Exception {
        mockDevice1 = deviceDAO.create(mockDevice1);
        mockDevice2 = mockDevice1;
        mockDevice2.setID(0);
        Device mockDevice2 = deviceDAO.create(mockDevice1);
    }

    @Test
    public void testFindById() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }
}