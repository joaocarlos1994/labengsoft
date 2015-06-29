package br.com.fatec.projeto.biblioteca.api.service;

import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Devolucao;

public interface DevolucaoDAO {
	
	public Devolucao save(Devolucao devolucao);

	public boolean remove(Devolucao devolucao);

	public List<Devolucao> findAll();

	public Devolucao findById(Long id);

	public Devolucao update(Devolucao devolucao);

}
