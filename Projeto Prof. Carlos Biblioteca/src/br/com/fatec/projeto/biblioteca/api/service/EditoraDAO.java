package br.com.fatec.projeto.biblioteca.api.service;

import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Editora;;

public interface EditoraDAO {
	
	public Editora save(Editora editora);

	public boolean remove(Editora editora);

	public List<Editora> findAll();

	public Editora findById(Long id);

	public Editora update(Editora editora);

}
