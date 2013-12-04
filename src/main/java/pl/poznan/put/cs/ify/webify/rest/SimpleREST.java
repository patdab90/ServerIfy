package pl.poznan.put.cs.ify.webify.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
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
		return m;
	}

	@GET
	@Path(value = "/get")
	@Produces(value = "application/json")
	public MessageParam get() {
		UserEntity user = new UserEntity();
		user.setUsername("patryk");
		GroupEntity group = new GroupEntity();
		group.setName("grupa1");
		return new MessageParam();// messageService.getBuilder().group(group).user(user)
		// .event(user, 10).params(null).build();
	}
}