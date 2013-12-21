package pl.poznan.put.cs.ify.webify.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.poznan.put.cs.ify.webify.rest.model.LoginMessage;
import pl.poznan.put.cs.ify.webify.service.IUserService;

@Controller
@Path("/login")
public class LoginControler {

	@Autowired
	private IUserService userService;

	@POST
	@Path("/l")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean login(LoginMessage message) {
		return userService.login(message.getUser(), message.getPassword());
	}

	@POST
	@Path("/{user}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean login2(@PathParam("user") String user,
			@PathParam("password") String password) {
		return userService.login(user, password);
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
}
