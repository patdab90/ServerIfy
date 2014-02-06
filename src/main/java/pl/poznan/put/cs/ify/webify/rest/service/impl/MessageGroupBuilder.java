package pl.poznan.put.cs.ify.webify.rest.service.impl;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.rest.model.MessageGroup;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageGroupBuilder;

public class MessageGroupBuilder implements IMessageGroupBuilder {

	private GroupEntity group;

	@Override
	public MessageGroup build(GroupEntity group) {
		this.group = group;
		return build();
	}

	@Override
	public MessageGroup build() {
		MessageGroup g = new MessageGroup();
		g.setName(group.getName());
		g.setOwner(group.getOwner().getUsername());
		return g;
	}

	@Override
	public IMessageGroupBuilder group(GroupEntity group) {
		this.group = group;
		return this;
	}

}
