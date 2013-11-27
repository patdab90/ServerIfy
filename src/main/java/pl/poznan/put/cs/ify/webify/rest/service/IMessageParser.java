package pl.poznan.put.cs.ify.webify.rest.service;

import java.util.List;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.rest.model.Message;

public interface IMessageParser {
	void parse(Message message);

	void parse();

	IMessageParser message(Message message);

	UserEntity getUser();

	GroupEntity getGroup();

	UserEntity getTarget();

	int getTag();

	List<ParameterEntity> getParameters();

	String getRecipe();

	String getDevice();
}
