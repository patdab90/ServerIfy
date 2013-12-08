package pl.poznan.put.cs.ify.webify.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Repository
public class UserDAO extends BaseDAO<UserEntity> implements IUserDAO {

	public UserDAO() {
		super(UserEntity.class);
	}

	@Override
	@Transactional(readOnly = true)
	public UserEntity findByNames(final String fName, final String lName) {

		final TypedQuery<UserEntity> query = getManager()
				.createQuery(
						"SELECT u FROM UserEntity u WHERE u.firstName = :fName AND u.lastName = :lName",
						cls);
		query.setParameter("fName", fName);
		query.setParameter("lName", lName);
		return getSingleResult(query);
	}

	@Override
	@Transactional
	public UserEntity findByUserName(final String username) {

		final TypedQuery<UserEntity> query = getManager().createQuery(
				"SELECT u FROM UserEntity u WHERE u.username=:username", cls);
		query.setParameter("username", username);
		return getSingleResult(query);

	}

	@Override
	@Transactional(readOnly = true)
	public List<UserEntity> findMembersByGroup(GroupEntity group) {
		TypedQuery<UserEntity> q = getManager().createQuery(
				"SELECT u FROM UserEntity u, GroupPermissionEntity gp, GroupEntity g "
						+ "WHERE gp.user.id = :u.id "
						+ "AND gp.group.id = :groupId AND gp.x = true", cls);
		q.setParameter("groupId", group.getId());
		return q.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserEntity> findByGroup(GroupEntity group) {
		TypedQuery<UserEntity> q = getManager().createQuery(
				"SELECT u FROM UserEntity u, GroupPermissionEntity gp, GroupEntity g "
						+ "WHERE gp.user.id = :u.id "
						+ "AND gp.group.id = :groupId ", cls);
		q.setParameter("groupId", group.getId());
		return q.getResultList();
	}
}
