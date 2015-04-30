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
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;
import br.com.fatec.projeto.biblioteca.test.commons.CustomAsserts;

public class ProfessorDAOTest extends ConfigDBTestCase{
	
	private ProfessorFactory professorFactory;
	private ProfessorDAO professorDAO;
	
	@Before
	public void config() {
		this.professorDAO = new ProfessorDAOImpl();
		this.professorFactory = new ProfessorFactory();
	}
	
	@Test
	public void savedProfessorTest(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);
		
		Professor professorToSave = professorFactory.createProfessor(null, "Carlos Lab. Eng. Soft.", "9999999", "5555555");
		Professor savedProfessor = professorDAO.save(professorToSave);
		
		assertProfessor(professorToSave, savedProfessor);
	}
	
	@Test
	public void findAllTest() {
		Calendar calendar = Calendar.getInstance();

		calendar.set(1991, 5, 2);
		Professor alunoToSave1 = this.professorFactory.createProfessor(null, "carlos1", "00000011", "11000000");
		calendar.set(1991, 5, 3);
		Professor alunoToSave2 = this.professorFactory.createProfessor(null, "carlos2", "00000012", "12000000");
		calendar.set(1991, 5, 4);
		Professor alunoToSave3 = this.professorFactory.createProfessor(null, "carlos3", "00000013", "13000000");

		List<Professor> expectedAlunos = new ArrayList<Professor>();
		expectedAlunos.add(this.professorDAO.save(alunoToSave1));
		expectedAlunos.add(this.professorDAO.save(alunoToSave2));
		expectedAlunos.add(this.professorDAO.save(alunoToSave3));

		List<Professor> encontrados = this.professorDAO.findAll();

		assertProfessor(expectedAlunos, encontrados);
	}
	
	@Test
	public void removeProfessorTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Professor alunoToSave = this.professorFactory.createProfessor(null, "carlos", "00000010","00000010");
		Professor savedAluno = this.professorDAO.save(alunoToSave);

		assertProfessor(alunoToSave, savedAluno);
		this.professorDAO.remove(savedAluno);

		Assert.assertNull(this.professorDAO.findById(savedAluno.getId()));
	}
	
	@Test
	public void updateProfessorTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Professor professorToSave = this.professorFactory.createProfessor(null, "carlos", "00000010", "00000010");
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
		
		private static void assertProfessor(List<Professor> expected, List<Professor> actual) {
			Assert.assertEquals(expected.size(), actual.size());
			for (int i = 0; i < expected.size(); i++) {
				assertProfessor(expected.get(i), actual.get(i));
			}
		}

}
