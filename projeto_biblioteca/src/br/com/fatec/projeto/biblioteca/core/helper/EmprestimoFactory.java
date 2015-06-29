package br.com.fatec.projeto.biblioteca.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;
import br.com.fatec.projeto.biblioteca.api.entity.Pessoa;
import br.com.fatec.projeto.biblioteca.core.impl.AlunoDAOImpl;
import br.com.fatec.projeto.biblioteca.core.impl.ProfessorDAOImpl;

public class EmprestimoFactory {
	
	private AlunoDAOImpl alunoDAOImpl;
	private ProfessorDAOImpl professorDAOImpl;
	
	public EmprestimoFactory(){
		alunoDAOImpl = new AlunoDAOImpl();
		professorDAOImpl = new ProfessorDAOImpl();
	}
	
	public  Emprestimo createEmprestimo(Long id, Pessoa pessoa, Date dataEmprestimo, 
			Date dataEntrega){
		
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setId(id);
		emprestimo.setPessoa(pessoa);
		emprestimo.setDataEmprestimo(dataEmprestimo);
		emprestimo.setDataEntrega(dataEntrega);
		
		return emprestimo;
	}
	
	public Emprestimo createEmprestimo(Long id,Date dataEmprestimo, Date dataEntrega,
			Long alunoId, Long professorId){
		
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setId(id);
		
		if (alunoId != null && alunoId != 0){
			emprestimo.setPessoa(alunoDAOImpl.findById(alunoId));
		}else {
			emprestimo.setPessoa(professorDAOImpl.findById(professorId));
		}
		
		emprestimo.setDataEmprestimo(dataEmprestimo);
		emprestimo.setDataEntrega(dataEntrega);
		
		return emprestimo;
	}
	
	public Emprestimo criarEmprestimo(ResultSet resultado) {
		try {
			return this.createEmprestimo(resultado.getLong(Emprestimo.COL_ID), resultado.getDate(Emprestimo.COL_EMPRESTIMO), 
					resultado.getDate(Emprestimo.COL_ENTREGA), resultado.getLong(Emprestimo.ALUNO_ID), resultado.getLong(Emprestimo.PROFESSOR_ID));
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado", e);
		}
	}

	public List<Emprestimo> createEmprestimos(ResultSet resultado) {
		try {
			List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			while (resultado.next()) {
				emprestimos.add(this.createEmprestimo(resultado.getLong(Emprestimo.COL_ID), resultado.getDate(Emprestimo.COL_EMPRESTIMO), 
						resultado.getDate(Emprestimo.COL_ENTREGA), resultado.getLong(Emprestimo.ALUNO_ID), resultado.getLong(Emprestimo.PROFESSOR_ID)));
			}
			return emprestimos;
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

}
