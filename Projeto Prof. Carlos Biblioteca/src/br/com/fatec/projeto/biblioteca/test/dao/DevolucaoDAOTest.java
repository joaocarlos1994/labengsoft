package br.com.fatec.projeto.biblioteca.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Devolucao;
import br.com.fatec.projeto.biblioteca.api.service.DevolucaoDAO;
import br.com.fatec.projeto.biblioteca.core.helper.DevolucaoFactory;
import br.com.fatec.projeto.biblioteca.core.helper.EditoraFactory;
import br.com.fatec.projeto.biblioteca.core.helper.EmprestimoFactory;
import br.com.fatec.projeto.biblioteca.core.helper.ExemplarFactory;
import br.com.fatec.projeto.biblioteca.core.helper.LivroFactory;
import br.com.fatec.projeto.biblioteca.core.impl.DevolucaoDAOImpl;
import br.com.fatec.projeto.biblioteca.core.impl.EmprestimoDAOImpl;
import br.com.fatec.projeto.biblioteca.core.impl.ExemplarDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigCenarioTestCase;

public class DevolucaoDAOTest extends ConfigCenarioTestCase{
	
	private DevolucaoDAO devolucaoDAO;
	private DevolucaoDAOImpl devolucaoDAOImpl;


	@Before
	public void config() {
		this.devolucaoDAO = new DevolucaoDAOImpl();
		this.devolucaoDAOImpl = new DevolucaoDAOImpl();
	}

	@Test
	public void saveDevolucaoTest() {
		
		Devolucao devolucaoToSave = devolucaoDAOImpl.findById(1L);
		Devolucao savedDevolucao = this.devolucaoDAO.save(devolucaoToSave);

		assertDevolucao(devolucaoToSave, savedDevolucao);
	}

	@Test
	public void findAllTest() {
		
		Devolucao devolucaoToSave1 = devolucaoDAOImpl.findById(1L);
		
		Devolucao devolucaoToSave2 = devolucaoDAOImpl.findById(2L);
		
		Devolucao devolucaoToSave3 = devolucaoDAOImpl.findById(3L);

		List<Devolucao> expectedDevolucaos = new ArrayList<Devolucao>();
		
		expectedDevolucaos.add(devolucaoToSave1);
		expectedDevolucaos.add(devolucaoToSave2);
		expectedDevolucaos.add(devolucaoToSave3);

		List<Devolucao> encontrados = this.devolucaoDAO.findAll();

		assertDevolucaos(expectedDevolucaos, encontrados);
	}

	@Test
	public void removeDevolucaoTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Devolucao devolucaoToSave = devolucaoDAOImpl.findById(1L);
		Devolucao savedDevolucao = this.devolucaoDAO.save(devolucaoToSave);

		assertDevolucao(devolucaoToSave, savedDevolucao);
		this.devolucaoDAO.remove(savedDevolucao);

		Assert.assertNull(this.devolucaoDAO.findById(savedDevolucao.getId()));
	}

	@Test
	public void updateDevolucaoTest() {
		
		Devolucao devolucaoToSave = devolucaoDAOImpl.findById(1L);
		Devolucao savedDevolucao = this.devolucaoDAO.save(devolucaoToSave);

		assertDevolucao(devolucaoToSave, savedDevolucao);

		Devolucao updatedDevolucao = this.devolucaoDAO.update(savedDevolucao);

		assertDevolucao(savedDevolucao, updatedDevolucao);
	}

	// auxiliar

	private static void assertDevolucao(Devolucao expected, Devolucao actual) {
		Assert.assertEquals(expected.getDataDevolucao(), actual.getDataDevolucao());
	}

	private static void assertDevolucaos(List<Devolucao> expected, List<Devolucao> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertDevolucao(expected.get(i), actual.get(i));
		}
	}
	
}
