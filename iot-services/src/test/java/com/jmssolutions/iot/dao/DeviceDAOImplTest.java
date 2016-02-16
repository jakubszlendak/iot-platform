package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.DeviceClass;
import com.jmssolutions.iot.domain.User;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jakub on 01.02.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/repositories-context.xml")
public class DeviceDAOImplTest extends TestCase {

    @Autowired
    DeviceDAO deviceDAO;

    @Autowired
    UserDAO userDAO;

    DeviceClass dc1;
    Device d1, d2, d3;
    User u1;
    final static String DEV_NAME = "galaksi";
    final static String DEV_PRODUCER = "szajsung";
    final static long   TEST_USER_ID = 68;


    @Before
    public void setUp() throws Exception {
        super.setUp();

        dc1 = new DeviceClass();
        dc1.setClassName("PHONE");

        u1 = userDAO.getUserById(TEST_USER_ID);

        d1 = new Device();
        d1.setName(DEV_NAME+"1");
        d1.setProducerName(DEV_PRODUCER);
        d1.setDeviceClass(dc1);
        d1.setOwner(u1);

        d2 = new Device();
        d2.setName(DEV_NAME+"2");
        d2.setProducerName(DEV_PRODUCER);
        d2.setDeviceClass(dc1);
        d2.setOwner(u1);
        d2 = deviceDAO.create(d2);

        d3 = new Device();
        d3.setName(DEV_NAME+"3");
        d3.setProducerName(DEV_PRODUCER);
        d3.setDeviceClass(dc1);
        d3.setOwner(u1);
        d3 = deviceDAO.create(d3);





    }

    @After
    public void tearDown() throws Exception {
        if(d1!= null && deviceDAO.findById(d1.getID()) != null)
            deviceDAO.remove(d1.getID());
        if(d2 != null && deviceDAO.findById(d2.getID()) != null)
            deviceDAO.remove(d2.getID());
        if(d3 != null && deviceDAO.findById(d3.getID()) != null)
            deviceDAO.remove(d3.getID());
    }

    @Test
    public void testCreateDevice() throws Exception {
        Device d = deviceDAO.create(d1);
        d1=d;
        assertNotNull(d);
    }

    @Test
    public void testFindDeviceClass() throws Exception {
        assertEquals("PHONE", deviceDAO.findDeviceClass("PHONE").getClassName());
    }


    @Test
    public void testFindDeviceById() throws Exception {
        Device d = deviceDAO.findById(d2.getID());
        assertNotNull(d);
        assertNull(deviceDAO.findById((long)12312));
    }

    @Test
    public void testFindDevicesByOwner() throws Exception {
        assertFalse(deviceDAO.findByOwner(u1).isEmpty());
        assertEquals(2, deviceDAO.findByOwner(u1).size());
    }

    @Test
    public void testUpdateDevice() throws Exception {
        Device d = deviceDAO.findById(d2.getID());
        d.setProducerName("lala");
        d.setAesKey("lolo");
        Device du = deviceDAO.update(d);
        assertEquals("lolo", du.getAesKey());
        assertEquals("lala", du.getProducerName());
    }

    @Test
    public void testDeleteDevice() throws Exception {
        deviceDAO.remove(d2.getID());

    }
}