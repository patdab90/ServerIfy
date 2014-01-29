package pl.poznan.put.cs.ify.webify.data.dao;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;

public interface IGroupDAO extends IBaseDAO<GroupEntity> {

	GroupEntity findByName(String groupname);

}
