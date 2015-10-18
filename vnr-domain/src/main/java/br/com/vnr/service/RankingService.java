package br.com.vnr.service;

import java.util.List;

import br.com.vnr.dto.RankingDTO;
import br.com.vnr.exceptions.ServiceException;

public interface RankingService {

	List<RankingDTO> getRankingList() throws ServiceException;

}
