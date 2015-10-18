package br.com.vnr.repository;

import br.com.vnr.entity.Voter;

public interface VoterRepository extends AbstractBaseRepository<Long, Voter> {

	Voter findByEmail(String email);

}
