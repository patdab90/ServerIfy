package pl.poznan.put.cs.ify.webify.rest.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.naming.AuthenticationException;

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
import pl.poznan.put.cs.ify.webify.data.dao.IGroupPermissionDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IParameterDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupPermissionEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.EventQueueEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;
import pl.poznan.put.cs.ify.webify.rest.model.Message;
import pl.poznan.put.cs.ify.webify.rest.model.MessageEvent;
import pl.poznan.put.cs.ify.webify.rest.model.MessageParam;
import pl.poznan.put.cs.ify.webify.rest.model.MessageUser;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageService;
import pl.poznan.put.cs.ify.webify.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:pl/poznan/put/cs/ify/webify/spring-context.xml" })
@ActiveProfiles("test")
public class MessageServiceTest {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IMessageService service;
	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private IGroupDAO groupDAO;
	private Message message;
	private GroupEntity groupEntity;
	private UserEntity userEntity;
	@Autowired
	private IGroupPermissionDAO groupPermissionDAO;
	@Autowired
	private IEventQueueDAO eventQueueDAO;

	@Autowired
	private IParameterDAO parameterDAO;

	@Test
	@Transactional
	public void getDataExecutionTest1() throws AuthenticationException,
			IllegalAccessException {
		init();
		log.info("getDateExecutionTest1()");
		assertNotNull("Service is null", service);
		assertNotNull(message);

		message.getEvent().setTag(MessageEvent.GETDATAEVENT);
		log.debug("getDataExecutionTest1 test <<");
		Message result = service.execute(message);
		log.debug("getDataExecutionTest1 test >>");
		log.info("getDateExecutionTest1(): result=" + result);
		assertNotNull(result);
		assertNull(result.getValues());
	}

	@Test
	@Transactional
	public void getDataExecutionTest2() throws AuthenticationException,
			IllegalAccessException {
		init();
		log.trace(">> ");
		final String paramname = "param_name1" + System.currentTimeMillis();
		final String paramtype = "param_type1" + System.currentTimeMillis();
		final String paramvalue = "param_value1" + System.currentTimeMillis();

		log.info("getDateExecutionTest2()");
		assertNotNull("Service is null", service);
		assertNotNull(message);

		ParameterEntity param = new ParameterEntity();
		param.setStringValue(paramvalue);
		param.setGroup(groupEntity);
		param.setName(paramname);
		param.setRecipe(message.getUser().getRecipe());
		param.setType(paramtype);
		param.setUser(userEntity);
		parameterDAO.persist(param);
		assertNotNull(param.getId());

		message.getEvent().setTag(MessageEvent.GETDATAEVENT);
		// message.getEvent().setTarget(userEntity.getUsername());
		log.debug("getDataExecutionTest2 test <<");
		Message result = service.execute(message);
		log.debug("getDataExecutionTest2 test >>");
		assertNotNull(result);
		assertNotNull(result.getValues());
		assertFalse(result.getValues().isEmpty());
		assertEquals(result.getValues().size(), 1);
		assertTrue(result.getValues().containsKey(paramname));
		MessageParam p1 = result.getValues().get(paramname);
		assertEquals(paramtype, p1.getType());
		assertEquals(paramvalue, p1.getValue());
	}

	@Test
	@Transactional
	public void broadcastTest() throws AuthenticationException,
			IllegalAccessException {
		log.info("init()");
		assertNotNull("User DAO is null", userDAO);
		assertNotNull("Group DAO is null", groupDAO);
		message = new Message();
		MessageEvent event = new MessageEvent("BROADCAST",
				MessageEvent.GETDATAEVENT);
		Date now = new Date();
		String time = "" + now.getTime();
		log.info("init(): time=" + time);
		String username = "username" + time;
		String group = "group" + time;
		String device = "device" + time;
		String recipe = "recipe" + time;
		MessageUser user = new MessageUser(username, StringUtils.sh1(username,
				username), group, device, recipe);
		message.setEvent(event);
		message.setUser(user);
		userEntity = new UserEntity();
		userEntity.setFirstName("test1");
		userEntity.setLastName("test1");
		userEntity.setUsername(username);
		userEntity.setPassword(username);
		log.info("broadcastTest(): username=" + userEntity.getUsername());
		userEntity.addRole(UserRole.USER);
		userDAO.persist(userEntity);
		assertNotNull(userEntity);
		assertNotNull(userEntity.getId());
		log.info("broadcastTest(): user id=" + userEntity.getId());
		assertEquals(userEntity, userDAO.findByUserName(username));

		UserEntity targetUser = new UserEntity();
		targetUser.setFirstName("test1");
		targetUser.setLastName("test1");
		targetUser.setPassword(username + "2");
		targetUser.setUsername(username + "2");
		log.info("broadcastTest(): username=" + targetUser.getUsername());
		targetUser.addRole(UserRole.USER);
		userDAO.persist(targetUser);
		assertNotNull(targetUser);
		assertNotNull(targetUser.getId());
		log.info("broadcastTest(): user id=" + targetUser.getId());
		assertEquals(userEntity, userDAO.findByUserName(username));

		groupEntity = new GroupEntity();
		groupEntity.setName(group);
		log.info("init(): group name=" + groupEntity.getName());
		groupEntity.setOwner(userEntity);
		groupDAO.persist(groupEntity);
		assertNotNull(groupEntity);
		assertNotNull(groupEntity.getId());
		log.info("init(): group id=" + groupEntity.getId());

		GroupPermissionEntity gp = new GroupPermissionEntity();
		gp.setA(true);
		gp.setD(true);
		gp.setGroup(groupEntity);
		gp.setR(true);
		gp.setX(true);
		gp.setUser(userEntity);
		groupPermissionDAO.persist(gp);
		assertNotNull(gp);
		assertNotNull(gp.getId());

		GroupPermissionEntity gp2 = new GroupPermissionEntity();
		gp2.setA(true);
		gp2.setD(true);
		gp2.setGroup(groupEntity);
		gp2.setR(true);
		gp2.setX(true);
		gp2.setUser(targetUser);
		groupPermissionDAO.persist(gp2);
		assertNotNull(gp2);
		assertNotNull(gp2.getId());

		UserEntity broadcast = userDAO.findByUserName("BROADCAST");
		assertNotNull(broadcast);

		message.getEvent().setTag(1);
		message.getEvent().setTarget(broadcast.getUsername());
		log.info("BROADCAST test <<");
		Message result = service.execute(message);
		log.info("BROADCAST test >>");
		assertNull(result);
		EventQueueEntity event1 = eventQueueDAO.findCurrent(userEntity, recipe,
				groupEntity);
		assertNotNull(event1);
		assertEquals(message, event1.getDataObject());
		EventQueueEntity event2 = eventQueueDAO.findCurrent(targetUser, recipe,
				groupEntity);
		assertNotNull(event2);
		assertEquals(message, event1.getDataObject());

	}

	@Transactional
	public void init() {
		log.info("init()");
		assertNotNull("User DAO is null", userDAO);
		assertNotNull("Group DAO is null", groupDAO);
		message = new Message();
		MessageEvent event = new MessageEvent(null, MessageEvent.GETDATAEVENT);
		Date now = new Date();
		String time = "" + now.getTime();
		log.info("init(): time=" + time);
		String username = "username" + time;
		String group = "group" + time;
		String device = "device" + time;
		String recipe = "recipe" + time;
		MessageUser user = new MessageUser(username, StringUtils.sh1(username,
				username), group, device, recipe);
		message.setEvent(event);
		message.setUser(user);

		userEntity = new UserEntity();
		userEntity.setFirstName("test1");
		userEntity.setLastName("test1");
		userEntity.setUsername(username);
		userEntity.setPassword(username);

		log.info("init(): username=" + userEntity.getUsername());
		userEntity.addRole(UserRole.USER);
		userDAO.persist(userEntity);
		assertNotNull(userEntity);
		assertNotNull(userEntity.getId());
		log.info("init(): user id=" + userEntity.getId());
		UserEntity u2 = userDAO.findByUserName(username);
		assertEquals(userEntity, u2);

		groupEntity = new GroupEntity();
		groupEntity.setName(group);
		log.info("init(): group name=" + groupEntity.getName());
		groupEntity.setOwner(userEntity);
		groupDAO.persist(groupEntity);
		assertNotNull(groupEntity);
		assertNotNull(groupEntity.getId());
		log.info("init(): group id=" + groupEntity.getId());

		GroupPermissionEntity gp = new GroupPermissionEntity();
		gp.setA(true);
		gp.setD(true);
		gp.setGroup(groupEntity);
		gp.setR(true);
		gp.setX(true);
		gp.setUser(userEntity);
		groupPermissionDAO.persist(gp);
		assertNotNull(gp);
		assertNotNull(gp.getId());
	}
}
