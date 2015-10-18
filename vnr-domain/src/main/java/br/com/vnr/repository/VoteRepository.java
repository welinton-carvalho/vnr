package br.com.vnr.repository;

import java.util.List;

import br.com.vnr.entity.Restaurant;
import br.com.vnr.entity.Vote;

public interface VoteRepository extends AbstractBaseRepository<Long, Vote> {

	List<Vote> findByRestaurant(Restaurant restaurant);

}
