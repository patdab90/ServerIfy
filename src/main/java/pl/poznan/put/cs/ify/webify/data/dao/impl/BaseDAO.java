package pl.poznan.put.cs.ify.webify.data.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import pl.poznan.put.cs.ify.webify.data.dao.IBaseDAO;
import pl.poznan.put.cs.ify.webify.data.entity.BaseEntity;

public abstract class BaseDAO<T extends BaseEntity> implements IBaseDAO<T> {

	transient protected Logger log = LoggerFactory.getLogger(this.getClass());
	transient protected final Class<T> cls;

	public BaseDAO(Class<T> cls2) {
		cls = cls2;
	}

	@PersistenceContext
	transient protected EntityManager manager;

	/**
	 * Pobranie wszystkich rekordów danego typu
	 * 
	 * @param id
	 * @param cls
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> findAll(final Class<T> cls) {
		final TypedQuery<T> query = this.getManager().createQuery(
				"SELECT u FROM " + cls.getSimpleName() + " u ", cls);
		final ArrayList<T> result = new ArrayList<T>();
		result.addAll(query.getResultList());
		return result;
	}

	/**
	 * TODO: komentarz
	 * 
	 * @date 30-05-2013
	 * 
	 * @param id
	 * @param cls
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public T findById(final Long id, final Class<T> cls) {
		if (id == null) {
			return null;
		}
		return this.getManager().find(cls, id);
	}

	/**
	 * TODO: komentarz
	 * 
	 * @date 30-05-2013
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public void flush(final T entity) {
		if (this.manager == null) {
			throw new IllegalStateException(entity.getClass().getSimpleName()
					+ ": flush: Brak entity managera");
		}
		this.manager.setFlushMode(FlushModeType.COMMIT);
		this.manager.flush();
	}

	public EntityManager getManager() {
		return this.manager;
	}

	/**
	 * Pobieranie pojedynczego wyniku z zapytania
	 * 
	 * @date 31-05-2013
	 * 
	 * @param query
	 * @return
	 */
	@Transactional
	protected T getSingleResult(final TypedQuery<T> query) {
		if (query == null || query.getResultList() == null
				|| query.getResultList().isEmpty()) {
			return null;
		}
		return query.getResultList().get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public T merge(final T entity) {
		if (this.getManager() == null) {
			throw new IllegalStateException(entity.getClass().getSimpleName()
					+ ": merge: Brak entity managera");
		}
		if (this.getManager().contains(this)) {
			return entity;
		}
		return this.manager.merge(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void persist(final T entity) {
		if (this.getManager() == null) {
			throw new IllegalStateException(entity.getClass().getSimpleName()
					+ ": persist: Brak entity managera");
		}
		log.debug(entity.getClass().getSimpleName() + ": persist");
		this.getManager().persist(entity);
	}

	@Override
	@Transactional
	public void remove(final T entity) {
		if (this.manager == null) {
			throw new IllegalStateException(entity.getClass().getSimpleName()
					+ ": remove: Brak entity managera");
		}
		// BaseEntity entity = this.manager.find(entity.getClass(), getId());
		this.manager.remove(entity);
	}

	@PersistenceContext
	public void setManager(final EntityManager manager) {
		if (this.manager == null) {
			this.manager = manager;
		}
	}

	@Override
	public T findById(Long id) {
		return findById(id, cls);
	}

	@Override
	public List<T> findAll() {
		return findAll(cls);
	}
}
