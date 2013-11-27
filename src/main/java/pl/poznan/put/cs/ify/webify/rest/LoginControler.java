package pl.poznan.put.cs.ify.webify.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import pl.poznan.put.cs.ify.webify.rest.model.LoginMessage;

@Controller
@Path("/login")
public class LoginControler {

	@POST
	@Path(value = "/")
	@Consumes(value = "application/json")
	@Produces(value = "application/json")
	public Response login(LoginMessage message) {
		// LoginMessage ms
		return null;// Response.ok(ms).
	}
}
