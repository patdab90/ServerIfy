package pl.poznan.put.cs.ify.webify.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.rest.model.LoginMessage;
import pl.poznan.put.cs.ify.webify.service.IUserService;

@Controller
@Path("/")
public class LoginControler {

	@Autowired
	private IUserService userService;

	@POST
	@Path("/login/l")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean login(LoginMessage message) {
		return userService.login(message.getUser(), message.getPassword());
	}

	@POST
	@Path("/login/{user}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean login2(@PathParam("user") final String user,
			@PathParam("password") final String password) {
		if (user == null || password == null) {
			return false;
		}
		return userService.loginHash(user, password);
	}

	@POST
	@Path("/register/{user}/{password}/{firstName}/{secondName}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean register(@PathParam("user") final String user,
			@PathParam("password") final String password,
			@PathParam("firstName") String firstName,
			@PathParam("secondName") String secondName) {
		UserEntity userEntity = userService.getByUsername(user);
		if (userEntity == null) {
			if (password != null && !password.isEmpty()) {
				userEntity = userService.registerUser(user, password,
						firstName, secondName);
				if (userEntity != null) {
					return true;
				}
			}

		}
		return false;
	}
}
