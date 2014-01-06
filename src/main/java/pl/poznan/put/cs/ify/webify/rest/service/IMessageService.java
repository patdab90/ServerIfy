package pl.poznan.put.cs.ify.webify.rest.service;

import pl.poznan.put.cs.ify.webify.rest.model.Message;

public interface IMessageService {
	public IMessageBuilder getBuilder();

	public IMessageParser getParser();

	Message execute(Message message);

	IMessageParser getParser(Message message);

	IMessageBuilder getBuilder(Message message);

	void pushMessage(Message message);

	Message pullMessage(Message message);
}
