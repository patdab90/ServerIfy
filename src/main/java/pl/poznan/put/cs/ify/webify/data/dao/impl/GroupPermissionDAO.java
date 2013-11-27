package pl.poznan.put.cs.ify.webify.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupPermissionDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupPermissionEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Repository
public class GroupPermissionDAO extends BaseDAO<GroupPermissionEntity>
		implements IGroupPermissionDAO {

	public GroupPermissionDAO() {
		super(GroupPermissionEntity.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupPermissionEntity> find(UserEntity user) {
		TypedQuery<GroupPermissionEntity> q = getManager()
				.createNamedQuery(
						"SELECT g FROM GroupPermissionEntity g WHERE g.user.id = :user",
						cls);
		q.setParameter("user", user.getId());
		return q.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupPermissionEntity> find(GroupEntity group) {
		TypedQuery<GroupPermissionEntity> q = getManager()
				.createNamedQuery(
						"SELECT g FROM GroupPermissionEntity g WHERE g.group.id = :group",
						cls);
		q.setParameter("group", group.getId());
		return q.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupPermissionEntity> findByUsername(String username) {
		TypedQuery<GroupPermissionEntity> q = getManager()
				.createNamedQuery(
						"SELECT g FROM GroupPermissionEntity g WHERE g.user.username = :username",
						cls);
		q.setParameter("username", username);
		return q.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupPermissionEntity> findByGroupName(String groupName) {
		TypedQuery<GroupPermissionEntity> q = getManager()
				.createNamedQuery(
						"SELECT g FROM GroupPermissionEntity g WHERE g.group.name = :name",
						cls);
		q.setParameter("name", groupName);
		return q.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public GroupPermissionEntity find(UserEntity user, GroupEntity group) {
		return find(user.getId(), group.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public GroupPermissionEntity find(long userId, long groupId) {
		TypedQuery<GroupPermissionEntity> q = getManager()
				.createNamedQuery(
						"SELECT gp FROM GroupPermissionEntity gp WHERE gp.user.id = :user AND gp.user.id = :group",
						cls);
		q.setParameter("user", userId);
		q.setParameter("group", groupId);
		q.setMaxResults(1);
		return getSingleResult(q);
	}

}
