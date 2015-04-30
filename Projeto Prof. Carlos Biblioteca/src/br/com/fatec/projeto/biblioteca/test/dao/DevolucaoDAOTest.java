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
import br.com.fatec.projeto.biblioteca.core.helper.EmprestimoFactory;
import br.com.fatec.projeto.biblioteca.core.helper.ExemplarFactory;
import br.com.fatec.projeto.biblioteca.core.impl.DevolucaoDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

public class DevolucaoDAOTest extends ConfigDBTestCase{
	
	private DevolucaoDAO devolucaoDAO;
	private DevolucaoFactory devolucaoFactory;
	private Devolucao devolucao;
	private ExemplarFactory exemplarFactory;
	private EmprestimoFactory emprestimoFactory;

	@Before
	public void config() {
		this.devolucaoDAO = new DevolucaoDAOImpl();
		this.devolucaoFactory = new DevolucaoFactory();
	}

	@Test
	public void saveDevolucaoTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Devolucao devolucaoToSave = this.devolucaoFactory.createDevolucao(null, calendar.getTime(), devolucao.getExemplar().getId(), devolucao.getEmprestimo().getId());
		Devolucao savedDevolucao = this.devolucaoDAO.save(devolucaoToSave);

		assertDevolucao(devolucaoToSave, savedDevolucao);
	}

	@Test
	public void findAllTest() {
		Calendar calendar = Calendar.getInstance();

		calendar.set(1991, 5, 2);
		Devolucao devolucaoToSave1 = this.devolucaoFactory.createDevolucao(null, calendar.getTime(), devolucao.getExemplar().getId(), devolucao.getEmprestimo().getId());
		calendar.set(1991, 5, 3);
		Devolucao devolucaoToSave2 = this.devolucaoFactory.createDevolucao(null, calendar.getTime(), devolucao.getExemplar().getId(), devolucao.getEmprestimo().getId());
		calendar.set(1991, 5, 4);
		Devolucao devolucaoToSave3 = this.devolucaoFactory.createDevolucao(null, calendar.getTime(), devolucao.getExemplar().getId(), devolucao.getEmprestimo().getId());

		List<Devolucao> expectedDevolucaos = new ArrayList<Devolucao>();
		expectedDevolucaos.add(this.devolucaoDAO.save(devolucaoToSave1));
		expectedDevolucaos.add(this.devolucaoDAO.save(devolucaoToSave2));
		expectedDevolucaos.add(this.devolucaoDAO.save(devolucaoToSave3));

		List<Devolucao> encontrados = this.devolucaoDAO.findAll();

		assertDevolucaos(expectedDevolucaos, encontrados);
	}

	@Test
	public void removeDevolucaoTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Devolucao devolucaoToSave = this.devolucaoFactory.createDevolucao(null, calendar.getTime(), devolucao.getExemplar().getId(), devolucao.getEmprestimo().getId());
		Devolucao savedDevolucao = this.devolucaoDAO.save(devolucaoToSave);

		assertDevolucao(devolucaoToSave, savedDevolucao);
		this.devolucaoDAO.remove(savedDevolucao);

		Assert.assertNull(this.devolucaoDAO.findById(savedDevolucao.getId()));
	}

	@Test
	public void updateDevolucaoTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Devolucao devolucaoToSave = this.devolucaoFactory.createDevolucao(null, calendar.getTime(), devolucao.getExemplar().getId(), devolucao.getEmprestimo().getId());
		Devolucao savedDevolucao = this.devolucaoDAO.save(devolucaoToSave);

		assertDevolucao(devolucaoToSave, savedDevolucao);
		
		calendar.set(2015, 4, 27); //Settando data diferente
		
		savedDevolucao.setDataDevolucao(calendar.getTime());

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
