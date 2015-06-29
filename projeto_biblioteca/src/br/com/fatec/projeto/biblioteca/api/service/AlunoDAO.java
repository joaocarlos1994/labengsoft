package br.com.fatec.projeto.biblioteca.api.service;

import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;

/**
 * @author Carlos
 *
 * @version 1.0.1
 */
public interface AlunoDAO {

	public Aluno save(Aluno aluno);

	public boolean remove(Aluno aluno);

	public List<Aluno> findAll();

	public Aluno findById(Long id);

	public Aluno update(Aluno aluno);

}
