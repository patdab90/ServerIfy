package pl.poznan.put.cs.ify.webify.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.rest.model.LoginMessage;
import pl.poznan.put.cs.ify.webify.rest.model.Message;
import pl.poznan.put.cs.ify.webify.rest.model.MessageParam;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageService;

@Component
@Path(value = "/test")
public class SimpleREST {

	@Autowired
	private IMessageService messageService;

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
	public Response get() {
		return Response.ok(true).build();
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