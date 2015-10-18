package br.com.vnr.controller.request.mapper;

import javax.servlet.http.HttpServletRequest;

import br.com.vnr.entity.Voter;

public class VoterRequestMapper {

	public VoterRequestMapper() {

	}

	public Voter getVoter(HttpServletRequest request) {

		String name = request.getParameter("voterName");

		String email = request.getParameter("voterEmail");

		Voter voter = new Voter();
		voter.setName(name);
		voter.setEmail(email);

		return voter;
		
	}

}
