package pl.poznan.put.cs.ify.webify.rest.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

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

import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IGroupPermissionDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IParameterDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupPermissionEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;
import pl.poznan.put.cs.ify.webify.rest.model.Message;
import pl.poznan.put.cs.ify.webify.rest.model.MessageEvent;
import pl.poznan.put.cs.ify.webify.rest.model.MessageParam;
import pl.poznan.put.cs.ify.webify.rest.model.MessageUser;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageService;

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
	private IParameterDAO parameterDAO;

	@Test
	@Ignore("Change the structure of message")
	@Transactional
	public void getDataExecutionTest1() {
		log.info("getDateExecutionTest1()");
		assertNotNull("Service is null", service);
		assertNotNull(message);

		message.getEvent().setTag(MessageEvent.GET_DATA_EVENT);
		Message result = service.execute(message);
		log.info("getDateExecutionTest1(): result=" + result);
		assertNotNull(result);
		assertNull(result.getValues());
	}

	@Test
	@Ignore("Change the structure of message")
	@Transactional
	public void getDataExecutionTest2() {

		final String paramname = "param_name1" + System.currentTimeMillis();
		final String paramtype = "param_type1" + System.currentTimeMillis();
		final String paramvalue = "param_value1" + System.currentTimeMillis();

		log.info("getDateExecutionTest2()");
		assertNotNull("Service is null", service);
		assertNotNull(message);

		ParameterEntity param = new ParameterEntity();
		param.setStringValue(paramvalue);
		param.setDevice(message.getUser().getDevice());
		param.setGroup(groupEntity);
		param.setName(paramname);
		param.setRecipe(message.getUser().getRecipe());
		param.setType(paramtype);
		param.setUser(userEntity);
		parameterDAO.persist(param);
		assertNotNull(param.getId());

		message.getEvent().setTag(MessageEvent.GET_DATA_EVENT);
		Message result = service.execute(message);
		assertNotNull(result);
		assertNotNull(result.getValues());
		assertFalse(result.getValues().isEmpty());
		assertEquals(result.getValues().size(), 1);
		assertTrue(result.getValues().containsKey(paramname));
		MessageParam p1 = result.getValues().get(paramname);
		assertEquals(paramtype, p1.getType());
		assertEquals(paramvalue, p1.getValue());
	}

	@Before
	@Transactional
	public void init() {
		log.info("init()");
		assertNotNull("User DAO is null", userDAO);
		assertNotNull("Group DAO is null", groupDAO);
		message = new Message();
		MessageEvent event = new MessageEvent(null, MessageEvent.GET_DATA_EVENT);
		Date now = new Date();
		String time = "" + now.getTime();
		log.info("init(): time=" + time);
		String username = "username" + time;
		String group = "group" + time;
		String device = "device" + time;
		String recipe = "recipe" + time;
		MessageUser user = new MessageUser(username, group, device, recipe);
		message.setEvent(event);
		message.setUser(user);

		userEntity = new UserEntity();
		userEntity.setFirstName("test1");
		userEntity.setLastName("test1");
		userEntity.setPassword("test1");
		userEntity.setUsername(username);
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
