package br.com.fatec.projeto.biblioteca.core.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.entity.Professor;
import br.com.fatec.projeto.biblioteca.api.service.ProfessorDAO;
import br.com.fatec.projeto.biblioteca.core.helper.ProfessorFactory;
import br.com.fatec.projeto.biblioteca.core.service.GeradorIdService;

public class ProfessorDAOImpl implements ProfessorDAO{
	
	private Connection connection;
	private ProfessorFactory professorFactory;
	
	public ProfessorDAOImpl() {
		this.professorFactory = new ProfessorFactory();
		try {
			this.connection = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		} catch (SQLException e) {
			throw new RuntimeException("erro ao gerar conexão", e);
		}
	}
	
	@Override
	public Professor save(Professor professor) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Professor.TABLE + " VALUES (?,?,?,?);");
			Long id = GeradorIdService.getInstance().nextId();
			insert.setLong(1, id);
			insert.setString(2, professor.getNome());
			insert.setString(3, professor.getRegistro());
			insert.setString(4, professor.getRg());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar professor", e);
		}
	}

	@Override
	public boolean remove(Professor professor) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Professor.TABLE + " WHERE " + Aluno.COL_ID + " = ?;");
			remove.setLong(1, professor.getId());
			return remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("erro ao remover aluno com id: " + professor.getId(), e);
		}
	}

	@Override
	public List<Professor> findAll() {
		PreparedStatement query = null;
		List<Professor> professor = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Professor.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			professor = this.professorFactory.createProfessores(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}

		return professor;
	}

	@Override
	public Professor findById(Long id) {
		PreparedStatement query = null;
		Professor professor = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Professor.TABLE + " WHERE " + Professor.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				professor = this.professorFactory.createProfessor(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado no sistema");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("resultado nãoo inicializado", e);
		}
		return professor;
	}

	@Override
	public Professor update(Professor professor) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Professor.TABLE + " SET " + Professor.COL_NOME + " = ?, "
					+ Professor.COL_REGISTRO + " = ?, " + Professor.COL_RG + " = ?;");
			update.setString(1, professor.getNome());
			update.setString(2, professor.getRegistro());
			update.setString(3, professor.getRg());
			update.execute();
			return this.findById(professor.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar professor", e);
		}
	}

}
