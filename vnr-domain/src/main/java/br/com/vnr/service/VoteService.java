package br.com.vnr.service;

import br.com.vnr.entity.Restaurant;
import br.com.vnr.entity.Vote;
import br.com.vnr.exceptions.ServiceException;

public interface VoteService extends AbstractBaseMappedService<Long, Vote> {

	Vote doVote(Restaurant restaurant) throws ServiceException;

	long getQttOfVoteByRestaurant(Restaurant restaurant)
			throws ServiceException;
	
}
