package pl.poznan.put.cs.ify.webify.service;

import pl.poznan.put.cs.ify.webify.data.entity.receip.EventQueueEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

public interface IEventQueueService extends IBaseService<EventQueueEntity> {
	EventQueueEntity pull(UserEntity target);

	void push(EventQueueEntity element);
}
