package pl.poznan.put.cs.ify.webify.rest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.rest.model.Message;
import pl.poznan.put.cs.ify.webify.rest.model.MessageEvent;
import pl.poznan.put.cs.ify.webify.rest.model.MessageParam;
import pl.poznan.put.cs.ify.webify.rest.model.MessageUser;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageBuilder;
import pl.poznan.put.cs.ify.webify.service.IParameterService;

public class MessageBuilder implements IMessageBuilder {

	private Message message;
	private UserEntity user;
	private GroupEntity group;
	private UserEntity target;
	private String device;
	private int tag;
	private List<ParameterEntity> parameters;
	private String recipe;
	private IParameterService parameterBo;

	public MessageBuilder(IParameterService parameterBo) {
		this.parameterBo = parameterBo;
	}

	@Override
	@Transactional
	public Message build() {
		if (message == null)
			message = new Message();
		message.setUser(buildUser());
		message.setEvent(buildEvent());
		message.setValues(buildParameters());
		return message;
	}

	@Override
	public IMessageBuilder message(Message message) {
		this.message = message;
		return this;
	}

	@Override
	@Transactional
	public Message build(Message body) {
		message = body;
		return build();
	}

	@Override
	public IMessageBuilder user(UserEntity user) {
		this.user = user;
		return this;
	}

	@Override
	public IMessageBuilder group(GroupEntity group) {
		this.group = group;
		return this;
	}

	@Override
	public IMessageBuilder params(List<ParameterEntity> params) {
		this.parameters = params;
		return this;
	}

	@Override
	public IMessageBuilder event(UserEntity target, int tag) {
		this.target = target;
		this.tag = tag;
		return this;
	}

	@Override
	public IMessageBuilder recipe(String recipe) {
		this.recipe = recipe;
		return this;
	}

	private MessageUser buildUser() {
		MessageUser mu = message.getUser();
		if (mu != null && mu.getGroup() != null && mu.getUsername() != null
				&& mu.getDevice() != null && mu.getRecipe() != null) {
			return mu;
		}
		valid(user, group);
		return new MessageUser(user.getUsername(), "", group.getName(), device,
				recipe);
	}

	private MessageEvent buildEvent() {
		MessageEvent me = message.getEvent();
		if (me != null) {
			return me;
		}
		valid(target, tag);
		return new MessageEvent(target.getUsername(), tag);
	}

	@Transactional
	private Map<String, MessageParam> buildParameters() {
		// if (message.getValues() != null && !message.getValues().isEmpty()) {
		// return message.getValues();
		// }
		if (parameters == null || parameters.isEmpty()) {
			return null;
		}
		Map<String, MessageParam> map = new HashMap<String, MessageParam>(
				parameters.size());
		for (ParameterEntity param : parameters) {
			MessageParam mp = new MessageParam();
			mp.setType(param.getType());
			mp.setValue(parameterBo.getValue(param));
			mp.setUsername(param.getUser().getUsername());

			map.put(param.getName(), mp);
		}
		return map;
	}

	private void valid(UserEntity target, int tag) {
		// if (target == null) {
		// throw new IllegalStateException();
		// }
	}

	private void valid(UserEntity u, GroupEntity g) {
		if (u == null) {
			throw new IllegalStateException(
					"user is null. Use metod user(UserEntity) before metod build().");
		}
		if (group == null) {
			throw new IllegalStateException(
					"group is null. Use metod group(GroupEntity) before metod build().");
		}
	}

	@Override
	public IMessageBuilder device(String device) {
		this.device = device;
		return this;
	}
}
