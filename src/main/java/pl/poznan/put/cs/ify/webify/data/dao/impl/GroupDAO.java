package pl.poznan.put.cs.ify.webify.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Repository
public class GroupDAO extends BaseDAO<GroupEntity> implements IGroupDAO {

	public GroupDAO() {
		super(GroupEntity.class);
	}

	@Override
	public GroupEntity findByName(String groupname) {
		TypedQuery<GroupEntity> q = getManager().createQuery(
				"SELECT g FROM GroupEntity g WHERE g.name = :name", cls);
		q.setParameter("name", groupname);
		return getSingleResult(q);
	}

	@Override
	public List<GroupEntity> findByUser(UserEntity user) {
		TypedQuery<GroupEntity> q = getManager()
				.createQuery(
						"SELECT g FROM GroupEntity AS g JOIN GroupPermissionEntity AS gp JOIN UserEntity AS u "
								+ "WHERE gp.user.id = :userId "
								+ "AND gp.group.id = :g.id", cls);
		q.setParameter("userId", user.getId());
		return q.getResultList();
	}
}
