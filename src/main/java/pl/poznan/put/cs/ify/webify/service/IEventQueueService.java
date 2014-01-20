package pl.poznan.put.cs.ify.webify.service;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.EventQueueEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

public interface IEventQueueService extends IBaseService<EventQueueEntity> {

	void push(EventQueueEntity element);

	EventQueueEntity createQueueElement(Object dataObject,
			UserEntity sourceUser, UserEntity targetUser, String recipe,
			GroupEntity group);

	EventQueueEntity pull(UserEntity target, String recipe, GroupEntity group);

	void pushQueueElement(Object dataObject, UserEntity sourceUser,
			UserEntity userEntity, String recipe, GroupEntity group);
}
