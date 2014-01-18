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

	@Override
	@Transactional
	public void addGroupMember(final UserEntity admin, final GroupEntity group,
			final UserEntity user) {
		if (!canAdd(group, admin)) {
			log.debug("addGroupMember have no permission to add!!");
			return;// wyjatek??
		}
		log.debug("addGroupMember ");
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
			if ((groupPermission.isX() && groupPermission.isR())
					|| groupPermission.isC()) {
				return;// może tylko X??
			}
			groupPermission.setC(true);
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

		log.debug("createGroupe, group id=" + group.getId());

		GroupPermissionEntity gp = createPermission(creator, group);
		if (gp.getGroup() == null) {
			log.debug("createGroupe, group is null");
			gp.setGroup(group);
			gp = groupPermissionDAO.merge(gp);
			if (gp.getGroup() == null)
				log.error("createGroupe, group is still null");
			if (gp.getUser() == null) {
				log.debug("createGroupe, user is null");
				gp.setUser(creator);
				groupPermissionDAO.merge(gp);
				if (gp.getUser() == null)
					log.error("createGroupe, user is still null");
			}
		}
		log.error("createGroupe, group=" + gp);
		return group;
	}

	public GroupPermissionEntity createPermission(final UserEntity creator,
			final GroupEntity group) {
		final GroupPermissionEntity groupPermissionEntity = new GroupPermissionEntity();
		groupPermissionEntity.setUser(creator);
		groupPermissionEntity.setGroup(group);
		groupPermissionEntity.setD(true);
		groupPermissionEntity.setA(true);
		groupPermissionEntity.setR(true);
		groupPermissionEntity.setX(true);
		groupPermissionEntity.setC(false);
		groupPermissionDAO.persist(groupPermissionEntity);
		return groupPermissionEntity;
	}

	@Override
	@Transactional
	public List<GroupEntity> getGroups(final UserEntity user) {
		List<GroupPermissionEntity> gpl = groupPermissionDAO.find(user);
		if (gpl == null)
			return null;
		List<GroupEntity> res = new ArrayList<GroupEntity>(gpl.size());
		for (GroupPermissionEntity gp : gpl) {
			if (!gp.isC() && gp.isR())
				res.add(gp.getGroup());
		}
		return res;
		// return groupDAO.findByUser(user);
	}

	@Override
	@Transactional
	public List<GroupEntity> getUserInvitedGroups(final UserEntity user) {
		List<GroupPermissionEntity> gpl = groupPermissionDAO.findInvited(user);
		if (gpl == null)
			return null;
		List<GroupEntity> res = new ArrayList<GroupEntity>(gpl.size());
		for (GroupPermissionEntity gp : gpl) {
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
		case C:
			return groupPermmison.isC();
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
		case C:
			groupPermission.setC(value);
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

	@Override
	@Deprecated
	public List<GroupEntity> getGroupsByUsername(String username) {

		return null;
	}

	@Transactional
	@Override
	public GroupEntity findByName(String name) {
		return groupDAO.findByName(name);
	}

}
