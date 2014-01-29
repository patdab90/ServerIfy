package pl.poznan.put.cs.ify.webify.data.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;

@Repository
public class GroupDAO extends BaseDAO<GroupEntity> implements IGroupDAO {

	public GroupDAO() {
		super(GroupEntity.class);
	}

	@Override
	@Transactional
	public GroupEntity findByName(String groupname) {
		TypedQuery<GroupEntity> q = getManager().createQuery(
				"SELECT g FROM GroupEntity g WHERE g.name = :name", cls);
		q.setParameter("name", groupname);
		return getSingleResult(q);
	}

}
