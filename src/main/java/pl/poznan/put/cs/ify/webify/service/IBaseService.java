package pl.poznan.put.cs.ify.webify.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.poznan.put.cs.ify.webify.data.entity.BaseEntity;

/**
 * Interfejs BO
 * 
 * @date 30-05-2013
 * 
 */
public interface IBaseService<T extends BaseEntity> {

	final static Logger log = LoggerFactory.getLogger(IBaseService.class);

}
