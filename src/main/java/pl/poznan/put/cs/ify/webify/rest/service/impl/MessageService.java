package pl.poznan.put.cs.ify.webify.rest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IParameterDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.EventQueueEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.rest.model.Message;
import pl.poznan.put.cs.ify.webify.rest.model.MessageEvent;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageBuilder;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageParser;
import pl.poznan.put.cs.ify.webify.rest.service.IMessageService;
import pl.poznan.put.cs.ify.webify.service.IEventQueueService;
import pl.poznan.put.cs.ify.webify.service.IParameterService;

@Component
public class MessageService implements IMessageService {

	static private Logger log = LoggerFactory.getLogger(MessageService.class);

	@Autowired
	private IParameterService parameterBo;

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IGroupDAO groupDAO;

	@Autowired
	private IParameterDAO parameterDAO;

	@Autowired
	private IEventQueueService queueService;

	@Override
	public IMessageBuilder getBuilder() {
		return new MessageBuilder(parameterBo);
	}

	@Override
	public IMessageBuilder getBuilder(Message message) {
		return getBuilder().message(message);
	}

	@Override
	public IMessageParser getParser() {
		log.info("getParser() userDAO=" + userDAO);
		return new MessageParser(userDAO, groupDAO, parameterBo);
	}

	@Override
	@Transactional
	public IMessageParser getParser(Message message) {
		IMessageParser p = getParser().message(message);
		p.parse();
		return p;
	}

	@Override
	@Transactional
	public Message execute(Message message) {
		log.info("execute()");
		IMessageParser parser = getParser(message);
		UserEntity target = parser.getTarget();
		GroupEntity group = parser.getGroup();
		String recipe = parser.getRecipe();
		String device = parser.getDevice();
		int tag = parser.getTag();
		if (tag == MessageEvent.PUT_DATA_EVENT) {// SEND_DATA
			return putData(parser, group, recipe, device);
		} else if (tag == MessageEvent.GET_DATA_EVENT) {// GET_DATA
			log.info("execute() GET_DATA_EVENT");
			return getDataExecution(message, target, group, recipe, device);
		} else if (tag == -2) {
			// TODO
		} else if (tag == -3) {
			// TODO
		} else if (tag == MessageEvent.PULL_EVENT) {
			return pullMessage(message);
		} else if (tag > 0) {
			pushMessage(message);
		}
		return null;
	}

	public Message putData(IMessageParser parser, GroupEntity group,
			String recipe, String device) {
		log.info("execute() PUT_DATA_EVENT");
		List<ParameterEntity> params = parser.getParameters();
		for (ParameterEntity p : params) {
			log.debug("parameter=" + p);
			ParameterEntity pe = parameterDAO.find(p.getName(), group, recipe,
					device);
			if (pe == null) {
				parameterDAO.persist(p);
			} else {
				p.setId(pe.getId());
				parameterDAO.merge(p);
			}
		}
		return null;
	}

	protected Message getDataExecution(Message message, UserEntity target,
			GroupEntity group, String recipe, String device) {
		List<ParameterEntity> params = null;
		if (target == null) {
			params = parameterDAO.find(group, recipe);
		} else {
			params = parameterDAO.find(target, group, recipe, device);
		}
		IMessageBuilder builder = getBuilder(message);
		return builder.params(params).build();
	}

	@Override
	@Transactional
	public void pushMessage(Message message) {
		IMessageParser parser = getParser(message);
		parser.parse();
		UserEntity sourceUser = parser.getUser();
		UserEntity targetUser = parser.getTarget();
		Object dataObject = message;

		EventQueueEntity element = new EventQueueEntity();
		element.setSourceUser(sourceUser);
		element.setTargetUser(targetUser);
		element.setDataObject(dataObject);

		queueService.push(element);
	}

	@Override
	@Transactional
	public Message pullMessage(Message message) {
		IMessageParser parser = getParser(message);
		parser.parse();
		UserEntity targetUser = parser.getUser();
		EventQueueEntity element = queueService.pull(targetUser);
		Object data = element.getDataObject();
		return (Message) data;
	}
}
