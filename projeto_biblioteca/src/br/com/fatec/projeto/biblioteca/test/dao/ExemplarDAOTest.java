package br.com.fatec.projeto.biblioteca.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Exemplar;
import br.com.fatec.projeto.biblioteca.api.service.ExemplarDAO;
import br.com.fatec.projeto.biblioteca.core.helper.ExemplarFactory;
import br.com.fatec.projeto.biblioteca.core.impl.ExemplarDAOImpl;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigCenarioTestCase;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

public class ExemplarDAOTest extends ConfigCenarioTestCase{
	
	private ExemplarDAO exemplarDAO;
	private ExemplarDAOImpl exemplarDAOImpl;

	@Before
	public void config() {
		this.exemplarDAO = new ExemplarDAOImpl();
		this.exemplarDAOImpl = new ExemplarDAOImpl();
	}

	@Test
	public void saveExemplarTest() {

		Exemplar exemplarToSave = this.exemplarDAOImpl.findById(1L);
		Exemplar savedExemplar = this.exemplarDAO.save(exemplarToSave);
		assertExemplar(exemplarToSave, savedExemplar);
	}

	@Test
	public void findAllTest() {
		
		Exemplar exemplarToSave1 = this.exemplarDAOImpl.findById(1L);
		
		Exemplar exemplarToSave2 = this.exemplarDAOImpl.findById(2L);

		Exemplar exemplarToSave3 = this.exemplarDAOImpl.findById(3L);

		List<Exemplar> expectedExemplars = new ArrayList<Exemplar>();
		expectedExemplars.add(exemplarToSave1);
		expectedExemplars.add(exemplarToSave2);
		expectedExemplars.add(exemplarToSave3);

		List<Exemplar> encontrados = this.exemplarDAO.findAll();

		assertExemplars(expectedExemplars, encontrados);
	}

	@Test
	public void removeExemplarTest() {
		
		Exemplar exemplarToSave = this.exemplarDAOImpl.findById(1L);
		Exemplar savedExemplar = this.exemplarDAO.save(exemplarToSave);

		assertExemplar(exemplarToSave, savedExemplar);
		this.exemplarDAO.remove(savedExemplar);

		Assert.assertNull(this.exemplarDAO.findById(savedExemplar.getId()));
	}

	@Test
	public void updateExemplarTest() {
		
		Exemplar exemplarToSave = this.exemplarDAOImpl.findById(1L);
		
		exemplarToSave.setCodigoExemplar(2);

		Exemplar updatedExemplar = this.exemplarDAO.update(exemplarToSave);
		exemplarToSave = this.exemplarDAOImpl.findById(1L);

		assertExemplar(exemplarToSave, updatedExemplar);
	}

	// auxiliar

	private static void assertExemplar(Exemplar expected, Exemplar actual) {
		Assert.assertEquals(expected.getCodigoExemplar(), actual.getCodigoExemplar());
		
	}

	private static void assertExemplars(List<Exemplar> expected, List<Exemplar> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertExemplar(expected.get(i), actual.get(i));
		}
	}

}
