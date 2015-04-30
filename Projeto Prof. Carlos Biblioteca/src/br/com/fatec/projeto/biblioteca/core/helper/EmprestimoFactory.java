package br.com.fatec.projeto.biblioteca.core.helper;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;
import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;
import br.com.fatec.projeto.biblioteca.api.entity.Pessoa;
import br.com.fatec.projeto.biblioteca.api.service.AlunoDAO;

public class EmprestimoFactory {
	
	private AlunoDAO alunoDAO;
	
	public  Emprestimo createEmprestimo(Long id, Pessoa pessoa, Date dataEmprestimo, 
			Date dataEntrega){
		
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setId(id);
		emprestimo.setPessoa(pessoa);
		emprestimo.setDataEmprestimo(dataEmprestimo);
		emprestimo.setDataEntrega(dataEntrega);
		
		return emprestimo;
	}
	
	public Emprestimo createEmprestimo(Long id, long pessoaId, Date dataEmprestimo, 
			Date dataEntrega){
		
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setId(id);
		emprestimo.setPessoa(alunoDAO.findById(pessoaId));
		emprestimo.setDataEmprestimo(dataEmprestimo);
		emprestimo.setDataEntrega(dataEntrega);
		
		return emprestimo;
	}
	
	public Emprestimo criarEmprestimo(ResultSet resultado) {
		try {
			return this.createEmprestimo(resultado.getLong(Emprestimo.COL_ID), resultado.getLong(Emprestimo.COL_PESSOA),
					resultado.getDate(Emprestimo.COL_EMPRESTIMO), resultado.getDate(Emprestimo.COL_ENTREGA));
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

	public List<Emprestimo> createEmprestimos(ResultSet resultado) {
		try {
			List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			while (resultado.next()) {
				emprestimos.add(this.createEmprestimo(resultado.getLong(Emprestimo.COL_ID), resultado.getLong(Emprestimo.COL_PESSOA),
						resultado.getDate(Emprestimo.COL_EMPRESTIMO), resultado.getDate(Emprestimo.COL_ENTREGA)));
			}
			return emprestimos;
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

}
