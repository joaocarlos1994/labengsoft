package br.com.fatec.projeto.biblioteca.api.service;

import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;

public interface EmprestimoDAO {
	
	public Emprestimo save(Emprestimo emprestimo);

	public boolean remove(Emprestimo emprestimo);

	public List<Emprestimo> findAll();

	public Emprestimo findById(Long id);

	public Emprestimo update(Emprestimo emprestimo);

}
