package br.com.vnr.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vnr.entity.Restaurant;
import br.com.vnr.entity.Vote;
import br.com.vnr.exceptions.ServiceException;
import br.com.vnr.repository.AbstractBaseRepository;
import br.com.vnr.repository.VoteRepository;
import br.com.vnr.service.RestaurantService;
import br.com.vnr.service.VoteService;

@Service
public class VoteServiceImpl extends AbstractBaseMappedServiceImpl<Long, Vote>
		implements VoteService {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private VoteRepository voteRepository;

	@Override
	public AbstractBaseRepository<Long, Vote> getRepository() {
		return this.voteRepository;
	}

	@Override
	public Vote preSave(Vote vote) throws ServiceException {
		vote.setDateOfVote(new Date());
		return vote;
	}

	@Override
	public Vote doVote(Restaurant restaurant) throws ServiceException {

		try {

			restaurant = this.restaurantService.findOne(restaurant);

			if (restaurant == null) {
				throw new ServiceException(
						"Não foi possivel computar o voto, o restaurante informado não existe.");
			}

			Vote vote = new Vote();
			vote.setRestaurant(restaurant);

			return this.save(vote);

		} catch (ServiceException exception) {

			throw new ServiceException("Erro ao computar o voto.", exception);

		}

	}

	@Override
	public long getQttOfVoteByRestaurant(Restaurant restaurant)
			throws ServiceException {

		long qtt = 0;

		try {

			List<Vote> result = this.voteRepository
					.findByRestaurant(restaurant);

			if (restaurant != null && !result.isEmpty()) {

				qtt = result.size();

			}

		} catch (Exception e) {

			throw new ServiceException(
					"Erro ao computar a quantidade de votos para o restaurante.");

		}

		return qtt;

	}

	@Override
	public void setVoteRepository(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}

	@Override
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

}
