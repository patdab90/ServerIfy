package pl.poznan.put.cs.ify.webify.rest.service.impl;

import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.rest.model.MessageUserModel;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageUserBuilder;

public class MessageUserBuilder implements IMessageUserBuilder {

	private UserEntity user;

	@Override
	public MessageUserModel build() {
		MessageUserModel u = new MessageUserModel();
		u.setUserName(user.getUsername());
		u.setLastName(user.getLastName());
		u.setFirstName(user.getFirstName());
		return u;
	}

	@Override
	public MessageUserModel build(UserEntity user) {
		this.user = user;
		return build();
	}

	@Override
	public IMessageUserBuilder user(UserEntity user) {
		this.user = user;
		return this;
	}
}
