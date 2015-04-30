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
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

public class EditoraDAOTest extends ConfigDBTestCase{
	
	private EditoraDAO editoraDAO;
	private EditoraFactory editoraFactory;

	@Before
	public void config() {
		this.editoraDAO = new EditoraDAOImpl();
		this.editoraFactory = new EditoraFactory();
	}

	@Test
	public void saveEditoraTest() {
		
		Editora editoraToSave = this.editoraFactory.createEditora(null, "Novatec", "Tecnologia");
		Editora savedEditora = this.editoraDAO.save(editoraToSave);

		assertEditora(editoraToSave, savedEditora);
	}

	@Test
	public void findAllTest() {
		
		Editora editoraToSave1 = this.editoraFactory.createEditora(null, "Novatec1", "Tecnologia1");
		
		
		Editora editoraToSave2 = this.editoraFactory.createEditora(null, "Novatec2", "Tecnologia2");
		
		
		Editora editoraToSave3 = this.editoraFactory.createEditora(null, "Novatec3", "Tecnologia3");

		List<Editora> expectedEditoras = new ArrayList<Editora>();
		expectedEditoras.add(this.editoraDAO.save(editoraToSave1));
		expectedEditoras.add(this.editoraDAO.save(editoraToSave2));
		expectedEditoras.add(this.editoraDAO.save(editoraToSave3));

		List<Editora> encontrados = this.editoraDAO.findAll();

		assertEditoras(expectedEditoras, encontrados);
	}

	@Test
	public void removeEditoraTest() {
		
		Editora editoraToSave = this.editoraFactory.createEditora(null, "Novatec", "Tecnologia");
		Editora savedEditora = this.editoraDAO.save(editoraToSave);

		assertEditora(editoraToSave, savedEditora);
		this.editoraDAO.remove(savedEditora);

		Assert.assertNull(this.editoraDAO.findById(savedEditora.getId()));
	}

	@Test
	public void updateEditoraTest() {
		
		Editora editoraToSave = this.editoraFactory.createEditora(null, "Novatec", "Tecnologia");
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
