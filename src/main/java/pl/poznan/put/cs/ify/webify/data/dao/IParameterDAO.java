package pl.poznan.put.cs.ify.webify.data.dao;

import java.util.List;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

public interface IParameterDAO extends IBaseDAO<ParameterEntity> {

	ParameterEntity find(String name, GroupEntity group, String recipe);

	List<ParameterEntity> find(UserEntity user, GroupEntity group, String recipe);

	ParameterEntity find(String name, UserEntity user, GroupEntity group,
			String recipe);

	void update(ParameterEntity param);

	List<ParameterEntity> find(GroupEntity group, String recipe);
}
