package pl.poznan.put.cs.ify.webify.data.dao.impl;

import java.util.List;

import javax.persistence.Query;
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
	@Transactional(readOnly = true)
	public List<ParameterEntity> find(GroupEntity group, String recipe,
			String device) {
		TypedQuery<ParameterEntity> q = getManager()
				.createNamedQuery(
						"SELECT p FROM ParameterEntity "
								+ "WHERE p.group.id = :groupId AND p.recipe = :recipe AND p.device = :device",
						cls);
		q.setParameter("groupId", group.getId());
		q.setParameter("recipe", recipe);
		q.setParameter("device", device);
		return q.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public ParameterEntity find(String name, GroupEntity group, String recipe,
			String device) {
		TypedQuery<ParameterEntity> q = getManager().createNamedQuery(
				"SELECT p FROM ParameterEntity "
						+ "WHERE p.name = :name AND p.group.id = :groupId "
						+ "AND p.recipe = :recipe AND p.device = :device", cls);
		q.setParameter("groupId", group.getId());
		q.setParameter("recipe", recipe);
		q.setParameter("device", device);
		q.setParameter("name", name);
		return getSingleResult(q);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParameterEntity> find(UserEntity user, GroupEntity group,
			String recipe, String device) {
		TypedQuery<ParameterEntity> q = getManager()
				.createNamedQuery(
						"SELECT p FROM ParameterEntity "
								+ "WHERE p.user.id = :userId AND p.group.id = :groupId "
								+ "AND p.recipe = :recipe AND p.device = :device",
						cls);
		q.setParameter("userId", user.getId());
		q.setParameter("groupId", group.getId());
		q.setParameter("recipe", recipe);
		q.setParameter("device", device);
		return q.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public ParameterEntity find(String name, UserEntity user,
			GroupEntity group, String recipe, String device) {
		TypedQuery<ParameterEntity> q = getManager()
				.createNamedQuery(
						"SELECT p FROM ParameterEntity WHERE "
								+ "WHERE p.name = :name AND p.user.id = :userId AND p.group.id = :groupId "
								+ "AND p.recipe = :recipe AND p.device = :device",
						cls);
		q.setParameter("name", name);
		q.setParameter("userId", user.getId());
		q.setParameter("groupId", group.getId());
		q.setParameter("recipe", recipe);
		q.setParameter("device", device);
		return getSingleResult(q);
	}

	@Override
	public void update(ParameterEntity param) {
		// Query q = getManager()
		// .createNativeQuery(
		// "INSERT INTO parameters (a,b,c) VALUES (1,2,3) ON DUPLICATE KEY UPDATE c=c+1; UPDATE table SET c=c+1 WHERE a=1;");

	}

}
