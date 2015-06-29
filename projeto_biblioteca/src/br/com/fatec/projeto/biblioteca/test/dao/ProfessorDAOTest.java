package br.com.fatec.projeto.biblioteca.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.entity.Professor;
import br.com.fatec.projeto.biblioteca.api.service.ProfessorDAO;
import br.com.fatec.projeto.biblioteca.core.helper.AlunoFactory;
import br.com.fatec.projeto.biblioteca.core.helper.ProfessorFactory;
import br.com.fatec.projeto.biblioteca.core.impl.AlunoDAOImpl;
import br.com.fatec.projeto.biblioteca.core.impl.ProfessorDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigCenarioTestCase;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;
import br.com.fatec.projeto.biblioteca.test.commons.CustomAsserts;

public class ProfessorDAOTest extends ConfigCenarioTestCase {

	private ProfessorDAOImpl professorDAOImpl;
	private ProfessorDAO professorDAO;

	@Before
	public void config() {
		this.professorDAO = new ProfessorDAOImpl();
		this.professorDAOImpl = new ProfessorDAOImpl();
	}

	@Test
	public void savedProfessorTest() {

		Professor professorToSave = this.professorDAOImpl.findById(1L);
		Professor savedProfessor = professorDAO.save(professorToSave);

		assertProfessor(professorToSave, savedProfessor);
	}

	@Test
	public void findAllTest() {
		
		Professor professorToSave0 = this.professorDAOImpl.findById(0L);
		
		Professor professorToSave1 = this.professorDAOImpl.findById(1L);

		Professor professorToSave2 = this.professorDAOImpl.findById(2L);

		Professor professorToSave3 = this.professorDAOImpl.findById(3L);

		List<Professor> expectedAlunos = new ArrayList<Professor>();
		expectedAlunos.add(professorToSave0);
		expectedAlunos.add(professorToSave1);
		expectedAlunos.add(professorToSave2);
		expectedAlunos.add(professorToSave3);

		List<Professor> encontrados = this.professorDAO.findAll();

		assertProfessor(expectedAlunos, encontrados);
	}

	@Test
	public void removeProfessorTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Professor alunoToSave = this.professorDAOImpl.findById(1L);
		Professor savedAluno = this.professorDAO.save(alunoToSave);

		assertProfessor(alunoToSave, savedAluno);
		this.professorDAO.remove(savedAluno);

		Assert.assertNull(this.professorDAO.findById(savedAluno.getId()));
	}

	@Test
	public void updateProfessorTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Professor professorToSave = this.professorDAOImpl.findById(1L);
		Professor savedProfessor = this.professorDAO.save(professorToSave);

		assertProfessor(professorToSave, savedProfessor);
		savedProfessor.setNome("augusto");
		savedProfessor.setRegistro("111010101019020191");

		Professor updatedAluno = this.professorDAO.update(savedProfessor);

		assertProfessor(savedProfessor, updatedAluno);
	}

	// auxiliar

	private static void assertProfessor(Professor expected, Professor actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getRegistro(), actual.getRegistro());
	}

	private static void assertProfessor(List<Professor> expected,
			List<Professor> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertProfessor(expected.get(i), actual.get(i));
		}
	}

}
