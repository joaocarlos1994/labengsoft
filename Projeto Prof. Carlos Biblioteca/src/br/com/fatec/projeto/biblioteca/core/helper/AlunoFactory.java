package br.com.fatec.projeto.biblioteca.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;

public class AlunoFactory {
	
	public Aluno createAluno(Long id, String nome, String rg, String ra){
		
		Aluno aluno = new Aluno();
		aluno.setId(id);
		aluno.setNome(nome);
		aluno.setRg(rg);
		aluno.setRa(ra);
		
		return aluno;
	}
	
	public Aluno criarAluno(ResultSet resultado) {
		try {
			return this.createAluno(resultado.getLong(Aluno.COL_ID), resultado.getString(Aluno.COL_NOME),
					resultado.getString(Aluno.COL_RG), resultado.getString(Aluno.COL_RA));
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

	public List<Aluno> createAlunos(ResultSet resultado) {
		try {
			List<Aluno> alunos = new ArrayList<Aluno>();
			while (resultado.next()) {
				alunos.add(this.createAluno(resultado.getLong(Aluno.COL_ID), resultado.getString(Aluno.COL_NOME),
						resultado.getString(Aluno.COL_RG), resultado.getString(Aluno.COL_RA)));
			}
			return alunos;
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

}
