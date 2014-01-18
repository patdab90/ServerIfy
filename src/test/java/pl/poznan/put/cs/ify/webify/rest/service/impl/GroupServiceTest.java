package pl.poznan.put.cs.ify.webify.rest.service.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupPermissionDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupPermissionEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;
import pl.poznan.put.cs.ify.webify.service.IGroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:pl/poznan/put/cs/ify/webify/spring-context.xml" })
@ActiveProfiles("test")
public class GroupServiceTest {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	private UserEntity user;
	private UserEntity user2;

	@Autowired
	private IGroupService groupService;

	@Autowired
	private IGroupPermissionDAO permissionDAO;

	@Autowired
	private IUserDAO userDAO;

	@Test
	public void createTest() {
		GroupEntity g = groupService.createGroupe(user, "TestCreateGroup1"
				+ new Date().getTime());
		assertNotNull(g);
		assertNotNull(g.getId());
		java.util.List<GroupPermissionEntity> l = g.getUsers();

		assertNotNull(l);
		if (l.isEmpty()) {
			l = permissionDAO.find(g);
		}
		assertEquals(1, l.size());
		GroupPermissionEntity gp = l.get(0);
		assertNotNull(gp);
		assertNotNull(gp.getUser());
		assertEquals(gp.getUser(), user);
	}

	@Test
	@Transactional
	// @Ignore
	public void getAllGroups() {
		GroupEntity g = groupService.createGroupe(user, "TestCreateGroup2"
				+ new Date().getTime());
		assertNotNull(g);
		assertNotNull(g.getId());
		java.util.List<GroupPermissionEntity> l = g.getUsers();
		assertNotNull(l);
		if (l.isEmpty()) {
			l = permissionDAO.find(g);
		}
		assertEquals(1, l.size());
		GroupPermissionEntity gp = l.get(0);
		assertNotNull(gp);
		assertNotNull(gp.getUser());
		assertEquals(gp.getUser(), user);

		GroupEntity g2 = groupService.createGroupe(user, "TestCreateGroup3"
				+ new Date().getTime());
		assertNotNull(g2);
		assertNotNull(g2.getId());
		java.util.List<GroupPermissionEntity> l2 = g2.getUsers();
		assertNotNull(l2);
		if (l2.isEmpty()) {
			l2 = permissionDAO.find(g2);
		}
		assertEquals(1, l2.size());
		GroupPermissionEntity gp2 = l2.get(0);
		assertNotNull(gp2);
		assertNotNull(gp2.getUser());
		assertEquals(gp2.getUser(), user);

		List<GroupEntity> groups = groupService.getGroups(user);
		assertNotNull(groups);
		assertEquals(2, groups.size());
	}

	@Test
	// @Transactional
	public void shouldAddMember() {

		GroupEntity g = groupService.createGroupe(user, "TestCreateGroup1"
				+ new Date().getTime());
		assertNotNull(g);
		assertNotNull(g.getId());
		java.util.List<GroupPermissionEntity> l = g.getUsers();

		assertNotNull(l);
		if (l.isEmpty()) {
			l = permissionDAO.find(g);
		}
		assertEquals(1, l.size());
		GroupPermissionEntity gp = l.get(0);
		assertNotNull(gp);
		assertNotNull(gp.getUser());
		assertEquals(gp.getUser(), user);

		// before
		String username = "userTest2" + this.getClass().getSimpleName()
				+ new Date().getTime();
		user2 = new UserEntity();
		user2.setFirstName("test2");
		user2.setLastName("test2");
		user2.setPassword("test2");
		user2.setUsername(username);
		user2.addRole(UserRole.USER);

		userDAO.persist(user2);
		assertNotNull(user2);
		assertNotNull(user2.getId());
		log.info("init(): user2 id=" + user2.getId());
		UserEntity u2 = userDAO.findByUserName(username);
		assertEquals(user2, u2);

		groupService.addGroupMember(user, g, user2);

		GroupPermissionEntity perm = permissionDAO.find(user2, g);
		assertNotNull(perm);
		assertEquals(user2, perm.getUser());
		assertEquals(g, perm.getGroup());
	}

	@Before
	public void init() {
		String username = "userTest" + this.getClass().getSimpleName()
				+ new Date().getTime();
		user = new UserEntity();
		user.setFirstName("test1");
		user.setLastName("test1");
		user.setPassword("test1");
		user.setUsername(username);
		user.addRole(UserRole.USER);

		userDAO.persist(user);
		assertNotNull(user);
		assertNotNull(user.getId());
		log.info("init(): user id=" + user.getId());
		UserEntity u2 = userDAO.findByUserName(username);
		assertEquals(user, u2);
	}

	@After
	public void clean() {
		// userDAO.remove(user);
	}
}
