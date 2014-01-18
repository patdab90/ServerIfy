package pl.poznan.put.cs.ify.webify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;

/**
 * Class to fill DB with initial data.
 * 
 * @author Patryk
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:pl/poznan/put/cs/ify/webify/spring-context.xml" })
public class AGodCreationTest {

	@Autowired
	private IUserDAO userDAO;

	private final static String USER_TEST1 = "test1";
	private final static String USER_TEST2 = "test2";
	private final static String USER_TEST3 = "test3";
	private final static String USER_TEST4 = "test4";
	private final static String USER_TEST5 = "test5";
	private final static String USER_BROADCAST = "BROADCAST";

	private static final Logger log = LoggerFactory.getLogger("InitialCreator");

	@Before
	public void createTestUsers() {
		log.info("And God say a word!");
		createUser(USER_TEST1, USER_TEST1);
		createUser(USER_TEST2, USER_TEST2);
		createUser(USER_TEST3, USER_TEST3);
		createUser(USER_TEST4, USER_TEST4);
		createUser(USER_TEST5, USER_TEST5);
		createUser(USER_BROADCAST, USER_BROADCAST);
		log.info("And word become a hmmm. something.");
	}

	@Test
	public void t() {

	}

	private void createUser(String username, String password) {
		if (userDAO.findByUserName(username) != null)
			return;
		UserEntity u = new UserEntity();
		u.setUsername(username);
		u.setPassword(password);
		u.addRole(UserRole.USER);
		u.setFirstName("Systemuser");
		u.setLastName("Systemuser");
		u.setEnabled(true);
		userDAO.persist(u);
	}
}
