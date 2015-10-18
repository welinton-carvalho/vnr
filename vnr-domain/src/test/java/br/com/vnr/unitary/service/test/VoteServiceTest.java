package br.com.vnr.unitary.service.test;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.vnr.entity.Restaurant;
import br.com.vnr.exceptions.ServiceException;
import br.com.vnr.service.RestaurantService;
import br.com.vnr.service.VoteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context-test.xml" })
public class VoteServiceTest {

	@Autowired
	private VoteService voteService;

	@Mock
	private RestaurantService restaurantService;

	public VoteServiceTest() {

	}

	@Before
	public void onInit() throws ServiceException {

		this.restaurantService = EasyMock.createMock(RestaurantService.class);

		Restaurant restaurant2 = new Restaurant();
		restaurant2.setId(2L);

		EasyMock.expect(this.restaurantService.findOne(restaurant2))
				.andReturn(null).anyTimes();

		EasyMock.replay(this.restaurantService);

		this.voteService.setRestaurantService(this.restaurantService);

	}

	@Test
	public void doVoteTest() throws ServiceException {

		try {

			Restaurant restaurant2 = new Restaurant();
			restaurant2.setId(2L);

			this.voteService.doVote(restaurant2);

		} catch (ServiceException exception) {

			assertEquals(exception.getCause().getMessage().toString(),
					"Não foi possivel computar o voto, o restaurante informado não existe.");
			
		}

	}

}
