package br.com.vnr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.vnr.enums.GenericStatus;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

@MappedSuperclass
public class AbstractBaseMappedEntity implements Serializable {

	private static final long serialVersionUID = 4710728940844016116L;

	@Id
	@Column(name = "ID", nullable = false, precision = 10, scale = 0, unique = true)
	@GeneratedValue(generator = "SEQUENCE_NAME")
	protected Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "ACTIVE_RECORD", length = 10, nullable = false)
	protected GenericStatus activeRecord;

	public AbstractBaseMappedEntity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GenericStatus getActiveRecord() {
		return activeRecord;
	}

	public void setActiveRecord(GenericStatus activeRecord) {
		this.activeRecord = activeRecord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activeRecord == null) ? 0 : activeRecord.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractBaseMappedEntity other = (AbstractBaseMappedEntity) obj;
		if (activeRecord != other.activeRecord)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		try {
			return new Gson().toJson(this);
		} catch (StackOverflowError | Exception e) {
			throw new JsonParseException(
					"Ocorreu um erro ao gerar o Json da entidade: "
							+ getClass().getName());
		}
	}

}
