package br.com.fatec.projeto.biblioteca.api.service;

import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Livro;

public interface LivroDAO {
	
	public Livro save(Livro livro);

	public boolean remove(Livro livro);

	public List<Livro> findAll();

	public Livro findById(Long id);

	public Livro update(Livro livro);

}
