package br.com.vnr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

import br.com.vnr.dto.RankingDTO;
import br.com.vnr.entity.Restaurant;
import br.com.vnr.exceptions.ServiceException;
import br.com.vnr.service.RankingService;
import br.com.vnr.service.RestaurantService;
import br.com.vnr.service.VoteService;

@Service
public class RankingServiceImpl implements RankingService {

	@Autowired
	private VoteService voteService;

	@Autowired
	private RestaurantService restaurantService;

	public RankingServiceImpl() {

	}

	public List<RankingDTO> getRankingList() throws ServiceException {

		List<RankingDTO> result = null;

		try {

			List<Restaurant> restaurants = this.restaurantService.all();

			long qttTotalOfVotes = this.voteService.all().size();

			if (restaurants != null && !restaurants.isEmpty()) {

				result = new ArrayList<RankingDTO>();

				for (Restaurant restaurant : restaurants) {

					long qttOfVotes = this.voteService
							.getQttOfVoteByRestaurant(restaurant);

					result.add(new RankingDTO(restaurant, qttOfVotes,
							qttTotalOfVotes));

				}

			} else {

				throw new ServiceException(
						"Não existe restaurantes cadastrados.");

			}

		} catch (ServiceException | InvalidDataAccessResourceUsageException e) {

			throw new ServiceException(
					"Ocorreu um erro ao computar a lista de votos.");

		}

		return result;

	}

}
