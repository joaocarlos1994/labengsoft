package br.com.fatec.projeto.biblioteca.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;
import br.com.fatec.projeto.biblioteca.api.entity.Pessoa;
import br.com.fatec.projeto.biblioteca.api.service.EmprestimoDAO;
import br.com.fatec.projeto.biblioteca.core.helper.EmprestimoFactory;
import br.com.fatec.projeto.biblioteca.core.impl.EmprestimoDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

public class EmprestimoDAOTest extends ConfigDBTestCase{
	
	private EmprestimoDAO emprestimoDAO;
	private EmprestimoFactory emprestimoFactory;
	private Emprestimo emprestimo;
	
	@Before
	public void config() {
		this.emprestimoDAO = new EmprestimoDAOImpl();
		this.emprestimoFactory = new EmprestimoFactory();
	}

	@Test
	public void saveEmprestimoTest() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(1991, 5, 2);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(1991, 5, 9);

		Emprestimo emprestimoToSave = this.emprestimoFactory.createEmprestimo(null, 1,
				calendar1.getTime(), calendar2.getTime());
		Emprestimo savedEmprestimo = this.emprestimoDAO.save(emprestimoToSave);

		assertEmprestimo(emprestimoToSave, savedEmprestimo);
	}

	@Test
	public void findAllTest() {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		calendar1.set(1991, 5, 2);
		calendar2.set(1991, 5, 9);
		Emprestimo emprestimoToSave1 = this.emprestimoFactory.createEmprestimo(null, 1,
				calendar1.getTime(), calendar2.getTime());
		calendar1.set(1991, 5, 3);
		calendar2.set(1991, 5, 10);
		Emprestimo emprestimoToSave2 = this.emprestimoFactory.createEmprestimo(null, 2,
				calendar1.getTime(), calendar2.getTime());
		calendar1.set(1991, 5, 4);
		calendar2.set(1991, 5, 11);
		Emprestimo emprestimoToSave3 = this.emprestimoFactory.createEmprestimo(null, 3,
				calendar1.getTime(), calendar2.getTime());

		List<Emprestimo> expectedEmprestimos = new ArrayList<Emprestimo>();
		expectedEmprestimos.add(this.emprestimoDAO.save(emprestimoToSave1));
		expectedEmprestimos.add(this.emprestimoDAO.save(emprestimoToSave2));
		expectedEmprestimos.add(this.emprestimoDAO.save(emprestimoToSave3));

		List<Emprestimo> encontrados = this.emprestimoDAO.findAll();

		assertEmprestimos(expectedEmprestimos, encontrados);
	}

	@Test
	public void removeEmprestimoTest() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(1991, 5, 2);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(1991, 5, 9);

		Emprestimo emprestimoToSave = this.emprestimoFactory.createEmprestimo(null, 1,
				calendar1.getTime(), calendar2.getTime());
		Emprestimo savedEmprestimo = this.emprestimoDAO.save(emprestimoToSave);

		assertEmprestimo(emprestimoToSave, savedEmprestimo);
		this.emprestimoDAO.remove(savedEmprestimo);

		Assert.assertNull(this.emprestimoDAO.findById(savedEmprestimo.getId()));
	}

	@Test
	public void updateEmprestimoTest() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(1991, 5, 2);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(1991, 5, 9);
		
		Emprestimo emprestimoToSave = this.emprestimoFactory.createEmprestimo(null, 1,
				calendar1.getTime(), calendar2.getTime());
		Emprestimo savedEmprestimo = this.emprestimoDAO.save(emprestimoToSave);

		calendar2.set(1991, 5, 5);
		
		assertEmprestimo(emprestimoToSave, savedEmprestimo);
		savedEmprestimo.setDataEntrega(calendar2.getTime());

		Emprestimo updatedEmprestimo = this.emprestimoDAO.update(savedEmprestimo);

		assertEmprestimo(savedEmprestimo, updatedEmprestimo);
	}

	// auxiliar

	private static void assertEmprestimo(Emprestimo expected, Emprestimo actual) {
		Assert.assertEquals(expected.getDataEntrega(), actual.getDataEntrega());
	}

	private static void assertEmprestimos(List<Emprestimo> expected, List<Emprestimo> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEmprestimo(expected.get(i), actual.get(i));
		}
	}

}
