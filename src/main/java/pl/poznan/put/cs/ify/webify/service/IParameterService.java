package pl.poznan.put.cs.ify.webify.service;

import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;

public interface IParameterService extends IBaseService<ParameterEntity> {
	String getValue(ParameterEntity param);

	<T> T getValue(ParameterEntity param, Class<T> cls);
}
