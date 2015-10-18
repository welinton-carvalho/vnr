package br.com.vnr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representation of the elector.
 **/
@Entity
@SequenceGenerator(name = "SEQUENCE_NAME", sequenceName = "S_VOTER")
@Table(name = "TB_VOTER")
public class Voter extends AbstractBaseMappedEntity {

	private static final long serialVersionUID = 1931706218033287509L;

	@Column(name = "DS_NAME", nullable = false)
	private String name;

	@Column(name = "DS_EMAIL", unique = true, nullable = false)
	private String email;

	public Voter() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
