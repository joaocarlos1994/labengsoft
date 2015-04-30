package br.com.fatec.projeto.biblioteca.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Editora;
import br.com.fatec.projeto.biblioteca.api.entity.Livro;
import br.com.fatec.projeto.biblioteca.api.entity.Livro;
import br.com.fatec.projeto.biblioteca.api.service.EditoraDAO;

public class LivroFactory {
	
	private EditoraDAO editoraDAO;
	
	public Livro createLivro(Long id, String titulo, long edicao, 
			Date anoPublicacao, Editora editora){
		
		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setEdicao(edicao);
		livro.setAnoPublicacao(anoPublicacao);
		livro.setEditora(editora);
		
		return livro;
	}
	
	public Livro createLivro(Long id, String titulo, long edicao, 
			Date anoPublicacao, long editoraId){
		
		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setEdicao(edicao);
		livro.setAnoPublicacao(anoPublicacao);
		livro.setEditora(editoraDAO.findById(editoraId));
		
		return livro;
	}
	
	public Livro criarLivro(ResultSet resultado) {
		try {
			return this.createLivro(resultado.getLong(Livro.COL_ID), resultado.getString(Livro.COL_TITULO),
					resultado.getLong(Livro.COL_EDICAO), resultado.getDate(Livro.COL_ANO_PUBLICACAO),
					resultado.getLong(Livro.COL_EDITORA));
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

	public List<Livro> createLivros(ResultSet resultado) {
		try {
			List<Livro> livros = new ArrayList<Livro>();
			while (resultado.next()) {
				livros.add(this.createLivro(resultado.getLong(Livro.COL_ID), resultado.getString(Livro.COL_TITULO),
						resultado.getLong(Livro.COL_EDICAO), resultado.getDate(Livro.COL_ANO_PUBLICACAO),
						resultado.getLong(Livro.COL_EDITORA)));
			}
			return livros;
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

}
