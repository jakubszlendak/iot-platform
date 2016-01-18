package com.jmssolutions.iot.services;

import com.jmssolutions.iot.domain.User;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jakub on 18.01.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/services-context.xml")
public class UserServiceImplTest extends TestCase {

    @Autowired
    UserService userService;

    User u, u2;

    private final static String TEST_USERNAME = "bolex";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        u = new User();
        u.setFirstName("Kuba");
        u.setLastName("Sz");
        u.setEmail("dd@upa");
        u.setUsername(TEST_USERNAME);
        u.setPassword("trzaslo");
        u.setEnabled(1);

        u2 = new User();
        u2.setFirstName("Kuba");
        u2.setLastName("Sz");
        u2.setEmail("dd@upa");
        u2.setUsername(TEST_USERNAME + "1");
        u2.setPassword("trzaslo");
        u2.setEnabled(1);

    }

    @After
    public void tearDown() throws Exception {
        User u = userService.getUserByUsername(TEST_USERNAME);
        if(u != null)
            userService.deleteUser(u.getID());

        User u2 = userService.getUserByUsername(TEST_USERNAME+"1");
        if(u2 != null)
            userService.deleteUser(u2.getID());
    }

    @Test
    public void testCreateUser() throws Exception {

        assertNotNull(userService.createUser(u));

        assertNull(userService.createUser(u));

    }

    public void testUpdateUser() throws Exception {

    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = userService.createUser(u2);
        userService.deleteUser(user.getID());
        assertNull(userService.getUserByUsername(user.getUsername()));

    }

    public void testAddUserRoles() throws Exception {

    }

    public void testGetUserById() throws Exception {

    }

    public void testGetAllUsers() throws Exception {

    }

    public void testGetUserByUserParams() throws Exception {

    }
}