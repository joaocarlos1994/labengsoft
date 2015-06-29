package br.com.fatec.projeto.biblioteca.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Editora;
import br.com.fatec.projeto.biblioteca.api.service.EditoraDAO;
import br.com.fatec.projeto.biblioteca.core.helper.EditoraFactory;
import br.com.fatec.projeto.biblioteca.core.impl.EditoraDAOImpl;
import br.com.fatec.projeto.biblioteca.core.service.GeradorIdService;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigCenarioTestCase;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

public class EditoraDAOTest extends ConfigCenarioTestCase{
	
	private EditoraDAO editoraDAO;
	private EditoraDAOImpl editoraDAOImpl;

	@Before
	public void config() {
		this.editoraDAO = new EditoraDAOImpl();
		this.editoraDAOImpl = new EditoraDAOImpl();
	}

	@Test
	public void saveEditoraTest() {
		
		Editora editoraToSave = this.editoraDAOImpl.findById(1L);
		Editora savedEditora = this.editoraDAO.save(editoraToSave);

		assertEditora(editoraToSave, savedEditora);
	}

	@Test
	public void findAllTest() {
		
		Editora editoraToSave1 = this.editoraDAOImpl.findById(1L);
		
		
		Editora editoraToSave2 = this.editoraDAOImpl.findById(2L);
		
		
		Editora editoraToSave3 = this.editoraDAOImpl.findById(3L);

		List<Editora> expectedEditoras = new ArrayList<Editora>();
		expectedEditoras.add(editoraToSave1);
		expectedEditoras.add(editoraToSave2);
		expectedEditoras.add(editoraToSave3);

		List<Editora> encontrados = this.editoraDAO.findAll();

		assertEditoras(expectedEditoras, encontrados);
	}

	@Test
	public void removeEditoraTest() {
		
		Editora editoraToSave = this.editoraDAOImpl.findById(1L);
		Editora savedEditora = this.editoraDAO.save(editoraToSave);

		assertEditora(editoraToSave, savedEditora);
		this.editoraDAO.remove(savedEditora);

		Assert.assertNull(this.editoraDAO.findById(savedEditora.getId()));
	}

	@Test
	public void updateEditoraTest() {
		
		Editora editoraToSave = this.editoraDAOImpl.findById(1L);
		Editora savedEditora = this.editoraDAO.save(editoraToSave);

		assertEditora(editoraToSave, savedEditora);
		savedEditora.setNomeEditora("Microsoft");
		savedEditora.setSeguimento("Windows");

		Editora updatedEditora = this.editoraDAO.update(savedEditora);

		assertEditora(savedEditora, updatedEditora);
	}

	// auxiliar

	private static void assertEditora(Editora expected, Editora actual) {
		Assert.assertEquals(expected.getNomeEditora(), actual.getNomeEditora());
		Assert.assertEquals(expected.getSeguimento(), actual.getSeguimento());
	}

	private static void assertEditoras(List<Editora> expected, List<Editora> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEditora(expected.get(i), actual.get(i));
		}
	}

}
