package pl.poznan.put.cs.ify.webify.service;

import java.util.List;

import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

public interface IParameterService extends IBaseService<ParameterEntity> {
	String getValue(ParameterEntity param);

	void setValue(ParameterEntity param, String value);

	List<ParameterEntity> getData(UserEntity target, GroupEntity group,
			String recipe);
}
