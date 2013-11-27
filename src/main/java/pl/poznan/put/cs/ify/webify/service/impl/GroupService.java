package pl.poznan.put.cs.ify.webify.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IGroupPermissionDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupPermissionEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.GroupPermission;
import pl.poznan.put.cs.ify.webify.service.IGroupService;

@Component
public class GroupService implements IGroupService {

	@Autowired
	private IGroupPermissionDAO groupPermissionDAO;

	@Autowired
	private IGroupDAO groupDAO;

	@Autowired
	private IUserDAO userDAO;

	@Override
	@Transactional
	public void addGroupMember(final UserEntity admin, final GroupEntity group,
			final UserEntity user) {
		if (!canAdd(group, admin)) {
			return;// wyjatek??
		}
		GroupPermissionEntity groupPermission = groupPermissionDAO.find(user,
				group);
		if (groupPermission == null) {
			groupPermission = new GroupPermissionEntity();
			groupPermission.setUser(user);
			groupPermission.setGroup(group);
			groupPermissionDAO.persist(groupPermission);
		} else {
			if (groupPermission.isX() && groupPermission.isR()) {
				return;// może tylko X??
			}
			groupPermission.setR(true);
			groupPermission.setX(true);
			groupPermissionDAO.merge(groupPermission);
		}
	}

	@Override
	@Transactional
	public void addPermission(final UserEntity user, final GroupEntity group,
			final GroupPermission... permission) {
		setParameters(user, group, true, permission);
	}

	@Override
	@Transactional
	public void addPermissions(final UserEntity user, final GroupEntity group,
			final Collection<GroupPermission> permissions) {
		addPermission(user, group, (GroupPermission[]) permissions.toArray());
	}

	@Override
	@Transactional(readOnly = true)
	public boolean canAdd(final GroupEntity group, final UserEntity user) {
		return hasPermition(user, group, GroupPermission.A);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean canDelete(final GroupEntity group, final UserEntity user) {
		return hasPermition(user, group, GroupPermission.D);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean canList(final GroupEntity group, final UserEntity user) {
		return hasPermition(user, group, GroupPermission.R);
	}

	@Transactional
	public GroupEntity createGroupe(final UserEntity creator, final String name) {
		if (creator == null) {
			throw new NullPointerException();
		}
		if (name == null) {
			throw new NullPointerException();
		}
		if (name.isEmpty()) {
			throw new IllegalStateException();
		}

		final GroupEntity group = new GroupEntity();
		group.setName(name);
		group.setOwner(creator);
		groupDAO.persist(group);

		final GroupPermissionEntity groupPermissionEntity = new GroupPermissionEntity();
		groupPermissionEntity.setUser(creator);
		groupPermissionEntity.setGroup(group);
		groupPermissionEntity.setD(true);
		groupPermissionEntity.setA(true);
		groupPermissionEntity.setR(true);
		groupPermissionEntity.setX(true);
		groupPermissionDAO.persist(groupPermissionEntity);

		group.addUser(groupPermissionEntity);
		return groupDAO.merge(group);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupEntity> getGroups(final UserEntity user) {
		return groupDAO.findByUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserEntity> getMembers(final GroupEntity group) {
		return userDAO.findMembersByGroup(group);
	}

	@Transactional(readOnly = true)
	public List<UserEntity> getUsers(final GroupEntity group) {
		return userDAO.findByGroup(group);
	}

	@Transactional(readOnly = true)
	protected boolean hasPermision(final GroupPermission permission,
			final GroupPermissionEntity groupPermmison) {
		switch (permission) {
		case D:
			return groupPermmison.isD();
		case A:
			return groupPermmison.isA();
		case R:
			return groupPermmison.isR();
		case X:
			return groupPermmison.isX();
		default:
			throw new UnsupportedOperationException();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean hasPermition(final UserEntity user, final GroupEntity group,
			final GroupPermission permission) {
		final GroupPermissionEntity groupPermmison = groupPermissionDAO.find(
				user, group);
		if (groupPermmison == null) {
			return false;
		}
		return hasPermision(permission, groupPermmison);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isGroupOvner(final GroupEntity group, final UserEntity user) {
		return group.getOwner().equals(user);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isMember(final GroupEntity group, final UserEntity user) {
		return hasPermition(user, group, GroupPermission.X);
	}

	@Override
	@Transactional
	public void removeGroupMember(final UserEntity admin,
			final GroupEntity entity, final UserEntity user) {

	}

	@Override
	@Transactional
	public void removePermission(final UserEntity user,
			final GroupEntity group, final GroupPermission... permissions) {
		setParameters(user, group, false, permissions);
	}

	@Override
	@Transactional
	public void removePermissions(final UserEntity user,
			final GroupEntity group,
			final Collection<GroupPermission> permissions) {
		removePermission(user, group, (GroupPermission[]) permissions.toArray());
	}

	@Transactional
	protected void setParameters(final UserEntity user,
			final GroupEntity group, final boolean value,
			final GroupPermission... permissions) {
		final GroupPermissionEntity groupPermission = groupPermissionDAO.find(
				user, group);
		for (final GroupPermission p : permissions) {
			setPermission(groupPermission, p, value);
		}
	}

	protected void setPermission(final GroupPermissionEntity groupPermission,
			final GroupPermission permission, final boolean value) {
		switch (permission) {
		case D:
			groupPermission.setD(value);
			break;
		case A:
			groupPermission.setA(value);
			break;
		case R:
			groupPermission.setR(value);
			break;
		case X:
			groupPermission.setX(value);
			break;
		default:
			throw new UnsupportedOperationException();
		}
	}

	@Override
	@Transactional
	public void setPermission(final UserEntity user, final GroupEntity group,
			final boolean d, final boolean a, final boolean r, final boolean x) {
		final GroupPermissionEntity groupPermission = groupPermissionDAO.find(
				user, group);
		groupPermission.setA(a);
		groupPermission.setD(d);
		groupPermission.setR(r);
		groupPermission.setX(x);
		groupPermissionDAO.merge(groupPermission);
	}
}
