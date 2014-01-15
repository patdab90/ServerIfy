package pl.poznan.put.cs.ify.webify.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.poznan.put.cs.ify.webify.rest.model.Message;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageService;

@Controller
@Path("/recipe")
public class GroupRecipeControler {

	@Autowired
	private IMessageService messageService;

	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveEvent(Message message) {
		messageService.pushMessage(message);
		return Response.ok().build();
	}

	@POST
	@Path("/load")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message loadEvent(Message message) {
		return messageService.pullMessage(message);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message exe(Message message) {
		return messageService.execute(message);
	}
}
