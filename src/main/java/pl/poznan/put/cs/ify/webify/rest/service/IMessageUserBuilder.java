package pl.poznan.put.cs.ify.webify.rest.service;

import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.rest.model.MessageUserModel;

public interface IMessageUserBuilder {
	MessageUserModel build();

	MessageUserModel build(UserEntity user);

	IMessageUserBuilder user(UserEntity user);
}
