package pl.poznan.put.cs.ify.webify.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.data.dao.IParameterDAO;
import pl.poznan.put.cs.ify.webify.data.entity.group.GroupEntity;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;
import pl.poznan.put.cs.ify.webify.service.IParameterService;

@Component
public class ParameterService implements IParameterService {

	@Autowired
	private IParameterDAO parameterDAO;

	public String getValue(ParameterEntity param) {
		String res = null;
		String type = param.getType();
		if (type == "String") {
			res = param.getStringValue();
		} else if (type == "Integer") {
			res = param.getIntegerValue().toString();
		} else if (type == "Double") {
			res = param.getDoubleValue().toString();
		} else if (type == "Boolean") {
			res = param.getBooleanValue().toString();
		} else {
			res = param.getStringValue();
		}
		return res;
	}

	@Override
	public void setValue(ParameterEntity param, String value) {
		String type = param.getType();
		if (type == "String") {
			param.setStringValue(value);
		} else if (type == "Integer") {
			param.setIntegerValue(Integer.parseInt(value));
		} else if (type == "Double") {
			param.setDoubleValue(Double.parseDouble(value));
		} else if (type == "Boolean") {
			param.setBooleanValue(Boolean.parseBoolean(value));
		} else {
			param.setStringValue(value);
		}
	}

	@Override
	public List<ParameterEntity> getData(UserEntity target, GroupEntity group,
			String recipe) {
		List<ParameterEntity> params = null;
		if (target == null) {
			params = parameterDAO.find(group, recipe);
		} else {
			params = parameterDAO.find(target, group, recipe);
		}
		return params;
	}
}
