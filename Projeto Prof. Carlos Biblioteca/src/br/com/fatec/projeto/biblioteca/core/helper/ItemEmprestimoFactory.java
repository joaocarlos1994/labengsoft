package br.com.fatec.projeto.biblioteca.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;
import br.com.fatec.projeto.biblioteca.api.entity.Exemplar;
import br.com.fatec.projeto.biblioteca.api.entity.ItemEmprestimo;
import br.com.fatec.projeto.biblioteca.api.service.EmprestimoDAO;
import br.com.fatec.projeto.biblioteca.api.service.ExemplarDAO;

public class ItemEmprestimoFactory {
	
	private EmprestimoDAO emprestimoDAO;
	private ExemplarDAO exemplarDAO;
	
public ItemEmprestimo createItemEmprestimo(Long id, Emprestimo emprestimo, Exemplar exemplar){
		
		ItemEmprestimo itemEmprestimo = new ItemEmprestimo();
		itemEmprestimo.setId(id);
		itemEmprestimo.setEmprestimo(emprestimo);
		itemEmprestimo.setExemplar(exemplar);
		
		return itemEmprestimo;
	}

public ItemEmprestimo createItemEmprestimo(Long id, long emprestimoId, long exemplarId){
	
	ItemEmprestimo itemEmprestimo = new ItemEmprestimo();
	itemEmprestimo.setId(id);
	itemEmprestimo.setEmprestimo(emprestimoDAO.findById(emprestimoId));
	itemEmprestimo.setExemplar(exemplarDAO.findById(exemplarId));
	
	return itemEmprestimo;
}
	
	public ItemEmprestimo createItemEmprestimo(ResultSet resultado) {
		try {
			return this.createItemEmprestimo(resultado.getLong(ItemEmprestimo.COL_ID), resultado.getLong(ItemEmprestimo.COL_EMPRESTIMO),
					resultado.getLong(ItemEmprestimo.COL_EXEMPLAR));
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

	public List<ItemEmprestimo> createItemEmprestimos(ResultSet resultado) {
		try {
			List<ItemEmprestimo> itemEmprestimos = new ArrayList<ItemEmprestimo>();
			while (resultado.next()) {
				itemEmprestimos.add(this.createItemEmprestimo(resultado.getLong(ItemEmprestimo.COL_ID), resultado.getLong(ItemEmprestimo.COL_EMPRESTIMO),
						resultado.getLong(ItemEmprestimo.COL_EXEMPLAR)));
			}
			return itemEmprestimos;
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

}
