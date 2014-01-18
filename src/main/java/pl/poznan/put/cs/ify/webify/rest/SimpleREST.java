package pl.poznan.put.cs.ify.webify.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.data.dao.IEventQueueDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.user.UserRole;
import pl.poznan.put.cs.ify.webify.rest.model.LoginMessage;
import pl.poznan.put.cs.ify.webify.rest.model.Message;
import pl.poznan.put.cs.ify.webify.rest.model.MessageEvent;
import pl.poznan.put.cs.ify.webify.rest.model.MessageParam;
import pl.poznan.put.cs.ify.webify.rest.model.MessageUser;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageService;

@Component
@Path(value = "/test")
public class SimpleREST {

	@Autowired
	private IMessageService messageService;

	@Autowired
	private IEventQueueDAO queueDAO;

	private UserEntity sourceUser;

	@Autowired
	private IUserDAO userDAO;

	private UserEntity targetUser;

	private Message message;

	@POST
	@Path(value = "/post")
	@Consumes(value = "application/json")
	@Produces(value = "application/json")
	public Message post(Message message) {
		Message m = new Message();
		return message;
	}

	@GET
	@Path(value = "/get")
	@Produces(value = "application/json")
	public Request get(Request r) {

		UserEntity user = new UserEntity();
		user.setUsername("patryk");
		GroupEntity group = new GroupEntity();
		group.setName("grupa1");
		return r;// new MessageParam();//
					// messageService.getBuilder().group(group).user(user)
		// .event(user, 10).params(null).build();
	}

	@GET
	@Path(value = "/test_get")
	@Produces(value = "application/json")
	public Message get() {

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
		message = new Message();
		message.setEvent(event);
		message.setUser(user);

		userDAO.persist(sourceUser);

		userDAO.persist(targetUser);

		MessageParam p = new MessageParam();
		p.setType("String");
		p.setUsername(targetUserName);
		p.setValue("taki_text");

		MessageParam p2 = new MessageParam();
		p2.setType("Integer");
		p2.setUsername(targetUserName);
		p2.setValue("123");

		Map<String, MessageParam> map = new HashMap<String, MessageParam>();
		map.put("tekst1", p);
		map.put("liczba1", p2);
		message.setValues(map);
		return message;
	}

	@GET
	@Path(value = "/test_get2")
	@Produces(value = "application/json")
	public LoginMessage get2() {
		LoginMessage l = new LoginMessage();
		l.setUser("test");
		l.setPassword("test");
		return l;
	}

	@POST
	@Path(value = "/post2")
	@Produces(value = "application/json")
	@Consumes(value = MediaType.TEXT_HTML)
	public LoginMessage get3(String text) {
		LoginMessage l = new LoginMessage();
		l.setUser(text);
		l.setPassword("test");
		return l;
	}

}