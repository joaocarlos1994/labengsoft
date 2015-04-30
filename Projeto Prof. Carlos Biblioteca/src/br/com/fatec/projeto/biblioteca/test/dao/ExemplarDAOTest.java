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
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

public class ExemplarDAOTest extends ConfigDBTestCase{
	
	private ExemplarDAO exemplarDAO;
	private ExemplarFactory exemplarFactory;

	@Before
	public void config() {
		this.exemplarDAO = new ExemplarDAOImpl();
		this.exemplarFactory = new ExemplarFactory();
	}

	@Test
	public void saveExemplarTest() {

		Exemplar exemplarToSave = this.exemplarFactory.createExemplar(null, 1, 1);
		Exemplar savedExemplar = this.exemplarDAO.save(exemplarToSave);

		assertExemplar(exemplarToSave, savedExemplar);
	}

	@Test
	public void findAllTest() {
		
		Exemplar exemplarToSave1 = this.exemplarFactory.createExemplar(null, 1, 1);
		
		Exemplar exemplarToSave2 = this.exemplarFactory.createExemplar(null, 2, 2);

		Exemplar exemplarToSave3 = this.exemplarFactory.createExemplar(null, 3, 3);

		List<Exemplar> expectedExemplars = new ArrayList<Exemplar>();
		expectedExemplars.add(this.exemplarDAO.save(exemplarToSave1));
		expectedExemplars.add(this.exemplarDAO.save(exemplarToSave2));
		expectedExemplars.add(this.exemplarDAO.save(exemplarToSave3));

		List<Exemplar> encontrados = this.exemplarDAO.findAll();

		assertExemplars(expectedExemplars, encontrados);
	}

	@Test
	public void removeExemplarTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 5, 2);

		Exemplar exemplarToSave = this.exemplarFactory.createExemplar(null, 1, 1);
		Exemplar savedExemplar = this.exemplarDAO.save(exemplarToSave);

		assertExemplar(exemplarToSave, savedExemplar);
		this.exemplarDAO.remove(savedExemplar);

		Assert.assertNull(this.exemplarDAO.findById(savedExemplar.getId()));
	}

	@Test
	public void updateExemplarTest() {
		
		Exemplar exemplarToSave = this.exemplarFactory.createExemplar(null,  1, 1);
		Exemplar savedExemplar = this.exemplarDAO.save(exemplarToSave);

		assertExemplar(exemplarToSave, savedExemplar);
		savedExemplar.setCodigoExemplar(2);

		Exemplar updatedExemplar = this.exemplarDAO.update(savedExemplar);

		assertExemplar(savedExemplar, updatedExemplar);
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
