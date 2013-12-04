package pl.poznan.put.cs.ify.webify.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Repository
public class GroupDAO extends BaseDAO<GroupEntity> implements IGroupDAO {

	public GroupDAO() {
		super(GroupEntity.class);
	}

	@Override
	@Transactional(readOnly = true)
	public GroupEntity findByName(String groupname) {
		TypedQuery<GroupEntity> q = (TypedQuery<GroupEntity>) getManager()
				.createNamedQuery(
						"SELECT g FROM GroupEntity WHERE g.name LIKE :name",
						cls);
		q.setParameter("name", groupname);
		return getSingleResult(q);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupEntity> findByUser(UserEntity user) {
		TypedQuery<GroupEntity> q = getManager()
				.createNamedQuery(
						"SELECT g FROM GroupEntity g, GroupPermissionEntity gp, UserEntity u WHERE gp.user.id = :userId AND gp.group.id = :g.id",
						cls);
		q.setParameter("userId", user.getId());
		return q.getResultList();
	}
}