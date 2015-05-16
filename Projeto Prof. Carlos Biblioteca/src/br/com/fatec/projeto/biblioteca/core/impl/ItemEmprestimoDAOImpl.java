package br.com.fatec.projeto.biblioteca.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.entity.ItemEmprestimo;
import br.com.fatec.projeto.biblioteca.api.service.ItemEmprestimoDAO;
import br.com.fatec.projeto.biblioteca.core.helper.ItemEmprestimoFactory;
import br.com.fatec.projeto.biblioteca.core.service.GeradorIdService;

public class ItemEmprestimoDAOImpl implements ItemEmprestimoDAO{
	
	private Connection connection;
	private ItemEmprestimoFactory itemEmprestimoDAOFactory;
	
	public ItemEmprestimoDAOImpl() {
		this.itemEmprestimoDAOFactory = new ItemEmprestimoFactory();
		try {
			this.connection = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		} catch (SQLException e) {
			throw new RuntimeException("erro ao gerar conexão", e);
		}
	}
	
	@Override
	public ItemEmprestimo save(ItemEmprestimo itemEmprestimoDAO) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + ItemEmprestimo.TABLE + " VALUES (?,?,?);");
			Long id = GeradorIdService.getInstance().getNextId(ItemEmprestimo.TABLE);
			insert.setLong(1, id);
			insert.setLong(2, itemEmprestimoDAO.getEmprestimo().getId());
			insert.setLong(3, itemEmprestimoDAO.getExemplar().getId());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar itemEmprestimoDAO", e);
		}
	}

	@Override
	public boolean remove(ItemEmprestimo itemEmprestimoDAO) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + ItemEmprestimo.TABLE + " WHERE " + ItemEmprestimo.COL_ID + " = ?;");
			remove.setLong(1, itemEmprestimoDAO.getId());
			return remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("erro ao remover aluno com id: " + itemEmprestimoDAO.getId(), e);
		}
	}

	@Override
	public List<ItemEmprestimo> findAll() {
		PreparedStatement query = null;
		List<ItemEmprestimo> itemEmprestimoDAO = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + ItemEmprestimo.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			itemEmprestimoDAO = this.itemEmprestimoDAOFactory.createItemEmprestimos(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("resultado n�o inicializado", e);
		}

		return itemEmprestimoDAO;
	}

	@Override
	public ItemEmprestimo findById(Long id) {
		PreparedStatement query = null;
		ItemEmprestimo itemEmprestimoDAO = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + ItemEmprestimo.TABLE + " WHERE " + ItemEmprestimo.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				itemEmprestimoDAO = this.itemEmprestimoDAOFactory.createItemEmprestimo(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado no sistema");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("resultado nãoo inicializado", e);
		}
		return itemEmprestimoDAO;
	}

	@Override
	public ItemEmprestimo update(ItemEmprestimo itemEmprestimoDAO) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + ItemEmprestimo.TABLE + " SET " + ItemEmprestimo.COL_EMPRESTIMO + " = ?, "
					+ ItemEmprestimo.COL_EXEMPLAR + " = ?;");
			update.setLong(1, itemEmprestimoDAO.getEmprestimo().getId());
			update.setLong(2, itemEmprestimoDAO.getExemplar().getId());
			update.execute();
			return this.findById(itemEmprestimoDAO.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao salvar itemEmprestimoDAO", e);
		}
	}

}
