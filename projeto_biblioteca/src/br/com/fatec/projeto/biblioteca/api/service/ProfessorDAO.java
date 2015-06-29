package br.com.fatec.projeto.biblioteca.api.service;

import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Professor;

public interface ProfessorDAO {

	public Professor save(Professor professor);

	public boolean remove(Professor professor);

	public List<Professor> findAll();

	public Professor findById(Long id);

	public Professor update(Professor professor);

}
