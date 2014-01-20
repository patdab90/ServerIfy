package pl.poznan.put.cs.ify.webify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IEventQueueDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.EventQueueEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.service.IEventQueueService;

@Component
public class EventQueueService implements IEventQueueService {

	@Autowired
	private IEventQueueDAO queueDAO;

	@Override
	@Transactional
	public EventQueueEntity pull(UserEntity target, String recipe,
			GroupEntity group) {
		if (target == null || group == null) {
			return null;
		}
		EventQueueEntity element = queueDAO.findCurrent(target, recipe, group);
		if (element != null)
			queueDAO.remove(element);
		return element;
	}

	@Override
	@Transactional
	public void push(EventQueueEntity element) {
		queueDAO.persist(element);
	}

	@Override
	public EventQueueEntity createQueueElement(Object dataObject,
			UserEntity sourceUser, UserEntity targetUser, String recipe,
			GroupEntity group) {
		EventQueueEntity element = new EventQueueEntity();
		element.setSourceUser(sourceUser);
		element.setTargetUser(targetUser);
		element.setDataObject(dataObject);
		element.setRecipe(recipe);
		element.setGroup(group);
		return element;
	}

	@Override
	@Transactional
	public void pushQueueElement(Object dataObject, UserEntity sourceUser,
			UserEntity userEntity, String recipe, GroupEntity group) {
		EventQueueEntity element = createQueueElement(dataObject, sourceUser,
				userEntity, recipe, group);
		push(element);
	}
}
