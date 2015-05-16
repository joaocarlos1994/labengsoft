package br.com.fatec.projeto.biblioteca.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.entity.Exemplar;
import br.com.fatec.projeto.biblioteca.api.service.ExemplarDAO;
import br.com.fatec.projeto.biblioteca.core.helper.ExemplarFactory;
import br.com.fatec.projeto.biblioteca.core.service.GeradorIdService;

public class ExemplarDAOImpl implements ExemplarDAO{
	
	private Connection connection;
	private ExemplarFactory exemplarFactory;

	public ExemplarDAOImpl() {
		this.exemplarFactory = new ExemplarFactory();
		try {
			this.connection = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		} catch (SQLException e) {
			throw new RuntimeException("erro ao gerar conexão", e);
		}
	}

	@Override
	public Exemplar save(Exemplar exemplar) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Exemplar.TABLE + " VALUES (?,?);");
			Long id = GeradorIdService.getInstance().getNextId(Exemplar.TABLE);
			insert.setLong(1, id);
			insert.setLong(2, exemplar.getLivro().getId());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar exemplar", e);
		}
	}

	@Override
	public boolean remove(Exemplar exemplar) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Exemplar.TABLE + " WHERE " + Exemplar.COL_ID + " = ?;");
			remove.setLong(1, exemplar.getId());
			return remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("erro ao remover exemplar com id: " + exemplar.getId(), e);
		}
	}

	@Override
	public List<Exemplar> findAll() {
		PreparedStatement query = null;
		List<Exemplar> exemplars = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Exemplar.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			exemplars = this.exemplarFactory.createExemplars(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}

		return exemplars;
	}

	@Override
	public Exemplar findById(Long id) {
		PreparedStatement query = null;
		Exemplar exemplar = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Exemplar.TABLE + " WHERE " + Exemplar.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				exemplar = this.exemplarFactory.criarExemplar(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " est� duplicado no sistema");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}
		return exemplar;
	}

	@Override
	public Exemplar update(Exemplar exemplar) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Exemplar.TABLE + " SET " + Exemplar.COL_LIVRO + " = ?;");
			update.setLong(1, exemplar.getLivro().getId());
			update.execute();
			return this.findById(exemplar.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar exemplar", e);
		}
	}

}
