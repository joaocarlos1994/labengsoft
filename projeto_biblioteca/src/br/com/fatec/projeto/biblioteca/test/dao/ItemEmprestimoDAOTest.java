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
import br.com.fatec.projeto.biblioteca.core.impl.EmprestimoDAOImpl;
import br.com.fatec.projeto.biblioteca.core.impl.ExemplarDAOImpl;
import br.com.fatec.projeto.biblioteca.core.impl.ItemEmprestimoDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigCenarioTestCase;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

public class ItemEmprestimoDAOTest extends ConfigCenarioTestCase{
	
	
	private ItemEmprestimoDAO itemEmprestimoDAO;
	private ItemEmprestimoDAOImpl itemEmprestimoDAOImpl;
	private EmprestimoDAOImpl emprestimoDAOImpl;
	private ExemplarDAOImpl exemplarDAOImpl;
	

	@Before
	public void config() {
		this.itemEmprestimoDAO = new ItemEmprestimoDAOImpl();
		this.itemEmprestimoDAOImpl = new ItemEmprestimoDAOImpl();
		this.emprestimoDAOImpl = new EmprestimoDAOImpl();
		this.exemplarDAOImpl = new ExemplarDAOImpl();
	}

	@Test
	public void saveItemEmprestimoTest() {

		ItemEmprestimo itemEmprestimoToSave = this.itemEmprestimoDAOImpl.findById(1L);
		ItemEmprestimo savedItemEmprestimo = this.itemEmprestimoDAO.save(itemEmprestimoToSave);

		assertItemEmprestimo(itemEmprestimoToSave, savedItemEmprestimo);
	}

	@Test
	public void findAllTest() {
		
		ItemEmprestimo itemEmprestimoToSave1 = this.itemEmprestimoDAOImpl.findById(1L);
		
		ItemEmprestimo itemEmprestimoToSave2 = this.itemEmprestimoDAOImpl.findById(2L);
	
		ItemEmprestimo itemEmprestimoToSave3 = this.itemEmprestimoDAOImpl.findById(3L);

		List<ItemEmprestimo> expectedItemEmprestimos = new ArrayList<ItemEmprestimo>();
		expectedItemEmprestimos.add(itemEmprestimoToSave1);
		expectedItemEmprestimos.add(itemEmprestimoToSave2);
		expectedItemEmprestimos.add(itemEmprestimoToSave3);

		List<ItemEmprestimo> encontrados = this.itemEmprestimoDAO.findAll();

		assertItemEmprestimos(expectedItemEmprestimos, encontrados);
	}

	@Test
	public void removeItemEmprestimoTest() {

		ItemEmprestimo itemEmprestimoToSave = this.itemEmprestimoDAOImpl.findById(1L);
		ItemEmprestimo savedItemEmprestimo = this.itemEmprestimoDAO.save(itemEmprestimoToSave);

		assertItemEmprestimo(itemEmprestimoToSave, savedItemEmprestimo);
		this.itemEmprestimoDAO.remove(savedItemEmprestimo);

		Assert.assertNull(this.itemEmprestimoDAO.findById(savedItemEmprestimo.getId()));
	}

	@Test
	public void updateItemEmprestimoTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		ItemEmprestimo itemEmprestimoToSave = this.itemEmprestimoDAOImpl.findById(1L);
		ItemEmprestimo savedItemEmprestimo = this.itemEmprestimoDAO.save(itemEmprestimoToSave);

		assertItemEmprestimo(itemEmprestimoToSave, savedItemEmprestimo);
		savedItemEmprestimo.setEmprestimo(emprestimoDAOImpl.findById(2L));
		savedItemEmprestimo.setExemplar(exemplarDAOImpl.findById(3L));

		ItemEmprestimo updatedItemEmprestimo = this.itemEmprestimoDAO.update(savedItemEmprestimo);

		assertItemEmprestimo(savedItemEmprestimo, updatedItemEmprestimo);
	}

	// auxiliar

	private static void assertItemEmprestimo(ItemEmprestimo expected, ItemEmprestimo actual) {
		Assert.assertEquals(expected.getEmprestimo().getDataEmprestimo(), actual.getEmprestimo().getDataEmprestimo());
		Assert.assertEquals(expected.getExemplar().getCodigoExemplar(), actual.getExemplar().getCodigoExemplar());
	}

	private static void assertItemEmprestimos(List<ItemEmprestimo> expected, List<ItemEmprestimo> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertItemEmprestimo(expected.get(i), actual.get(i));
		}
	}

}
