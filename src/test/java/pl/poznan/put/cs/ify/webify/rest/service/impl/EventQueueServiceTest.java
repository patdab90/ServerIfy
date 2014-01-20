package pl.poznan.put.cs.ify.webify.rest.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IEventQueueDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.EventQueueEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;
import pl.poznan.put.cs.ify.webify.rest.model.Message;
import pl.poznan.put.cs.ify.webify.rest.model.MessageEvent;
import pl.poznan.put.cs.ify.webify.rest.model.MessageUser;
import pl.poznan.put.cs.ify.webify.service.IEventQueueService;
import pl.poznan.put.cs.ify.webify.service.IGroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:pl/poznan/put/cs/ify/webify/spring-context.xml" })
@ActiveProfiles("test")
public class EventQueueServiceTest {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	private Message message;

	private EventQueueEntity element;

	@Autowired
	private IEventQueueService queueService;

	@Autowired
	private IGroupService groupService;
	@Autowired
	private IGroupDAO groupDAO;

	@Autowired
	private IEventQueueDAO queueDAO;

	private UserEntity sourceUser;

	@Autowired
	private IUserDAO userDAO;

	private UserEntity targetUser;
	private GroupEntity groupEntity;

	@Test()
	@Transactional
	public void p1ushTest() {
		assertNull(element.getId());
		queueService.push(element);
		Long id1 = element.getId();
		assertNotNull(id1);
		assertNotNull(element.getId());
	}

	@Test
	@Transactional
	public void pullTest() {
		assertNull(element.getId());
		queueService.push(element);
		Long id1 = element.getId();
		assertNotNull(id1);
		assertNotNull(element.getId());
		assertNotNull(queueDAO.findById(id1));
		// EventQueueEntity e = queueService.pull(targetUser);
		// assertNotNull(e);
		// EventQueueEntity e2 = queueService.pull(targetUser);
		// assertNull(e2);
	}

	@Before
	@Transactional
	public void init() {
		log.info("init()");
		assertNotNull("User DAO is null", userDAO);

		Date now = new Date();
		String time = "" + now.getTime();
		String username = "username" + time;
		String targetUserName = "Targetusername" + time;
		String group = "group" + time;
		String device = "device" + time;
		String recipe = "recipe" + time;

		targetUser = new UserEntity();
		targetUser.setFirstName("test1");
		targetUser.setLastName("test1");
		targetUser.setPassword("test1");
		targetUser.addRole(UserRole.USER);

		targetUser.setUsername(targetUserName);

		sourceUser = new UserEntity();
		sourceUser.setFirstName("test1");
		sourceUser.setLastName("test1");
		sourceUser.setPassword("test1");
		sourceUser.setUsername(username);
		sourceUser.addRole(UserRole.USER);

		MessageUser user = new MessageUser(username, username, group, device,
				recipe);
		MessageEvent event = new MessageEvent(targetUserName, 1);
		log.info("init(): time=" + time);
		message = new Message();
		message.setEvent(event);
		message.setUser(user);

		log.info("init(): username=" + sourceUser.getUsername());

		userDAO.persist(sourceUser);
		assertNotNull(sourceUser);
		assertNotNull(sourceUser.getId());
		log.info("init(): user id=" + sourceUser.getId());
		UserEntity u2 = userDAO.findByUserName(username);
		assertEquals(sourceUser, u2);

		userDAO.persist(targetUser);
		assertNotNull(targetUser);
		assertNotNull(targetUser.getId());
		log.info("init(): user id=" + targetUser.getId());
		UserEntity u3 = userDAO.findByUserName(targetUserName);
		assertEquals(targetUser, u3);

		groupEntity = new GroupEntity();
		groupEntity.setName(group);
		groupEntity.setOwner(sourceUser);
		groupDAO.persist(groupEntity);
		assertNotNull(groupEntity.getId());

		groupService.addGroupMember(sourceUser, groupEntity, targetUser);

		element = new EventQueueEntity();
		element.setSourceUser(sourceUser);
		element.setTargetUser(targetUser);
		element.setDataObject(message);
		element.setGroup(groupEntity);
		element.setRecipe(recipe);
	}

	@After
	public void crean() {
		userDAO.remove(sourceUser);
		userDAO.remove(targetUser);

	}
}
