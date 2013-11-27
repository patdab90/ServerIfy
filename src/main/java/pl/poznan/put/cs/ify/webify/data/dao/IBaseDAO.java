package pl.poznan.put.cs.ify.webify.data.dao;

import java.util.List;

import pl.poznan.put.cs.ify.webify.data.entity.BaseEntity;

public interface IBaseDAO<T extends BaseEntity> {
	void persist(T entity);

	T merge(T entity);

	void flush(T entity);

	void remove(T entity);

	T findById(final Long id, final Class<T> cls);

	List<T> findAll(final Class<T> cls);

	T findById(final Long id);

	List<T> findAll();
}
