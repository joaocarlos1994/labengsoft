package br.com.fatec.projeto.biblioteca.web.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Exemplar;
import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;
import br.com.fatec.projeto.biblioteca.api.entity.Exemplar;
import br.com.fatec.projeto.biblioteca.api.entity.Livro;
import br.com.fatec.projeto.biblioteca.core.helper.ExemplarFactory;
import br.com.fatec.projeto.biblioteca.core.impl.ExemplarDAOImpl;

public class ExemplarAction {
	
	private static final long serialVersionUID = 1L;

	private ExemplarDAOImpl exemplarDAoImpl;
	private ExemplarFactory exemplarFactory;

	private Long id;
	private Livro livro;
	private long exemplar;
	private List<Exemplar> listExemplar;
	
	public String listExemplars() {

		exemplarDAoImpl = new ExemplarDAOImpl();

		listExemplar = new ArrayList<Exemplar>();
		listExemplar = exemplarDAoImpl.findAll();

		return "list";
	}

	public String registerExemplar() {

		exemplarFactory = new ExemplarFactory();
		exemplarDAoImpl = new ExemplarDAOImpl();

		Exemplar exemplar1 = exemplarFactory.createExemplar(id, livro, exemplar);
		exemplarDAoImpl.save(exemplar1);

		return this.listExemplars();
	}

	public String form() {
		listExemplar = new ArrayList<Exemplar>();
		ExemplarAction exemplarAction = new ExemplarAction();
		return "form";
	}

}
