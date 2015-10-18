package br.com.vnr.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_VOTER_X_VOTE", joinColumns = { @JoinColumn(name = "ID_VOTER", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "ID_VOTE", referencedColumnName = "ID") })
	private List<Vote> votes;

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

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public void addVote(Vote vote) {
		if (this.votes == null) {
			this.votes = new ArrayList<Vote>();
		}
		this.votes.add(vote);
	}

}
