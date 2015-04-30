package br.com.fatec.projeto.biblioteca.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Exemplar;
import br.com.fatec.projeto.biblioteca.api.entity.Exemplar;
import br.com.fatec.projeto.biblioteca.api.entity.Livro;
import br.com.fatec.projeto.biblioteca.api.service.LivroDAO;

public class ExemplarFactory {
	
	private LivroDAO livroDAO;
	
	public Exemplar createExemplar(Long id, Livro livro, long codigoExemplar){
		
		Exemplar exemplar = new Exemplar();
		exemplar.setId(id);
		exemplar.setLivro(livro);
		exemplar.setCodigoExemplar(codigoExemplar);
		
		return exemplar;
	}
	
	public Exemplar createExemplar(Long id, long livroId, long codigoExemplar){
		
		Exemplar exemplar = new Exemplar();
		exemplar.setId(id);
		exemplar.setLivro(livroDAO.findById(id));
		exemplar.setCodigoExemplar(codigoExemplar);
		
		return exemplar;
	}
	
	public Exemplar criarExemplar(ResultSet resultado) {
		try {
			return this.createExemplar(resultado.getLong(Exemplar.COL_ID), resultado.getLong(Exemplar.COL_LIVRO), 
					resultado.getLong(Exemplar.COL_EXEMPLAR));
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

	public List<Exemplar> createExemplars(ResultSet resultado) {
		try {
			List<Exemplar> exemplares = new ArrayList<Exemplar>();
			while (resultado.next()) {
				exemplares.add(this.createExemplar(resultado.getLong(Exemplar.COL_ID), resultado.getLong(Exemplar.COL_LIVRO), 
									resultado.getLong(Exemplar.COL_EXEMPLAR)));
			}
			return exemplares;
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

}
