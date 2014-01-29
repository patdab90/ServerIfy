package pl.poznan.put.cs.ify.webify.rest.service;

import javax.naming.AuthenticationException;

import pl.poznan.put.cs.ify.webify.rest.model.Message;

public interface IMessageService {
	public IMessageBuilder getBuilder();

	public IMessageParser getParser();

	Message execute(Message message) throws AuthenticationException,
			IllegalAccessException;

	IMessageParser getParser(Message message);

	IMessageBuilder getBuilder(Message message);

	void pushMessage(Message message) throws AuthenticationException,
			IllegalAccessException;

	Message pullMessage(Message message) throws AuthenticationException,
			IllegalAccessException;
}
