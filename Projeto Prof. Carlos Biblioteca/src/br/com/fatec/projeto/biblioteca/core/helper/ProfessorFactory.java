package br.com.fatec.projeto.biblioteca.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Professor;

public class ProfessorFactory {

	public Professor createProfessor(Long id, String nome, String rg,
			String registro) {

		Professor professor = new Professor();
		professor.setId(id);
		professor.setNome(nome);
		professor.setRg(rg);
		professor.setRegistro(registro);

		return professor;
	}
	
	//        carlos.olribeiro@gmail.com

	public Professor createProfessor(ResultSet resultado) {
		try {
			return this.createProfessor(resultado.getLong(Professor.COL_ID),
					resultado.getString(Professor.COL_NOME),
					resultado.getString(Professor.COL_RG),
					resultado.getString(Professor.COL_REGISTRO));
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado", e);
		}
	}

	public List<Professor> createProfessores(ResultSet resultado) {
		try {
			List<Professor> professor = new ArrayList<Professor>();
			while (resultado.next()) {
				professor.add(this.createProfessor(
						resultado.getLong(Professor.COL_ID),
						resultado.getString(Professor.COL_NOME),
						resultado.getString(Professor.COL_RG),
						resultado.getString(Professor.COL_REGISTRO)));
			}
			return professor;
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

}
