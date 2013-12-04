package pl.poznan.put.cs.ify.webify.rest.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.rest.model.Message;
import pl.poznan.put.cs.ify.webify.rest.model.MessageEvent;
import pl.poznan.put.cs.ify.webify.rest.model.MessageParam;
import pl.poznan.put.cs.ify.webify.rest.model.MessageUser;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageParser;
import pl.poznan.put.cs.ify.webify.service.IParameterService;

public class MessageParser implements IMessageParser {

	private Message message;
	private UserEntity user;
	private GroupEntity group;
	private UserEntity target;
	private int tag;
	private List<ParameterEntity> parameters;
	private String recipe;

	private IUserDAO userDAO;
	private IGroupDAO groupDAO;
	private IParameterService parameterService;
	private String device;

	public MessageParser(IUserDAO userDAO, IGroupDAO groupDAO,
			IParameterService parameterService) {
		this.userDAO = userDAO;
		this.groupDAO = groupDAO;
		this.parameterService = parameterService;
	}

	@Override
	public void parse(Message message) {
		this.message = message;
		parse();
	}

	@Override
	@Transactional
	public void parse() {
		MessageUser mu = message.getUser();
		device = mu.getDevice();
		recipe = mu.getRecipe();
		user = userDAO.findByUserName(mu.getUsername());
		group = groupDAO.findByName(mu.getGroup());

		MessageEvent me = message.getEvent();
		target = me.getTarget() != null ? userDAO
				.findByUserName(me.getTarget()) : null;
		tag = me.getTag();
		parameters = parseParameters();
	}

	@Transactional
	protected List<ParameterEntity> parseParameters() {
		List<ParameterEntity> param = new LinkedList<ParameterEntity>();
		Map<String, MessageParam> map = message.getValues();
		for (String key : map.keySet()) {
			MessageParam mp = map.get(key);
			String type = mp.getType();
			ParameterEntity p = createPatameter(key, type, mp.getValue());
			param.add(p);
		}
		return param;
	}

	protected ParameterEntity createPatameter(String name, String type,
			String value) {
		ParameterEntity p = new ParameterEntity();
		p.setName(name);
		p.setDevice(device);
		p.setRecipe(recipe);
		p.setUser(user);
		p.setGroup(group);
		p.setType(type);
		parameterService.setValue(p, value);
		return p;
	}

	@Override
	public IMessageParser message(Message message) {
		this.message = message;
		return this;
	}

	@Override
	public UserEntity getUser() {
		return user;
	}

	@Override
	public GroupEntity getGroup() {
		return group;
	}

	@Override
	public UserEntity getTarget() {
		return target;
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public List<ParameterEntity> getParameters() {
		return parameters;
	}

	@Override
	public String getRecipe() {
		return recipe;
	}

	@Override
	public String getDevice() {
		return device;
	}

}
