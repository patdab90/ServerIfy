package pl.poznan.put.cs.ify.webify.rest;

import java.util.LinkedList;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.rest.model.Message;
import pl.poznan.put.cs.ify.webify.rest.model.MessageGroup;
import pl.poznan.put.cs.ify.webify.rest.model.MessageUserModel;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageGroupBuilder;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageService;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageUserBuilder;
import pl.poznan.put.cs.ify.webify.rest.service.impl.MessageGroupBuilder;
import pl.poznan.put.cs.ify.webify.rest.service.impl.MessageUserBuilder;
import pl.poznan.put.cs.ify.webify.service.IGroupService;
import pl.poznan.put.cs.ify.webify.service.IUserService;

@Controller
@Path("/")
public class GroupRecipeControler {

	@Autowired
	private IMessageService messageService;

	@Autowired
	private IGroupService groupService;

	@Autowired
	private IGroupDAO groupDAO;

	@Autowired
	private IUserService userService;

	@GET
	@Path("/invitations/{user}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public List<MessageGroup> getInvitations(@PathParam("user") String user,
			@PathParam("password") String password) {
		List<MessageGroup> groupsList = new LinkedList<MessageGroup>();
		IMessageGroupBuilder builder = new MessageGroupBuilder();
		UserEntity userEntity = userService.getUser(user, password);
		if (userEntity != null) {
			List<GroupEntity> groups = groupService.getInvitations(userEntity);
			for (GroupEntity groupEntity : groups) {
				groupsList.add(builder.build(groupEntity));
			}
		}
		return groupsList;
	}

	@GET
	@Path("/invite/{member}/{group}/{user}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public boolean invite(@PathParam("user") String user,
			@PathParam("password") String password,
			@PathParam("member") String member, @PathParam("group") String group) {
		UserEntity userEntity = userService.getUser(user, password);
		if (userEntity != null) {
			GroupEntity groupEntity = groupDAO.findByName(group);
			if (groupEntity != null) {
				UserEntity memberEntity = userService.getByUsername(member);
				if (member != null) {
					groupService.inviteUser(userEntity, groupEntity,
							memberEntity);
					return true;
				}
			}
		}
		return false;
	}

	@POST
	@Path("/invitations/accept/{group}/{user}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public boolean getInvitations(@PathParam("user") String user,
			@PathParam("password") String password,
			@PathParam("group") String group) {
		UserEntity userEntity = userService.getUser(user, password);
		if (userEntity != null) {
			GroupEntity groupEntity = groupDAO.findByName(group);
			if (groupEntity != null)
				groupService.setPermission(userEntity, groupEntity, false,
						false, true, true, false);
			return true;
		}
		return false;
	}

	@GET
	@Path("/groups/{user}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public List<MessageGroup> getGroups(@PathParam("user") String user,
			@PathParam("password") String password) {
		List<MessageGroup> groupsList = new LinkedList<MessageGroup>();
		IMessageGroupBuilder builder = new MessageGroupBuilder();
		UserEntity userEntity = userService.getUser(user, password);
		if (userEntity != null) {
			List<GroupEntity> groups = groupService
					.getGroupsByMember(userEntity);
			for (GroupEntity groupEntity : groups) {
				groupsList.add(builder.build(groupEntity));
			}
		}
		return groupsList;
	}

	@GET
	@Path("/groups/members/{group}/{user}/{password}/")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public List<MessageUserModel> getMembers(@PathParam("user") String user,
			@PathParam("password") String password,
			@PathParam("group") String group) {
		List<MessageUserModel> usersList = new LinkedList<MessageUserModel>();
		IMessageUserBuilder builder = new MessageUserBuilder();
		UserEntity userEntity = userService.getUser(user, password);
		boolean member = false;
		if (userEntity != null) {
			GroupEntity groupEntity = groupDAO.findByName(group);
			if (groupEntity != null) {
				List<UserEntity> users = groupService.getMembers(groupEntity);
				for (UserEntity userEntity2 : users) {
					if (userEntity.equals(userEntity2)) {
						member = true;
					}
					usersList.add(builder.build(userEntity2));
				}
			}
		}
		if (!member) {
			return new LinkedList<MessageUserModel>();
		}
		return usersList;
	}

	@POST
	@Path("/groups/create/{group}/{user}/{password}/")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public boolean createGroup(@PathParam("user") String user,
			@PathParam("password") String password,
			@PathParam("group") String group,
			@QueryParam("members") List<String> members) {

		UserEntity userEntity = userService.getUser(user, password);
		if (userEntity != null) {
			GroupEntity groupEntity = groupService.createGroupe(userEntity,
					group);
			if (groupEntity != null) {
				for (String userName : members) {
					UserEntity member = userService.getByUsername(userName);
					if (member != null)
						groupService
								.inviteUser(userEntity, groupEntity, member);
				}
				return true;
			}
		}
		return false;
	}

	@POST
	@Path("/recipe/send")
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
	@Path("/recipe/load")
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
	@Path("/recipe")
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
