package br.com.vnr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.vnr.enums.GenericStatus;

@Entity
@SequenceGenerator(name = "SEQUENCE_NAME", sequenceName = "S_RESTAURANT")
@Table(name = "TB_RESTAURANT")
public class Restaurant extends AbstractBaseMappedEntity {

	private static final long serialVersionUID = 4977476863090226017L;

	@Column(name = "DS_NAME", unique = true, nullable = false)
	private String name;

	@Column(name = "DS_LOGO", nullable = true)
	private String logo;

	public Restaurant() {
		super();
	}

	public Restaurant(String name) {
		super();
		this.name = name;
		this.logo = "sem-logo.png";
		this.activeRecord = GenericStatus.ACTIVE;
	}

	public Restaurant(Long id, String name, String logo) {
		this.id = id;
		this.name = name;
		this.logo = logo;
		this.activeRecord = GenericStatus.ACTIVE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
