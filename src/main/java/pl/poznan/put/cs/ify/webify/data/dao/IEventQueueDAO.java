package pl.poznan.put.cs.ify.webify.data.dao;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.EventQueueEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

public interface IEventQueueDAO extends IBaseDAO<EventQueueEntity> {

	/**
	 * 
	 * @param target
	 * @param recipe
	 * @param group
	 * @return
	 */
	EventQueueEntity findCurrent(UserEntity target, String recipe,
			GroupEntity group);
}
