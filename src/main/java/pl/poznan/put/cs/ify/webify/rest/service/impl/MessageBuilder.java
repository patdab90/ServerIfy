package pl.poznan.put.cs.ify.webify.rest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Message build() {
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
	public Message build(Message body) {
		message = body;

		return message;
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
		valid(user, group);
		return new MessageUser(user.getUsername(), group.getName(), device,
				recipe);
	}

	private MessageEvent buildEvent() {
		valid(target, tag);
		return new MessageEvent(target.getUsername(), tag);
	}

	private Map<String, MessageParam> buildParameters() {// TODO przenieść
															// zamienianie Listy
															// parametrów na
															// mapę do servisu
		if (parameters == null || parameters.isEmpty()) {
			return null;
		}
		Map<String, MessageParam> map = new HashMap<String, MessageParam>(
				parameters.size());
		for (ParameterEntity param : parameters) {
			MessageParam mp = new MessageParam();
			mp.setType(param.getType().toString());// TODO zrobić tak żeby nie
													// wywoływać toString
			mp.setValue(parameterBo.getValue(param));
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
