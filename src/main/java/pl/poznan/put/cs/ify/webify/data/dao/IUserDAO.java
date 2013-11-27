package pl.poznan.put.cs.ify.webify.data.dao;

import java.util.List;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

public interface IUserDAO extends IBaseDAO<UserEntity> {
	UserEntity findByUserName(String username);

	UserEntity findByNames(String fName, String lName);

	List<UserEntity> findByGroup(GroupEntity group);

	List<UserEntity> findMembersByGroup(GroupEntity group);
}
