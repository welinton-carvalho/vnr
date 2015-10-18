package br.com.vnr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "SEQUENCE_NAME", sequenceName = "S_VOTE")
@Table(name = "TB_VOTE")
public class Vote extends AbstractBaseMappedEntity {

	private static final long serialVersionUID = 4005075443076692720L;

	@Column(name = "DT_VOTE", nullable = false)
	private Date dateOfVote;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_RESTAURANT", referencedColumnName = "ID")
	private Restaurant restaurant;

	public Vote() {

	}

	public Date getDateOfVote() {
		return dateOfVote;
	}

	public void setDateOfVote(Date dateOfVote) {
		this.dateOfVote = dateOfVote;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
