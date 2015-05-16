package br.com.fatec.projeto.biblioteca.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.entity.Editora;
import br.com.fatec.projeto.biblioteca.api.service.EditoraDAO;
import br.com.fatec.projeto.biblioteca.core.helper.EditoraFactory;
import br.com.fatec.projeto.biblioteca.core.service.GeradorIdService;

public class EditoraDAOImpl implements EditoraDAO {
	
	private Connection connection;
	private EditoraFactory editoraFactory;
	
	public EditoraDAOImpl() {
		this.editoraFactory = new EditoraFactory();
		try {
			this.connection = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		} catch (SQLException e) {
			throw new RuntimeException("erro ao gerar conexão", e);
		}
	}
	
	@Override
	public Editora save(Editora editora) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Editora.TABLE + " VALUES (?,?,?);");
			Long id = GeradorIdService.getInstance().getNextId(Editora.TABLE);
			insert.setLong(1, id);
			insert.setString(2, editora.getNomeEditora());
			insert.setString(3, editora.getSeguimento());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar editora", e);
		}
	}

	@Override
	public boolean remove(Editora editora) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Editora.TABLE + " WHERE " + Editora.COL_ID + " = ?;");
			remove.setLong(1, editora.getId());
			return remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("erro ao remover aluno com id: " + editora.getId(), e);
		}
	}

	@Override
	public List<Editora> findAll() {
		PreparedStatement query = null;
		List<Editora> editora = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Editora.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			editora = this.editoraFactory.createEditoras(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}

		return editora;
	}

	@Override
	public Editora findById(Long id) {
		PreparedStatement query = null;
		Editora editora = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Editora.TABLE + " WHERE " + Editora.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				editora = this.editoraFactory.createEditora(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado no sistema");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("resultado nãoo inicializado", e);
		}
		return editora;
	}

	@Override
	public Editora update(Editora editora) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Editora.TABLE + " SET " + Editora.COL_EDITORA + " = ?, "
					+ Editora.SEGUIMENTO + " = ?;");
			update.setString(1, editora.getNomeEditora());
			update.setString(2, editora.getSeguimento());
			update.execute();
			return this.findById(editora.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar editora", e);
		}
	}
	
}
