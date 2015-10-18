package br.com.vnr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.vnr.entity.AbstractBaseMappedEntity;

@NoRepositoryBean
public interface AbstractBaseRepository<PK, T extends AbstractBaseMappedEntity>
		extends CrudRepository<T, Long> {

	@Query("from #{#entityName} t")
	public List<T> all();

	@Query("from #{#entityName} t where t.activeRecord = 'ACTIVE'")
	public List<T> allActive();

}