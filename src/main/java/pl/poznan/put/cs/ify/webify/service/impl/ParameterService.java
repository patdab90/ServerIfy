package pl.poznan.put.cs.ify.webify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.poznan.put.cs.ify.webify.data.dao.IParameterDAO;
import pl.poznan.put.cs.ify.webify.data.entity.receip.ParameterEntity;
import pl.poznan.put.cs.ify.webify.service.IBaseService;
import pl.poznan.put.cs.ify.webify.service.IParameterService;

@Component
public class ParameterService implements IParameterService,
		IBaseService<ParameterEntity> {

	@Autowired
	private IParameterDAO parameterDAO;

	public String getValue(ParameterEntity param) {
		return getValue(param, param.getType().getCls()).toString();
	}

	// TODO mmoże mozliwe jest wykonanie tego na bazie?
	@SuppressWarnings("unchecked")
	public <T> T getValue(ParameterEntity param, Class<T> cls) {
		Object res;
		switch (param.getType()) {
		case STRING:
			res = param.getStringValue();
			break;
		case INTEGER:
			res = param.getIntegerValue();
			break;
		case BYTE_ARRAY:
			res = param.getLobValue();
			break;
		case DOUBLE:
			res = param.getDoubleValue();
			break;
		case BOOLEAN:
			res = param.getBooleanValue();
			break;
		default:
			throw new UnsupportedOperationException("Type " + param.getType()
					+ " is unsuported!");
		}
		return (T) res;
	}

	public void setType(ParameterEntity param, String value) {
		switch (param.getType()) {
		case STRING:
			param.setStringValue(value);
			break;
		case INTEGER:
			param.setIntegerValue(Integer.parseInt(value));
			break;
		case BYTE_ARRAY:
			throw new UnsupportedOperationException();
		case DOUBLE:
			param.setDoubleValue(Double.parseDouble(value));
			break;
		case BOOLEAN:
			param.setBooleanValue(Boolean.parseBoolean(value));
			break;
		default:
			throw new UnsupportedOperationException("Type " + param.getType()
					+ " is unsuported!");
		}
	}
}
