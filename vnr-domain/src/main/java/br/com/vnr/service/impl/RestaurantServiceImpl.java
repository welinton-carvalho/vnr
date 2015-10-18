package br.com.vnr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vnr.entity.Restaurant;
import br.com.vnr.repository.AbstractBaseRepository;
import br.com.vnr.repository.RestaurantRepository;
import br.com.vnr.service.RestaurantService;

@Service
public class RestaurantServiceImpl extends
		AbstractBaseMappedServiceImpl<Long, Restaurant> implements
		RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public AbstractBaseRepository<Long, Restaurant> getRepository() {
		return this.restaurantRepository;
	}

}
