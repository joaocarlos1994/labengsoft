package br.com.fatec.projeto.biblioteca.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.ItemEmprestimo;
import br.com.fatec.projeto.biblioteca.api.service.EmprestimoDAO;
import br.com.fatec.projeto.biblioteca.api.service.ExemplarDAO;
import br.com.fatec.projeto.biblioteca.api.service.ItemEmprestimoDAO;
import br.com.fatec.projeto.biblioteca.core.helper.ItemEmprestimoFactory;
import br.com.fatec.projeto.biblioteca.core.impl.ItemEmprestimoDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

public class ItemEmprestimoDAOTest extends ConfigDBTestCase{
	
	private ItemEmprestimoDAO itemEmprestimoDAO;
	private ItemEmprestimoFactory itemEmprestimoFactory;
	private ExemplarDAO exemplarDAO;
	private EmprestimoDAO emprestimoDAO;
	

	@Before
	public void config() {
		this.itemEmprestimoDAO = new ItemEmprestimoDAOImpl();
		this.itemEmprestimoFactory = new ItemEmprestimoFactory();
	}

	@Test
	public void saveItemEmprestimoTest() {

		ItemEmprestimo itemEmprestimoToSave = this.itemEmprestimoFactory.createItemEmprestimo(null, 1, 1);
		ItemEmprestimo savedItemEmprestimo = this.itemEmprestimoDAO.save(itemEmprestimoToSave);

		assertItemEmprestimo(itemEmprestimoToSave, savedItemEmprestimo);
	}

	@Test
	public void findAllTest() {
		
		ItemEmprestimo itemEmprestimoToSave1 = this.itemEmprestimoFactory.createItemEmprestimo(null, 1, 2);
		
		ItemEmprestimo itemEmprestimoToSave2 = this.itemEmprestimoFactory.createItemEmprestimo(null, 3, 4);
	
		ItemEmprestimo itemEmprestimoToSave3 = this.itemEmprestimoFactory.createItemEmprestimo(null, 5, 6);

		List<ItemEmprestimo> expectedItemEmprestimos = new ArrayList<ItemEmprestimo>();
		expectedItemEmprestimos.add(this.itemEmprestimoDAO.save(itemEmprestimoToSave1));
		expectedItemEmprestimos.add(this.itemEmprestimoDAO.save(itemEmprestimoToSave2));
		expectedItemEmprestimos.add(this.itemEmprestimoDAO.save(itemEmprestimoToSave3));

		List<ItemEmprestimo> encontrados = this.itemEmprestimoDAO.findAll();

		assertItemEmprestimos(expectedItemEmprestimos, encontrados);
	}

	@Test
	public void removeItemEmprestimoTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		ItemEmprestimo itemEmprestimoToSave = this.itemEmprestimoFactory.createItemEmprestimo(null, 1, 2);
		ItemEmprestimo savedItemEmprestimo = this.itemEmprestimoDAO.save(itemEmprestimoToSave);

		assertItemEmprestimo(itemEmprestimoToSave, savedItemEmprestimo);
		this.itemEmprestimoDAO.remove(savedItemEmprestimo);

		Assert.assertNull(this.itemEmprestimoDAO.findById(savedItemEmprestimo.getId()));
	}

	@Test
	public void updateItemEmprestimoTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		ItemEmprestimo itemEmprestimoToSave = this.itemEmprestimoFactory.createItemEmprestimo(null, 1, 2);
		ItemEmprestimo savedItemEmprestimo = this.itemEmprestimoDAO.save(itemEmprestimoToSave);

		assertItemEmprestimo(itemEmprestimoToSave, savedItemEmprestimo);
		savedItemEmprestimo.setEmprestimo(emprestimoDAO.findById(2L));
		savedItemEmprestimo.setExemplar(exemplarDAO.findById(3L));

		ItemEmprestimo updatedItemEmprestimo = this.itemEmprestimoDAO.update(savedItemEmprestimo);

		assertItemEmprestimo(savedItemEmprestimo, updatedItemEmprestimo);
	}

	// auxiliar

	private static void assertItemEmprestimo(ItemEmprestimo expected, ItemEmprestimo actual) {
		Assert.assertEquals(expected.getEmprestimo(), actual.getEmprestimo());
		Assert.assertEquals(expected.getExemplar(), actual.getExemplar());
	}

	private static void assertItemEmprestimos(List<ItemEmprestimo> expected, List<ItemEmprestimo> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertItemEmprestimo(expected.get(i), actual.get(i));
		}
	}

}
