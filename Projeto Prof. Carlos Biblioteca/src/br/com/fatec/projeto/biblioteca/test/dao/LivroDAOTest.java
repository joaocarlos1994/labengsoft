package br.com.fatec.projeto.biblioteca.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Livro;
import br.com.fatec.projeto.biblioteca.api.service.LivroDAO;
import br.com.fatec.projeto.biblioteca.core.helper.LivroFactory;
import br.com.fatec.projeto.biblioteca.core.impl.LivroDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigCenarioTestCase;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

public class LivroDAOTest extends ConfigCenarioTestCase{
	
	private LivroDAO livroDAO;
	private LivroDAOImpl livroDAOImpl;

	@Before
	public void config() {
		this.livroDAO = new LivroDAOImpl();
		this.livroDAOImpl = new LivroDAOImpl();
	}
	
	

	@Test
	public void saveLivroTest() {
		
		Livro livroToSave = this.livroDAOImpl.findById(1L);
		Livro savedLivro = this.livroDAO.save(livroToSave);

		assertLivro(livroToSave, savedLivro);
	}

	@Test
	public void findAllTest() {
		
		Livro livroToSave1 = this.livroDAOImpl.findById(1L);
		
		Livro livroToSave2 = this.livroDAOImpl.findById(2L);

		Livro livroToSave3 = this.livroDAOImpl.findById(3L);

		List<Livro> expectedLivros = new ArrayList<Livro>();
		expectedLivros.add(livroToSave1);
		expectedLivros.add(livroToSave2);
		expectedLivros.add(livroToSave3);

		List<Livro> encontrados = this.livroDAO.findAll();

		assertLivros(expectedLivros, encontrados);
	}

	@Test
	public void removeLivroTest() {
		
		Livro livroToSave = this.livroDAOImpl.findById(1L);
		Livro savedLivro = this.livroDAO.save(livroToSave);

		assertLivro(livroToSave, savedLivro);
		this.livroDAO.remove(savedLivro);

		Assert.assertNull(this.livroDAO.findById(savedLivro.getId()));
	}

	@Test
	public void updateLivroTest() {
		
		Livro livroToSave = this.livroDAOImpl.findById(1L);
		Livro savedLivro = this.livroDAO.save(livroToSave);

		assertLivro(livroToSave, savedLivro);
		savedLivro.setTitulo("Python for Zumbi");

		Livro updatedLivro = this.livroDAO.update(savedLivro);

		assertLivro(savedLivro, updatedLivro);
	}

	// auxiliar

	private static void assertLivro(Livro expected, Livro actual) {
		Assert.assertEquals(expected.getTitulo(), actual.getTitulo());
		
	}

	private static void assertLivros(List<Livro> expected, List<Livro> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertLivro(expected.get(i), actual.get(i));
		}
	}

}
