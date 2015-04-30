package br.com.fatec.projeto.biblioteca.api.service;

import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Exemplar;

public interface ExemplarDAO {
	
	public Exemplar save(Exemplar exemplar);

	public boolean remove(Exemplar exemplar);

	public List<Exemplar> findAll();

	public Exemplar findById(Long id);

	public Exemplar update(Exemplar exemplar);

}
