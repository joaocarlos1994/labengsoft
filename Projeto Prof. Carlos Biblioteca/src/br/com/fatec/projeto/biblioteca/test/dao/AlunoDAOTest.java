package br.com.fatec.projeto.biblioteca.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.service.AlunoDAO;
import br.com.fatec.projeto.biblioteca.core.helper.AlunoFactory;
import br.com.fatec.projeto.biblioteca.core.impl.AlunoDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;
import br.com.fatec.projeto.biblioteca.test.commons.CustomAsserts;

/**
 * @author Carlos
 *
 * @version
 */
public class AlunoDAOTest extends ConfigDBTestCase {

	private AlunoDAO alunoDAO;
	private AlunoFactory alunoFactory;

	@Before
	public void config() {
		this.alunoDAO = new AlunoDAOImpl();
		this.alunoFactory = new AlunoFactory();
	}

	@Test
	public void saveAlunoTest() {
		
		Aluno alunoToSave = this.alunoFactory.createAluno(null, "carlos", "00000010", "1000000");
		Aluno savedAluno = this.alunoDAO.save(alunoToSave);

		assertAluno(alunoToSave, savedAluno);
	}

	@Test
	public void findAllTest() {
		
		Aluno alunoToSave1 = this.alunoFactory.createAluno(null, "carlos1", "00000011", "11000000");
	
		Aluno alunoToSave2 = this.alunoFactory.createAluno(null, "carlos2", "00000012", "12000000");

		Aluno alunoToSave3 = this.alunoFactory.createAluno(null, "carlos3", "00000013", "13000000");

		List<Aluno> expectedAlunos = new ArrayList<Aluno>();
		expectedAlunos.add(this.alunoDAO.save(alunoToSave1));
		expectedAlunos.add(this.alunoDAO.save(alunoToSave2));
		expectedAlunos.add(this.alunoDAO.save(alunoToSave3));

		List<Aluno> encontrados = this.alunoDAO.findAll();

		assertAlunos(expectedAlunos, encontrados);
	}

	@Test
	public void removeAlunoTest() {

		Aluno alunoToSave = this.alunoFactory.createAluno(null, "carlos", "00000010","00000010");
		Aluno savedAluno = this.alunoDAO.save(alunoToSave);

		assertAluno(alunoToSave, savedAluno);
		this.alunoDAO.remove(savedAluno);

		Assert.assertNull(this.alunoDAO.findById(savedAluno.getId()));
	}

	@Test
	public void updateAlunoTest() {

		Aluno alunoToSave = this.alunoFactory.createAluno(null, "carlos", "00000010", "00000010");
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
