package pl.poznan.put.cs.ify.webify.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IGroupDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IGroupPermissionDAO;
import pl.poznan.put.cs.ify.webify.data.dao.IUserDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupPermissionEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.GroupPermission;
import pl.poznan.put.cs.ify.webify.service.IGroupService;

@Service
public class GroupService implements IGroupService {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IGroupPermissionDAO groupPermissionDAO;

	@Autowired
	private IGroupDAO groupDAO;

	@Autowired
	private IUserDAO userDAO;

	@Transactional
	@Override
	public void inviteUser(final UserEntity admin, final GroupEntity group,
			final UserEntity user) {
		if (!canAdd(group, admin)) {
			log.debug("addGroupMember have no permission to add!!");
			return;// wyjatek??
		}
		GroupPermissionEntity groupPermission = groupPermissionDAO.find(user,
				group);
		if (groupPermission == null) {
			log.debug("addGroupMember new permission for group");
			groupPermission = new GroupPermissionEntity();
			groupPermission.setUser(user);
			groupPermission.setGroup(group);
			groupPermissionDAO.persist(groupPermission);
		} else {
			log.debug("addGroupMember modify permission for group");
			if (groupPermission.isI()
					|| (groupPermission.isX() && groupPermission.isR())) {
				return;// może tylko X??
			}
			groupPermission.setI(true);
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
	@Transactional
	public boolean canAdd(final GroupEntity group, final UserEntity user) {
		return hasPermition(user, group, GroupPermission.A);
	}

	@Override
	@Transactional
	public boolean canDelete(final GroupEntity group, final UserEntity user) {
		return hasPermition(user, group, GroupPermission.D);
	}

	@Transactional
	@Override
	public boolean canList(final GroupEntity group, final UserEntity user) {
		return hasPermition(user, group, GroupPermission.R);
	}

	@Transactional
	@Override
	public GroupEntity createGroupe(final UserEntity creator, final String name) {
		if (name.isEmpty()) {
			throw new IllegalStateException();
		}
		final GroupEntity group = new GroupEntity();
		group.setName(name);
		group.setOwner(creator);
		groupDAO.persist(group);

		log.debug("createGroupe, group id=" + group.getId());

		GroupPermissionEntity groupPermission = createPermission(creator, group);
		if (groupPermission.getGroup() == null) {// TODO może nie potrzebny!!!
			groupPermission.setGroup(group);
			groupPermission = groupPermissionDAO.merge(groupPermission);
			if (groupPermission.getUser() == null) {
				groupPermission.setUser(creator);
				groupPermission.setR(true);
				groupPermission.setX(true);
				groupPermission.setI(false);
				groupPermissionDAO.merge(groupPermission);
			}
		}
		return group;
	}

	public GroupPermissionEntity createPermission(final UserEntity creator,
			final GroupEntity group) {
		final GroupPermissionEntity groupPermissionEntity = new GroupPermissionEntity();
		groupPermissionEntity.setUser(creator);
		groupPermissionEntity.setGroup(group);
		groupPermissionEntity.setD(true);
		groupPermissionEntity.setA(true);
		groupPermissionEntity.setI(false);
		groupPermissionEntity.setR(true);
		groupPermissionEntity.setX(true);
		groupPermissionDAO.persist(groupPermissionEntity);
		return groupPermissionEntity;
	}

	/**
	 * Get greoups if user can read them.
	 */
	@Override
	@Transactional
	public List<GroupEntity> getGroupsByMember(final UserEntity user) {
		List<GroupPermissionEntity> gpl = groupPermissionDAO.find(user);
		if (gpl == null)
			return null;
		List<GroupEntity> res = new ArrayList<GroupEntity>(gpl.size());
		for (GroupPermissionEntity gp : gpl) {
			if (!gp.isI() && gp.isR())
				res.add(gp.getGroup());
		}
		return res;
	}

	@Transactional
	@Override
	public List<GroupEntity> getInvitations(final UserEntity user) {
		List<GroupPermissionEntity> gpl = groupPermissionDAO.find(user);
		if (gpl == null)
			return null;
		List<GroupEntity> res = new ArrayList<GroupEntity>(gpl.size());
		for (GroupPermissionEntity gp : gpl) {
			if (gp.isI())
				res.add(gp.getGroup());
		}
		return res;
	}

	@Override
	@Transactional
	public List<UserEntity> getMembers(final GroupEntity group) {
		return userDAO.findMembersByGroup(group);
	}

	@Transactional
	public List<UserEntity> getUsers(final GroupEntity group) {
		return userDAO.findByGroup(group);
	}

	@Transactional
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
	@Transactional
	public boolean hasPermition(final UserEntity user, final GroupEntity group,
			final GroupPermission permission) {
		final GroupPermissionEntity groupPermmison = groupPermissionDAO.find(
				user, group);
		log.debug("hasPermition groupPermmison=" + groupPermmison);
		if (groupPermmison == null) {
			return false;
		}
		return hasPermision(permission, groupPermmison);
	}

	@Override
	@Transactional
	public boolean isGroupOvner(final GroupEntity group, final UserEntity user) {
		return group.getOwner().equals(user);
	}

	@Override
	@Transactional
	public boolean isMember(final GroupEntity group, final UserEntity user) {
		return hasPermition(user, group, GroupPermission.X);
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
		case I:
			groupPermission.setI(value);
			break;
		default:
			throw new UnsupportedOperationException();
		}
	}

	@Override
	@Transactional
	public void setPermission(final UserEntity user, final GroupEntity group,
			final boolean d, final boolean a, final boolean r, final boolean x,
			final boolean i) {
		final GroupPermissionEntity groupPermission = groupPermissionDAO.find(
				user, group);
		groupPermission.setA(a);
		groupPermission.setD(d);
		groupPermission.setR(r);
		groupPermission.setX(x);
		groupPermission.setI(i);
		groupPermissionDAO.merge(groupPermission);
	}

	@Transactional
	@Override
	public GroupEntity findByName(String name) {
		return groupDAO.findByName(name);
	}

	@Override
	@Transactional
	public List<GroupEntity> getAllGroups(UserEntity user) {
		List<GroupPermissionEntity> gpl = groupPermissionDAO.find(user);
		if (gpl == null)
			return null;
		List<GroupEntity> res = new ArrayList<GroupEntity>(gpl.size());
		for (GroupPermissionEntity gp : gpl) {
			res.add(gp.getGroup());
		}
		return res;
	}

	@Transactional
	@Override
	public boolean canExecute(final GroupEntity group, final UserEntity user) {
		return hasPermition(user, group, GroupPermission.X);
	}
}
