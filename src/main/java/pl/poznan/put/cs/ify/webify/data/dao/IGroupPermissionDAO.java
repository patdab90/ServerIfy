package pl.poznan.put.cs.ify.webify.data.dao;

import java.util.List;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupPermissionEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

public interface IGroupPermissionDAO extends IBaseDAO<GroupPermissionEntity> {
	List<GroupPermissionEntity> find(UserEntity user);

	List<GroupPermissionEntity> find(GroupEntity group);

	List<GroupPermissionEntity> findByUsername(String username);

	List<GroupPermissionEntity> findByGroupName(String groupName);

	GroupPermissionEntity find(UserEntity user, GroupEntity group);

	GroupPermissionEntity find(long userId, long groupId);
}
