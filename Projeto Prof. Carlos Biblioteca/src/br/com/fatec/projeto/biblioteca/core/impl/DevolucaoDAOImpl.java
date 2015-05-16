package br.com.fatec.projeto.biblioteca.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.entity.Devolucao;
import br.com.fatec.projeto.biblioteca.api.service.DevolucaoDAO;
import br.com.fatec.projeto.biblioteca.api.service.EmprestimoDAO;
import br.com.fatec.projeto.biblioteca.api.service.ExemplarDAO;
import br.com.fatec.projeto.biblioteca.core.helper.DevolucaoFactory;
import br.com.fatec.projeto.biblioteca.core.service.GeradorIdService;

public class DevolucaoDAOImpl implements DevolucaoDAO{
	
	private Connection connection;
	private DevolucaoFactory devolucaoFactory;
	private ExemplarDAO exemplarDAO;
	private EmprestimoDAO emprestimoDAO;

	public DevolucaoDAOImpl() {
		this.devolucaoFactory = new DevolucaoFactory();
		try {
			this.connection = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		} catch (SQLException e) {
			throw new RuntimeException("erro ao gerar conexão", e);
		}
	}

	@Override
	public Devolucao save(Devolucao devolucao) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Devolucao.TABLE + " VALUES (?,?,?,?);");
			Long id = GeradorIdService.getInstance().getNextId(Devolucao.TABLE);
			insert.setLong(1, id);
			insert.setDate(2, new Date(devolucao.getDataDevolucao().getTime()));
			insert.setLong(3, devolucao.getExemplar().getId());
			insert.setLong(4, devolucao.getEmprestimo().getId());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar devolucao", e);
		}
	}

	@Override
	public boolean remove(Devolucao devolucao) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Devolucao.TABLE + " WHERE " + Devolucao.COL_ID + " = ?;");
			remove.setLong(1, devolucao.getId());
			return remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("erro ao remover devolucao com id: " + devolucao.getId(), e);
		}
	}

	@Override
	public List<Devolucao> findAll() {
		PreparedStatement query = null;
		List<Devolucao> devolucaos = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Devolucao.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			devolucaos = this.devolucaoFactory.createDevolucaos(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}

		return devolucaos;
	}

	@Override
	public Devolucao findById(Long id) {
		PreparedStatement query = null;
		Devolucao devolucao = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Devolucao.TABLE + " WHERE " + Devolucao.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				devolucao = this.devolucaoFactory.createDevolucao(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " est� duplicado no sistema");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado", e);
		}
		return devolucao;
	}

	@Override
	public Devolucao update(Devolucao devolucao) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Devolucao.TABLE + " SET " + Devolucao.COL_DATA_DEVOLUCAO + " = ?, "
					+ Devolucao.COL_EXEMPLAR + " = ?, " + Devolucao.COL_EMPRESTIMO + " = ?;");
			update.setDate(1, new Date(devolucao.getDataDevolucao().getTime()));
			update.setLong(2, devolucao.getExemplar().getId());
			update.setLong(3, devolucao.getEmprestimo().getId());
			update.execute();
			return this.findById(devolucao.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar devolucao", e);
		}
	}

}
