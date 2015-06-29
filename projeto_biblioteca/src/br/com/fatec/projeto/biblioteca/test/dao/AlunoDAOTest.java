package br.com.fatec.projeto.biblioteca.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.service.AlunoDAO;
import br.com.fatec.projeto.biblioteca.core.impl.AlunoDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigCenarioTestCase;

/**
 * @author Carlos
 *
 * @version
 */
public class AlunoDAOTest extends ConfigCenarioTestCase{

	private AlunoDAO alunoDAO;
	private AlunoDAOImpl alunoDAOImpl;

	@Before
	public void config() {
		this.alunoDAO = new AlunoDAOImpl();
		this.alunoDAOImpl = new AlunoDAOImpl();
	}

	@Test
	public void saveAlunoTest() {
		
		Aluno alunoToSave = this.alunoDAOImpl.findById(1L);
		Aluno savedAluno = this.alunoDAO.save(alunoToSave);

		assertAluno(alunoToSave, savedAluno);
	}

	@Test
	public void findAllTest() {
		
		Aluno alunoToSave0 = this.alunoDAOImpl.findById(0L);
		
		Aluno alunoToSave1 = this.alunoDAOImpl.findById(1L);
	
		Aluno alunoToSave2 = this.alunoDAOImpl.findById(2L);

		Aluno alunoToSave3 = this.alunoDAOImpl.findById(3L);

		List<Aluno> expectedAlunos = new ArrayList<Aluno>();
		expectedAlunos.add(alunoToSave0);
		expectedAlunos.add(alunoToSave1);
		expectedAlunos.add(alunoToSave2);
		expectedAlunos.add(alunoToSave3);

		List<Aluno> encontrados = this.alunoDAO.findAll();

		assertAlunos(expectedAlunos, encontrados);
	}

	@Test
	public void removeAlunoTest() {

		Aluno alunoToSave = this.alunoDAOImpl.findById(1L);
		Aluno savedAluno = this.alunoDAO.save(alunoToSave);

		assertAluno(alunoToSave, savedAluno);
		this.alunoDAO.remove(savedAluno);

		Assert.assertNull(this.alunoDAO.findById(savedAluno.getId()));
	}

	@Test
	public void updateAlunoTest() {

		Aluno alunoToSave = this.alunoDAOImpl.findById(1L);
		Aluno savedAluno = this.alunoDAO.save(alunoToSave);

		assertAluno(alunoToSave, savedAluno);
		savedAluno.setNome("augusto");
		savedAluno.setRa("111010101019020191");

		Aluno updatedAluno = this.alunoDAO.update(savedAluno);

		assertAluno(savedAluno, updatedAluno);
	}

	// auxiliar

	private static void assertAluno(Aluno expected, Aluno actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getRa(), actual.getRa());
	}

	private static void assertAlunos(List<Aluno> expected, List<Aluno> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertAluno(expected.get(i), actual.get(i));
		}
	}

}
