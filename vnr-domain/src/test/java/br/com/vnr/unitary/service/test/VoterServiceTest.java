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

import br.com.vnr.entity.Voter;
import br.com.vnr.exceptions.ServiceException;
import br.com.vnr.repository.VoterRepository;
import br.com.vnr.service.VoterService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context-test.xml" })
public class VoterServiceTest {

	@Autowired
	private VoterService voterService;

	@Mock
	private VoterRepository voterRepository;

	public VoterServiceTest() {

	}

	@Before
	public void onInit() {

		Voter voter = new Voter();
		voter.setId(1L);
		voter.setName("Teste");
		voter.setEmail("teste@teste.com.br");

		this.voterRepository = EasyMock.createMock(VoterRepository.class);

		EasyMock.expect(this.voterRepository.findByEmail("teste@teste.com.br"))
				.andReturn(voter).anyTimes();
		
		EasyMock.replay(this.voterRepository);

		this.voterService.setVoterRepository(this.voterRepository);

	}

	@Test
	public void preSaveValidationTest() {

		try {

			Voter voter = new Voter();
			voter.setId(2L);
			voter.setName("Teste 2");
			voter.setEmail("teste@teste.com.br");

			this.voterService.save(voter);

		} catch (ServiceException exception) {

			assertEquals(exception.getMessage().toString(),
					"Você já votou nessa enquete.");

		}

	}

}
