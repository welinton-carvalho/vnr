package br.com.vnr.service;

import br.com.vnr.entity.Voter;

public interface VoterService extends AbstractBaseMappedService<Long, Voter> {

	Voter findByEmail(String email);

}
