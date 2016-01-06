import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;
import com.jmssolutions.iot.dao.UserDAO;
import com.jmssolutions.iot.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/repositories-context.xml")
public class UserDAOImplTest {

	@Autowired
	UserDAO userDao;
	@Mock
	User user;
	@Before
	public void setUp() throws Exception {
		user = mock(User.class);
		when(user.getFirstName()).thenReturn("name");
		when(user.getEmail()).thenReturn("email");
		when(user.getLastName()).thenReturn("surname");
		when(user.getPassword()).thenReturn("passwd");
//		when(user.getID()).thenReturn((long) anyInt());
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testInsertUser() {
		userDao.insertUser(user);
//		userDao.getUserById(user.getID());
	
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAllUsers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetUserById() {
		fail("Not yet implemented"); // TODO
	}

}
