package pl.poznan.put.cs.ify.webify.data.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IEventQueueDAO;
import pl.poznan.put.cs.ify.webify.data.entity.receip.EventQueueEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Repository
public class EventQueueDAO extends BaseDAO<EventQueueEntity> implements
		IEventQueueDAO {

	public EventQueueDAO() {
		super(EventQueueEntity.class);
	}

	@Override
	@Transactional
	public EventQueueEntity findCurrent(UserEntity target) {
		TypedQuery<EventQueueEntity> q = getManager()
				.createQuery(
						"SELECT e FROM EventQueueEntity e WHERE e.targetUser.id = :target_id ORDER BY e.createdDate ASC",
						cls);
		// q.setMaxResults(1);
		q.setParameter("target_id", target.getId());
		return getSingleResult(q);
	}

}
