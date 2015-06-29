package br.com.fatec.projeto.biblioteca.api.service;

import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.ItemEmprestimo;

public interface ItemEmprestimoDAO {
	
	public ItemEmprestimo save(ItemEmprestimo itemEmprestimo);

	public boolean remove(ItemEmprestimo itemEmprestimo);

	public List<ItemEmprestimo> findAll();

	public ItemEmprestimo findById(Long id);

	public ItemEmprestimo update(ItemEmprestimo itemEmprestimo);

}
