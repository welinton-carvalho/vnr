package br.com.vnr.service;

import java.util.List;

import br.com.vnr.entity.AbstractBaseMappedEntity;
import br.com.vnr.exceptions.ServiceException;
import br.com.vnr.repository.AbstractBaseRepository;

public interface AbstractBaseMappedService<PK, T extends AbstractBaseMappedEntity> {

	public abstract AbstractBaseRepository<PK, T> getRepository();

	public abstract T preSave(T t) throws ServiceException;

	public abstract T save(T t) throws ServiceException;

	public abstract T postSave(T t) throws ServiceException;

	public abstract T preDelete(T t) throws ServiceException;

	public abstract void delete(T t) throws ServiceException;

	public abstract T postDelete(T t) throws ServiceException;

	public abstract T preDisable(T t) throws ServiceException;

	public abstract void disable(T t) throws ServiceException;

	public abstract T postDisable(T t) throws ServiceException;

	public abstract T preEnable(T t) throws ServiceException;

	public abstract void enable(T t) throws ServiceException;

	public abstract T postEnable(T t) throws ServiceException;

	public abstract T preFindOne(T t) throws ServiceException;

	public abstract T findOne(T t) throws ServiceException;

	public abstract T postFindOne(T t) throws ServiceException;

	public abstract List<T> all() throws ServiceException;

	public abstract List<T> postAll(List<T> t) throws ServiceException;

	public abstract List<T> allActive() throws ServiceException;

	public abstract List<T> postAllActive(List<T> t) throws ServiceException;

}
