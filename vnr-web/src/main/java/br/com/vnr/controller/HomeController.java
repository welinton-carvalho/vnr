package br.com.vnr.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vnr.controller.constants.PagesToNavigate;
import br.com.vnr.controller.request.mapper.VoterRequestMapper;
import br.com.vnr.dto.RankingDTO;
import br.com.vnr.entity.Restaurant;
import br.com.vnr.entity.Vote;
import br.com.vnr.entity.Voter;
import br.com.vnr.exceptions.ServiceException;
import br.com.vnr.service.impl.RankingServiceImpl;
import br.com.vnr.service.impl.RestaurantServiceImpl;
import br.com.vnr.service.impl.VoteServiceImpl;
import br.com.vnr.service.impl.VoterServiceImpl;
import br.com.vnr.util.LoadDataUtil;

public class HomeController extends AbstractBaseController {

	private static final long serialVersionUID = 7621434824029951754L;

	private static final String VOTING_JSP = "/voting/voting.jsp";

	private static final String VOTER_JSP = "/voting/voter.jsp";

	private static final String RANKING_JSP = "/voting/ranking.jsp";

	private VoteServiceImpl voteService;

	private VoterServiceImpl voterService;

	private RestaurantServiceImpl restaurantService;

	private RankingServiceImpl rankingService;

	private LoadDataUtil loadDataUtil;

	@Override
	public void init() throws ServletException {

		try {
			super.init();

			this.voteService = (VoteServiceImpl) this.loadBean("voteServiceImpl");
			if (this.voteService == null) {
				throw new ServiceException("Ocorreu um erro ao injetar o serviço # VoteService #.");
			}

			this.voterService = (VoterServiceImpl) this.loadBean("voterServiceImpl");
			if (this.voterService == null) {
				throw new ServiceException("Ocorreu um erro ao injetar o serviço # VoterService #.");
			}

			this.restaurantService = (RestaurantServiceImpl) this.loadBean("restaurantServiceImpl");
			if (this.restaurantService == null) {
				throw new ServiceException(
						"Ocorreu um erro ao injetar o serviço # RestaurantService #.");
			}

			this.rankingService = (RankingServiceImpl) this.loadBean("rankingServiceImpl");
			if (this.rankingService == null) {
				throw new ServiceException(
						"Ocorreu um erro ao injetar o serviço # RankingService #.");
			}

			this.loadDataUtil = (LoadDataUtil) this.loadBean("loadDataUtil");
			if (this.loadDataUtil == null) {
				throw new ServiceException(
						"Ocorreu um erro ao injetar o utilitário # LoadDataUtil #.");
			}

		} catch (Exception exception) {
			LOGGER.error(exception);
			throw new ServletException(exception);
		}

	}

	@Override
	public void initializeScreen(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			if (!this.verifyVotedCokie(request, response)) {

				this.loadDataUtil.loadRestaurantsData();

				this.drawRestaurants(request);

				this.forwardToPage(VOTING_JSP, request, response);

			} else {

				this.forwardToMessagePage("Você já votou nessa enquete!", request, response);

			}

		} catch (Exception e) {
			this.forwardToErrorPage(e, request, response);
			LOGGER.error(e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String executeAction = (String) request.getParameter("executeAction");

			if (executeAction == null | executeAction == "") {

				this.initializeScreen(request, response);

			} else {

				switch (executeAction) {

				case PagesToNavigate.JSP_DO_VOTE:

					this.doVote(request, response);

					break;

				case PagesToNavigate.JSP_DO_SAVE_VOTER:

					this.doSaveVoter(request, response);

					break;

				case PagesToNavigate.JSP_SHOW_RANKING:

					this.showRanking(request, response);

					break;

				default:
					response.sendError(404,
							"Opção de navegação não encontrada: " + String.valueOf(executeAction));
					break;
				}

			}

		} catch (Exception exception) {
			this.forwardToErrorPage(exception, request, response);
			LOGGER.error("Ocorreu um erro", exception);
		}

	}

	private void showRanking(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			List<RankingDTO> result = this.rankingService.getRankingList();

			Collections.sort(result);

			request.setAttribute("ranking", result);

			this.forwardToPage(RANKING_JSP, request, response);

		} catch (ServiceException e) {
			this.forwardToErrorPage(e, request, response);
			LOGGER.error("Ocorreu um erro", e);
		}

	}

	private void doVote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			Long idRestaurantFirstVote = Long.valueOf((String) this.getParameter(request,
					"idRestaurantFirstVote"));

			Long idRestaurantSecondVote = Long.valueOf((String) this.getParameter(request,
					"idRestaurantSecondVote"));

			Restaurant firstRestaurant = new Restaurant();
			firstRestaurant.setId(idRestaurantFirstVote);

			Vote firstVote = this.voteService.doVote(firstRestaurant);

			request.getSession().setAttribute("firstVote", firstVote);

			Restaurant secondRestaurant = new Restaurant();
			secondRestaurant.setId(idRestaurantSecondVote);

			Vote secondVote = this.voteService.doVote(secondRestaurant);

			request.getSession().setAttribute("secondVote", secondVote);

			this.saveCokie(request, response);

			this.forwardToPage(VOTER_JSP, request, response);

		} catch (ServiceException e) {
			this.forwardToErrorPage(e, request, response);
			LOGGER.error("Ocorreu um erro", e);
		}

	}

	private void doSaveVoter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			Voter voter = new VoterRequestMapper().getVoter(request);
						
			voter.addVote((Vote) this.getSessionAttribute(request, "firstVote"));
			
			voter.addVote((Vote) this.getSessionAttribute(request, "secondVote"));

			this.voterService.save(voter);

			this.showRanking(request, response);

		} catch (ServiceException e) {
			this.forwardToErrorPage(e, request, response);
			LOGGER.error("Ocorreu um erro", e);
		}

	}

	private void drawRestaurants(HttpServletRequest request) throws ServiceException {

		List<Restaurant> restaurants = null;

		try {

			restaurants = this.restaurantService.all();

			if (restaurants != null && restaurants.isEmpty() && restaurants.size() >= 5) {

			}
			// Embaralha os restaurantes.
			Collections.shuffle(restaurants);

			request.setAttribute("firstTwoRestaurants", restaurants.subList(0, 2));

			request.setAttribute("anothersRestaurants", restaurants.subList(2, 5));

		} catch (Exception exception) {

			throw new ServiceException(
					"Ocorreu um erro ao montar a lista de restaurantes para votação.", exception);

		}

	}

	private void saveCokie(HttpServletRequest request, HttpServletResponse response) {

		Cookie haveVoted = new Cookie("haveVoted", "yes");
		haveVoted.setMaxAge(99999999);
		haveVoted.setComment("Marcacao de voto VNR WEB");

		response.addCookie(haveVoted);

	}

	private boolean verifyVotedCokie(HttpServletRequest request, HttpServletResponse response) {

		boolean exists = false;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {

			for (Cookie cookie : cookies) {

				if (cookie.getName().equalsIgnoreCase("haveVoted")) {
					exists = true;
				}

			}

		}

		return exists;

	}

}
