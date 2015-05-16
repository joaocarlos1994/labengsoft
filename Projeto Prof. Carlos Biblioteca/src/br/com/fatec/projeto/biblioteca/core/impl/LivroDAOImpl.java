package br.com.fatec.projeto.biblioteca.core.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.entity.Livro;
import br.com.fatec.projeto.biblioteca.api.service.LivroDAO;
import br.com.fatec.projeto.biblioteca.core.helper.LivroFactory;
import br.com.fatec.projeto.biblioteca.core.service.GeradorIdService;

public class LivroDAOImpl implements LivroDAO{
	
	private Connection connection;
	private LivroFactory livroFactory;

	public LivroDAOImpl() {
		this.livroFactory = new LivroFactory();
		try {
			this.connection = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		} catch (SQLException e) {
			throw new RuntimeException("erro ao gerar conexão", e);
		}
	}

	@Override
	public Livro save(Livro livro) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Livro.TABLE + " VALUES (?,?,?,?);");
			Long id = GeradorIdService.getInstance().getNextId(Livro.TABLE);
			insert.setLong(1, id);
			insert.setString(2, livro.getTitulo());
			insert.setDate(3, new Date(livro.getAnoPublicacao().getTime()));
			insert.setLong(4, livro.getEditora().getId());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar livro", e);
		}
	}

	@Override
	public boolean remove(Livro livro) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Livro.TABLE + " WHERE " + Livro.COL_ID + " = ?;");
			remove.setLong(1, livro.getId());
			return remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("erro ao remover livro com id: " + livro.getId(), e);
		}
	}

	@Override
	public List<Livro> findAll() {
		PreparedStatement query = null;
		List<Livro> livros = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Livro.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			livros = this.livroFactory.createLivros(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}

		return livros;
	}

	@Override
	public Livro findById(Long id) {
		PreparedStatement query = null;
		Livro livro = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Livro.TABLE + " WHERE " + Livro.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				livro = this.livroFactory.criarLivro(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " est� duplicado no sistema");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}
		return livro;
	}

	@Override
	public Livro update(Livro livro) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Livro.TABLE + " SET " + Livro.COL_TITULO + " = ?, "
					+ Livro.COL_EDICAO + " = ?, " + Livro.COL_ANO_PUBLICACAO + " = ?" + Livro.COL_EDITORA + " = ?;");
			update.setString(1, livro.getTitulo());
			update.setLong(2, livro.getEdicao());
			update.setDate(3, new Date(livro.getAnoPublicacao().getTime()));
			update.setLong(4, livro.getEditora().getId());
			update.execute();
			return this.findById(livro.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar livro", e);
		}
	}

}
