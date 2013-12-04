package pl.poznan.put.cs.ify.webify.service;

import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;

public interface IParameterService extends IBaseService<ParameterEntity> {
	String getValue(ParameterEntity param);

	void setValue(ParameterEntity param, String value);
}
