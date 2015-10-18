package br.com.vnr.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vnr.entity.Restaurant;
import br.com.vnr.service.RestaurantService;

@Component
public class LoadDataUtil {

	@Autowired
	private RestaurantService restaurantService;

	public LoadDataUtil() {

	}

	public boolean loadRestaurantsData() {

		boolean success = false;
		
		try {

			List<Restaurant> restaurants = new ArrayList<Restaurant>();
			restaurants.add(new Restaurant(1L, "Bobs", "bobs.jpg"));
			restaurants.add(new Restaurant(2L, "Subway", "subway.jpg"));
			restaurants.add(new Restaurant(3L, "Burger King", "burger-king.jpg"));
			restaurants.add(new Restaurant(4L, "McDonald's", "mcdonalds.jpg"));
			restaurants.add(new Restaurant(5L, "Giraffas", "giraffas.jpg"));

			for (Restaurant restaurant : restaurants) {
				this.restaurantService.save(restaurant);
			}
			
			success = true;

		} catch (Exception exception){
			// ignorar, vai dar erro se ja estiver em banco.
		}

		return success;
		
	}
}
