package br.com.fatec.projeto.biblioteca.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.service.AlunoDAO;
import br.com.fatec.projeto.biblioteca.core.helper.AlunoFactory;
import br.com.fatec.projeto.biblioteca.core.helper.ConfigDBMapper;
import br.com.fatec.projeto.biblioteca.core.service.GeradorIdService;

public class AlunoDAOImpl implements AlunoDAO {
	
	private Connection connection;
	private AlunoFactory alunoFactory;

	public AlunoDAOImpl() {
		this.alunoFactory = new AlunoFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnetion();
	}

	@Override
	public Aluno save(Aluno aluno) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Aluno.TABLE + " VALUES (?,?,?,?);");
			Long id = GeradorIdService.getInstance().getNextId(Aluno.TABLE);
			insert.setLong(1, id);
			insert.setString(2, aluno.getNome());
			insert.setString(3, aluno.getRa());
			insert.setString(4, aluno.getRg());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar aluno", e);
		}
	}

	@Override
	public boolean remove(Aluno aluno) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Aluno.TABLE + " WHERE " + Aluno.COL_ID + " = ?;");
			remove.setLong(1, aluno.getId());
			return remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("erro ao remover aluno com id: " + aluno.getId(), e);
		}
	}

	@Override
	public List<Aluno> findAll() {
		PreparedStatement query = null;
		List<Aluno> alunos = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Aluno.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			alunos = this.alunoFactory.createAlunos(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}

		return alunos;
	}

	@Override
	public Aluno findById(Long id) {
		PreparedStatement query = null;
		Aluno aluno = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Aluno.TABLE + " WHERE " + Aluno.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				aluno = this.alunoFactory.criarAluno(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " est� duplicado no sistema");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}
		return aluno;
	}

	@Override
	public Aluno update(Aluno aluno) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Aluno.TABLE + " SET " + Aluno.COL_NOME + " = ?, "
					+ Aluno.COL_RA + " = ?, " + Aluno.COL_RG + " = ?;");
			update.setString(1, aluno.getNome());
			update.setString(2, aluno.getRa());
			update.setString(3, aluno.getRg());
			update.execute();
			return this.findById(aluno.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar aluno", e);
		}
	}
	
}
