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
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

public class LivroDAOTest extends ConfigDBTestCase{
	
	private LivroDAO livroDAO;
	private LivroFactory livroFactory;

	@Before
	public void config() {
		this.livroDAO = new LivroDAOImpl();
		this.livroFactory = new LivroFactory();
	}

	@Test
	public void saveLivroTest() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Livro livroToSave = this.livroFactory.createLivro(null, "Python", 1, calendar.getTime(), 1L);
		Livro savedLivro = this.livroDAO.save(livroToSave);

		assertLivro(livroToSave, savedLivro);
	}

	@Test
	public void findAllTest() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);
		Livro livroToSave1 = this.livroFactory.createLivro(null, "Python", 1, calendar.getTime(), 1L);
		
		calendar.set(1994, 5, 2);
		Livro livroToSave2 = this.livroFactory.createLivro(null, "Python", 1, calendar.getTime(), 2L);

		calendar.set(2015, 5, 2);
		Livro livroToSave3 = this.livroFactory.createLivro(null, "Python", 1, calendar.getTime(), 3L);

		List<Livro> expectedLivros = new ArrayList<Livro>();
		expectedLivros.add(this.livroDAO.save(livroToSave1));
		expectedLivros.add(this.livroDAO.save(livroToSave2));
		expectedLivros.add(this.livroDAO.save(livroToSave3));

		List<Livro> encontrados = this.livroDAO.findAll();

		assertLivros(expectedLivros, encontrados);
	}

	@Test
	public void removeLivroTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Livro livroToSave = this.livroFactory.createLivro(null, "Python", 1, calendar.getTime(), 1L);
		Livro savedLivro = this.livroDAO.save(livroToSave);

		assertLivro(livroToSave, savedLivro);
		this.livroDAO.remove(savedLivro);

		Assert.assertNull(this.livroDAO.findById(savedLivro.getId()));
	}

	@Test
	public void updateLivroTest() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);
		Livro livroToSave = this.livroFactory.createLivro(null, "Python", 1, calendar.getTime(), 3L);
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
