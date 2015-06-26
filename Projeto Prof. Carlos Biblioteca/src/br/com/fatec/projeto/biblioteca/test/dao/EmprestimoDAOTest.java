package br.com.fatec.projeto.biblioteca.test.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;
import br.com.fatec.projeto.biblioteca.api.service.EmprestimoDAO;
import br.com.fatec.projeto.biblioteca.core.helper.EmprestimoFactory;
import br.com.fatec.projeto.biblioteca.core.impl.AlunoDAOImpl;
import br.com.fatec.projeto.biblioteca.core.impl.EmprestimoDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigCenarioTestCase;
import br.com.fatec.projeto.biblioteca.test.commons.CustomAsserts;

public class EmprestimoDAOTest extends ConfigCenarioTestCase{
	
	private EmprestimoDAO emprestimoDAO;
	private EmprestimoDAOImpl emprestimoDAOImpl;
	private AlunoDAOImpl alunoDAOImpl;
	
	@Before
	public void config() {
		this.emprestimoDAO = new EmprestimoDAOImpl();
		this.emprestimoDAOImpl = new EmprestimoDAOImpl();
		alunoDAOImpl = new AlunoDAOImpl();
	}

	@Test
	public void saveEmprestimoTest() {

		//Emprestimo emprestimoToSave = this.emprestimoDAOImpl.findById(1L);
		
		EmprestimoFactory emprestimoFactory = new EmprestimoFactory();
		Emprestimo createEmprestimo = emprestimoFactory.createEmprestimo(null, alunoDAOImpl.findById(1L), new Date(), new Date());
		
		
		Emprestimo savedEmprestimo = this.emprestimoDAO.save(createEmprestimo);

		assertEmprestimo(createEmprestimo , savedEmprestimo);
	}

	@Test
	public void findAllTest() {
		
		Emprestimo emprestimoToSave1 = this.emprestimoDAOImpl.findById(1L);
				
		Emprestimo emprestimoToSave2 = this.emprestimoDAOImpl.findById(2L);
				
		Emprestimo emprestimoToSave3 = this.emprestimoDAOImpl.findById(3L);

		List<Emprestimo> expectedEmprestimos = new ArrayList<Emprestimo>();
		expectedEmprestimos.add(emprestimoToSave1);
		expectedEmprestimos.add(emprestimoToSave2);
		expectedEmprestimos.add(emprestimoToSave3);

		List<Emprestimo> encontrados = this.emprestimoDAO.findAll();

		assertEmprestimos(expectedEmprestimos, encontrados);
	}

	@Test
	public void removeEmprestimoTest() {
		
		Emprestimo emprestimoToSave = this.emprestimoDAOImpl.findById(1L);
		Emprestimo savedEmprestimo = this.emprestimoDAO.save(emprestimoToSave);

		assertEmprestimo(emprestimoToSave, savedEmprestimo);
		this.emprestimoDAO.remove(savedEmprestimo);

		Assert.assertNull(this.emprestimoDAO.findById(savedEmprestimo.getId()));
	}

	@Test
	public void updateEmprestimoTest() {
		
		Emprestimo emprestimoToSave = this.emprestimoDAOImpl.findById(1L);
		emprestimoToSave.setDataEntrega(new Date());


		Emprestimo updatedEmprestimo = this.emprestimoDAO.update(emprestimoToSave);
		emprestimoToSave = this.emprestimoDAOImpl.findById(1L);
		
		assertEmprestimo(emprestimoToSave, updatedEmprestimo);
	}

	// auxiliar

	private static void assertEmprestimo(Emprestimo expected, Emprestimo actual) {
		Assert.assertEquals(expected.getPessoa().getNome(), expected.getPessoa().getNome());
		CustomAsserts.assertDatas(expected.getDataEntrega(), actual.getDataEntrega());
		
		
	}

	private static void assertEmprestimos(List<Emprestimo> expected, List<Emprestimo> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEmprestimo(expected.get(i), actual.get(i));
		}
	}

}
