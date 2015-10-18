//package br.com.vnr.integrated.service.test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import java.util.Date;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import br.com.vnr.entity.Restaurant;
//import br.com.vnr.entity.Vote;
//import br.com.vnr.enums.GenericStatus;
//import br.com.vnr.exceptions.ServiceException;
//import br.com.vnr.service.RestaurantService;
//import br.com.vnr.service.VoteService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:application-context-test.xml" })
//public class VoteTest {
//
//	@Autowired
//	private VoteService voteService;
//
//	@Autowired
//	private RestaurantService restaurantService;
//
//	private static final Long ID_VOTE_1 = Long.valueOf(-1001001);
//
//	@Before
//	public void onInit() throws ServiceException {
//
//		Restaurant restaurant = new Restaurant("MAC Donalds");
//		this.restaurantService.save(restaurant);
//
//		// save test.
//		Vote vote = new Vote();
//		vote.setId(ID_VOTE_1);
//		vote.setActiveRecord(GenericStatus.ACTIVE);
//		vote.setDateOfVote(new Date());
//		vote.setRestaurant(restaurant);
//		this.voteService.save(vote);
//
//	}
//
//	@After
//	public void onExit() throws ServiceException {
//
//		Vote vote = new Vote();
//		vote.setId(ID_VOTE_1);
//		// find test.
//		vote = this.voteService.findOne(vote);
//
//		// delte test.
//		this.voteService.delete(vote);
//		this.restaurantService.delete(vote.getRestaurant());
//
//	}
//
//	@Test
//	public void findTest() throws ServiceException {
//
//		Vote vote = new Vote();
//		vote.setId(ID_VOTE_1);
//		vote = this.voteService.findOne(vote);
//
//		assertNotNull(vote.getId());
//		assertNotNull(vote.getDateOfVote());
//		assertEquals(vote.getRestaurant().getName(), "MAC Donalds");
//
//	}
//
//	@Test
//	public void findAllTest() throws ServiceException {
//		// assertTrue(this.pstSystemUserService.all().size() > 0);
//	}
//
//	@Test
//	public void findAllActiveTest() throws ServiceException {
//		// assertTrue(this.pstSystemUserService.allActive().size() > 0);
//	}
//
//	@Test
//	public void saveTest() throws ServiceException {
//
//		// PstSystemUser pstSystemUser = new PstSystemUser();
//		//
//		// pstSystemUser.setName("setName");
//		// pstSystemUser.setSurname("setSurname");
//		// pstSystemUser.setLogin("setLogin");
//		// pstSystemUser.setHintForPassword("setHintForPassword");
//		// pstSystemUser.setPrivateEmail("setPrivateEmail");
//		// pstSystemUser.setCommercialEmail("setCommercialEmail");
//		// pstSystemUser.setLocale("setLocale");
//		// pstSystemUser.setSystemAccessRole(PstSystemAccessRole.ROLE_USER);
//		// pstSystemUser.setActiveRecord(PstGenericStatus.ACTIVE);
//		//
//		// this.pstSystemUserService.save(pstSystemUser);
//
//	}
//
//	@Test
//	public void disableTest() throws ServiceException {
//
//		// PstSystemUser pstSystemUser = new PstSystemUser();
//		// pstSystemUser.setId(ID_USER_DB_UNIT_1);
//		//
//		// pstSystemUser = this.pstSystemUserService.findOne(pstSystemUser);
//		//
//		// this.pstSystemUserService.disable(pstSystemUser);
//		//
//		// assertTrue(pstSystemUser.getActiveRecord().equals(PstGenericStatus.INACTIVE));
//
//	}
//
//	@Test
//	public void enableTest() throws ServiceException {
//
//		// PstSystemUser pstSystemUser = new PstSystemUser();
//		// pstSystemUser.setId(ID_USER_DB_UNIT_1);
//		//
//		// pstSystemUser = this.pstSystemUserService.findOne(pstSystemUser);
//		//
//		// this.pstSystemUserService.enable(pstSystemUser);
//		//
//		// assertTrue(pstSystemUser.getActiveRecord().equals(PstGenericStatus.ACTIVE));
//
//	}
//
//	@Test
//	public void removeTest() throws ServiceException {
//
//		// PstSystemUser pstSystemUser = new PstSystemUser();
//		// pstSystemUser.setId(ID_USER_DB_UNIT_1);
//		//
//		// pstSystemUser = this.pstSystemUserService.findOne(pstSystemUser);
//		//
//		// assertEquals(pstSystemUser.getLogin(), "userLoginTest1");
//		//
//		// this.pstSystemUserService.delete(pstSystemUser);
//		//
//		// pstSystemUser = new PstSystemUser();
//		// pstSystemUser.setId(ID_USER_DB_UNIT_1);
//		// pstSystemUser = this.pstSystemUserService.findOne(pstSystemUser);
//		//
//		// assertTrue(PstObjectUtil.isNull(pstSystemUser));
//
//	}
//
//}
