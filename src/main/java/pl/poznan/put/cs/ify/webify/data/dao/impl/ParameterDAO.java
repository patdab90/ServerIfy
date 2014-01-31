package pl.poznan.put.cs.ify.webify.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IParameterDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

@Repository
public class ParameterDAO extends BaseDAO<ParameterEntity> implements
		IParameterDAO {

	public ParameterDAO() {
		super(ParameterEntity.class);
	}

	@Override
	public ParameterEntity find(String name, GroupEntity group, String recipe) {
		TypedQuery<ParameterEntity> q = getManager().createQuery(
				"SELECT p FROM ParameterEntity p "
						+ "WHERE p.name = :name AND p.group.id = :groupId "
						+ "AND p.recipe = :recipe", cls);
		q.setParameter("groupId", group.getId());
		q.setParameter("recipe", recipe);
		q.setParameter("name", name);
		return getSingleResult(q);
	}

	@Override
	public List<ParameterEntity> find(UserEntity user, GroupEntity group,
			String recipe) {
		TypedQuery<ParameterEntity> q = getManager()
				.createQuery(
						"SELECT p FROM ParameterEntity p "
								+ "WHERE p.user.id = :userId AND p.group.id = :groupId "
								+ "AND p.recipe = :recipe", cls);
		q.setParameter("userId", user.getId());
		q.setParameter("groupId", group.getId());
		q.setParameter("recipe", recipe);
		return q.getResultList();
	}

	@Override
	public ParameterEntity find(String name, UserEntity user,
			GroupEntity group, String recipe) {
		TypedQuery<ParameterEntity> q = getManager()
				.createQuery(
						"SELECT p FROM ParameterEntity p "
								+ "WHERE p.name = :name AND p.user.id = :userId AND p.group.id = :groupId "
								+ "AND p.recipe = :recipe AND", cls);
		q.setParameter("name", name);
		q.setParameter("userId", user.getId());
		q.setParameter("groupId", group.getId());
		q.setParameter("recipe", recipe);
		return getSingleResult(q);
	}

	@Override
	@Deprecated
	public void update(ParameterEntity param) {
		// Query q = getManager()
		// .createNativeQuery(
		// "INSERT INTO parameters (a,b,c) VALUES (1,2,3) ON DUPLICATE KEY UPDATE c=c+1; UPDATE table SET c=c+1 WHERE a=1;");

	}

	@Override
	@Transactional
	public List<ParameterEntity> find(GroupEntity group, String recipe) {
		TypedQuery<ParameterEntity> q = getManager()
				.createQuery(
						"SELECT p FROM ParameterEntity p "
								+ "WHERE p.recipe = :recipe AND p.group.id = :groupId ",
						cls);
		q.setParameter("groupId", group.getId());
		q.setParameter("recipe", recipe);
		return q.getResultList();
	}
}
