package br.com.fatec.projeto.biblioteca.web.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.projeto.biblioteca.api.entity.Editora;
import br.com.fatec.projeto.biblioteca.api.entity.Livro;
import br.com.fatec.projeto.biblioteca.core.helper.LivroFactory;
import br.com.fatec.projeto.biblioteca.core.impl.LivroDAOImpl;

@Getter
@Setter
public class LivroAction {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String titulo;
	private long edicao;
	private Date anoPublicacao;
	private Editora editora;
	
	private LivroDAOImpl livroDAoImpl;
	private LivroFactory livroFactory;
	private List<Livro> listLivro;

	public String listLivros() {

		livroDAoImpl = new LivroDAOImpl();

		listLivro = new ArrayList<Livro>();
		listLivro = livroDAoImpl.findAll();

		return "list";
	}

	public String registerLivro() {

		livroFactory = new LivroFactory();
		livroDAoImpl = new LivroDAOImpl();
		Livro livro = livroFactory.createLivro(id, titulo, edicao, anoPublicacao, editora.getId());
		livroDAoImpl.save(livro);

		return this.listLivros();
	}

	public String form() {
		return "form";
	}

}