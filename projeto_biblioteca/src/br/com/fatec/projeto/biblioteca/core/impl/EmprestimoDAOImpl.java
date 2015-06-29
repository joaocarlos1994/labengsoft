package br.com.fatec.projeto.biblioteca.core.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;
import br.com.fatec.projeto.biblioteca.api.service.EmprestimoDAO;
import br.com.fatec.projeto.biblioteca.core.helper.ConfigDBMapper;
import br.com.fatec.projeto.biblioteca.core.helper.EmprestimoFactory;
import br.com.fatec.projeto.biblioteca.core.service.GeradorIdService;

public class EmprestimoDAOImpl implements EmprestimoDAO{
	
	private Connection connection;
	private EmprestimoFactory emprestimoFactory;

	public EmprestimoDAOImpl() {
		this.emprestimoFactory = new EmprestimoFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnetion();
	}

	@Override
	public Emprestimo save(Emprestimo emprestimo) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Emprestimo.TABLE + " VALUES (?,?,?,?, ?);");
			Long id = GeradorIdService.getInstance().getNextId(Emprestimo.TABLE);
			insert.setLong(1, id);
			insert.setDate(2, new Date(emprestimo.getDataEmprestimo().getTime()));
			insert.setDate(3, new Date(emprestimo.getDataEntrega().getTime()));
			
			if (emprestimo.getPessoa() instanceof Aluno){
				insert.setLong(4, emprestimo.getPessoa().getId());
				insert.setNull(5, Types.BIGINT);
			}else {
				
				insert.setNull(5, Types.BIGINT);
				insert.setLong(5, emprestimo.getPessoa().getId());
				
			}
			
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar emprestimo", e);
		}
	}

	@Override
	public boolean remove(Emprestimo emprestimo) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Emprestimo.TABLE + " WHERE " + Emprestimo.COL_ID + " = ?;");
			remove.setLong(1, emprestimo.getId());
			return remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("erro ao remover emprestimo com id: " + emprestimo.getId(), e);
		}
	}

	@Override
	public List<Emprestimo> findAll() {
		PreparedStatement query = null;
		List<Emprestimo> emprestimos = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Emprestimo.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			emprestimos = this.emprestimoFactory.createEmprestimos(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}

		return emprestimos;
	}

	@Override
	public Emprestimo findById(Long id) {
		PreparedStatement query = null;
		Emprestimo emprestimo = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Emprestimo.TABLE + " WHERE " + Emprestimo.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				emprestimo = this.emprestimoFactory.criarEmprestimo(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " est� duplicado no sistema");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}
		return emprestimo;
	}

	@Override
	public Emprestimo update(Emprestimo emprestimo) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Emprestimo.TABLE + " SET " + Emprestimo.COL_EMPRESTIMO + " = ?, "
					+ Emprestimo.COL_ENTREGA + " = ?;");
			update.setDate(1, new Date(emprestimo.getDataEmprestimo().getTime()));
			update.setDate(2, new Date(emprestimo.getDataEntrega().getTime()));
			update.execute();
			return this.findById(emprestimo.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar emprestimo", e);
		}
	}
	
}
