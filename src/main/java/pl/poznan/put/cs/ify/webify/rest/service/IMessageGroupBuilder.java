package pl.poznan.put.cs.ify.webify.rest.service;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.rest.model.MessageGroup;

public interface IMessageGroupBuilder {
	MessageGroup build(GroupEntity group);

	MessageGroup build();

	IMessageGroupBuilder group(GroupEntity group);
}
