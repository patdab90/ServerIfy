package pl.poznan.put.cs.ify.webify.service;

import java.util.Collection;
import java.util.List;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.data.enums.GroupPermission;

public interface IGroupService extends IBaseService<GroupEntity> {

	void addGroupMember(UserEntity admin, GroupEntity entity, UserEntity user);

	void removeGroupMember(UserEntity admin, GroupEntity entity, UserEntity user);

	void setPermission(UserEntity user, GroupEntity group, boolean d,
			boolean a, boolean r, boolean x);

	void addPermission(UserEntity user, GroupEntity group,
			GroupPermission... permission);

	void addPermissions(UserEntity user, GroupEntity group,
			Collection<GroupPermission> permissions);

	void removePermission(UserEntity user, GroupEntity group,
			GroupPermission... permissions);

	void removePermissions(UserEntity user, GroupEntity group,
			Collection<GroupPermission> permissions);

	List<UserEntity> getMembers(GroupEntity group);

	List<GroupEntity> getGroups(UserEntity user);

	@Deprecated
	List<GroupEntity> getGroupsByUsername(String username);

	boolean hasPermition(UserEntity user, GroupEntity group,
			GroupPermission permission);

	boolean isMember(GroupEntity group, UserEntity user);

	boolean isGroupOvner(GroupEntity group, UserEntity user);

	boolean canAdd(GroupEntity group, UserEntity user);

	boolean canDelete(GroupEntity group, UserEntity user);

	boolean canList(GroupEntity group, UserEntity user);

	GroupEntity createGroupe(UserEntity creator, String name);

	GroupEntity findByName(String name);

	List<GroupEntity> getUserInvitedGroups(UserEntity user);

}
