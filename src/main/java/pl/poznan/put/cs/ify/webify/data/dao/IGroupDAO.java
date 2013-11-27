package pl.poznan.put.cs.ify.webify.data.dao;

import java.util.List;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

public interface IGroupDAO extends IBaseDAO<GroupEntity> {

	GroupEntity findByName(String groupname);

	List<GroupEntity> findByUser(UserEntity user);
}
