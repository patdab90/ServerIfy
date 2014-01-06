package pl.poznan.put.cs.ify.webify.data.dao;

import pl.poznan.put.cs.ify.webify.data.entity.receip.EventQueueEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

public interface IEventQueueDAO extends IBaseDAO<EventQueueEntity> {

	EventQueueEntity findCurrent(UserEntity target);
}
