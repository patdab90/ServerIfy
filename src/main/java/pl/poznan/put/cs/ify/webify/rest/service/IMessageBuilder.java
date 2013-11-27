package pl.poznan.put.cs.ify.webify.rest.service;

import java.util.List;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.rest.model.Message;

public interface IMessageBuilder {
	Message build();

	Message build(Message body);

	IMessageBuilder user(UserEntity user);

	IMessageBuilder group(GroupEntity group);

	IMessageBuilder params(List<ParameterEntity> params);

	IMessageBuilder recipe(String recipe);

	IMessageBuilder device(String device);

	IMessageBuilder event(UserEntity target, int tag);

	IMessageBuilder message(Message message);
}
