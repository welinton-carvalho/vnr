package br.com.vnr.service;

import br.com.vnr.entity.Voter;
import br.com.vnr.repository.VoterRepository;

public interface VoterService extends AbstractBaseMappedService<Long, Voter> {

	Voter findByEmail(String email);

	void setVoterRepository(VoterRepository voterRepository);

}
