package pl.poznan.put.cs.ify.webify.rest;

import javax.naming.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
		try {
			messageService.pushMessage(message);
		} catch (AuthenticationException e) {
			return Response.status(Status.UNAUTHORIZED).entity(e).build();
		} catch (IllegalAccessException e) {
			return Response.status(Status.METHOD_NOT_ALLOWED).entity(e).build();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/load")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loadEvent(Message message) {
		try {
			return Response.ok(messageService.pullMessage(message)).build();
		} catch (AuthenticationException e) {
			return Response.status(Status.UNAUTHORIZED).entity(e).build();
		} catch (IllegalAccessException e) {
			return Response.status(Status.METHOD_NOT_ALLOWED).entity(e).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response exe(Message message) {
		try {
			return Response.ok(messageService.execute(message)).build();
		} catch (AuthenticationException e) {
			return Response.status(Status.UNAUTHORIZED).entity(e).build();
		} catch (IllegalAccessException e) {
			return Response.status(Status.METHOD_NOT_ALLOWED).entity(e).build();
		}
	}
}
