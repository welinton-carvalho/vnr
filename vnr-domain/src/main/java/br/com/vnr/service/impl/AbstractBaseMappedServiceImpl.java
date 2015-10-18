package br.com.vnr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.vnr.entity.AbstractBaseMappedEntity;
import br.com.vnr.enums.GenericStatus;
import br.com.vnr.exceptions.ServiceException;
import br.com.vnr.repository.AbstractBaseRepository;
import br.com.vnr.service.AbstractBaseMappedService;

public abstract class AbstractBaseMappedServiceImpl<PK, T extends AbstractBaseMappedEntity>
		implements AbstractBaseMappedService<PK, T> {

	@Override
	public abstract AbstractBaseRepository<PK, T> getRepository();

	@Override
	public T preSave(T t) throws ServiceException {
		return t;
	}

	@Override
	public T save(T t) throws ServiceException {
		if (!repositoryIsNull() && !objectIsNull(t)) {
			this.preSave(t);
			if (t.getId() == null) {
				t.setActiveRecord(GenericStatus.ACTIVE);
			}
			t = this.getRepository().save(t);
			this.postSave(t);
			return t;
		}
		throw new ServiceException("Ocorreu um erro ao salvar a entidade.");
	}

	@Override
	public T postSave(T t) throws ServiceException {
		return t;
	}

	@Override
	public T preDelete(T t) throws ServiceException {
		return t;
	}

	@Override
	public void delete(T t) throws ServiceException {
		try {
			if (!repositoryIsNull() && !objectIsNull(t)) {
				this.preDelete(t);
				this.getRepository().delete(t);
				this.postDelete(t);
			}
		} catch (Exception e) {
			throw new ServiceException("Ocorreu um erro ao excluir a entidade");
		}
	}

	@Override
	public T postDelete(T t) throws ServiceException {
		return t;
	}

	@Override
	public T preDisable(T t) throws ServiceException {
		return t;
	}

	@Override
	public void disable(T t) throws ServiceException {
		this.preDisable(t);
		this.updateStatus(GenericStatus.INACTIVE, t);
		this.postDisable(t);
	}

	@Override
	public T postDisable(T t) throws ServiceException {
		return t;
	}

	@Override
	public T preEnable(T t) throws ServiceException {
		return t;
	}

	@Override
	public void enable(T t) throws ServiceException {
		this.preEnable(t);
		this.updateStatus(GenericStatus.ACTIVE, t);
		this.postEnable(t);
	}

	@Override
	public T postEnable(T t) throws ServiceException {
		return t;
	}

	@Override
	public T preFindOne(T t) throws ServiceException {
		return t;
	}

	@Override
	@Transactional
	public T findOne(T t) throws ServiceException {
		T result = null;
		if (!repositoryIsNull()) {
			this.preFindOne(t);
			result = this.getRepository().findOne(t.getId());
			this.postFindOne(result);
		}
		return result;
	}

	@Override
	public T postFindOne(T t) throws ServiceException {
		return t;
	}

	@Override
	@Transactional
	public List<T> all() throws ServiceException {
		List<T> result = new ArrayList<T>();
		if (!repositoryIsNull()) {
			result = this.getRepository().all();
			this.postAll(result);
		}
		return result;
	}

	@Override
	public List<T> postAll(List<T> t) throws ServiceException {
		return t;
	}

	@Override
	@Transactional
	public List<T> allActive() throws ServiceException {
		List<T> result = new ArrayList<T>();
		if (!repositoryIsNull()) {
			result = this.getRepository().allActive();
			this.postAllActive(result);
		}
		return result;
	}

	@Override
	public List<T> postAllActive(List<T> t) throws ServiceException {
		return t;
	}

	@Transactional
	private T updateStatus(GenericStatus genericStatus, T t)
			throws ServiceException {
		if (!repositoryIsNull() && !objectIsNull(t)) {
			t.setActiveRecord(genericStatus);
			return this.getRepository().save(t);
		}
		throw new ServiceException(
				"Ocorreu um erro ao atualizar o estatus da entidade.");
	}

	private boolean repositoryIsNull() throws ServiceException {
		if (this.getRepository() == null) {
			throw new ServiceException(
					"O repositório não foi injetado corretamente.");
		}
		return false;
	}

	private boolean objectIsNull(T t) throws ServiceException {
		if (t == null) {
			throw new ServiceException(
					"A entidade informada não pode ser nula.");
		}
		return false;
	}

}
