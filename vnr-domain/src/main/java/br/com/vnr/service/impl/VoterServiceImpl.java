package br.com.vnr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vnr.entity.Voter;
import br.com.vnr.exceptions.ServiceException;
import br.com.vnr.repository.AbstractBaseRepository;
import br.com.vnr.repository.VoterRepository;
import br.com.vnr.service.VoterService;

@Service
public class VoterServiceImpl extends
		AbstractBaseMappedServiceImpl<Long, Voter> implements VoterService {

	@Autowired
	private VoterRepository voterRepository;

	@Override
	public AbstractBaseRepository<Long, Voter> getRepository() {
		return this.voterRepository;
	}

	@Override
	public Voter findByEmail(String email) {
		return this.voterRepository.findByEmail(email);
	}

	@Override
	public Voter preSave(Voter voter) throws ServiceException {

		Voter findedVoter = this.findByEmail(voter.getEmail());

		if (findedVoter != null
				&& (findedVoter.getId() != null
						&& findedVoter.getName() != null && findedVoter
						.getEmail() != null)) {

			throw new ServiceException("Você já votou nessa enquete.");

		}

		return voter;

	}

}
