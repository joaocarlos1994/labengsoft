package br.com.fatec.projeto.biblioteca.web.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;
import br.com.fatec.projeto.biblioteca.api.entity.Pessoa;
import br.com.fatec.projeto.biblioteca.api.entity.Professor;
import br.com.fatec.projeto.biblioteca.core.helper.EmprestimoFactory;
import br.com.fatec.projeto.biblioteca.core.impl.EmprestimoDAOImpl;

@Getter
@Setter
public class EmprestimoAction {
	
	private static final long serialVersionUID = 1L;

	private EmprestimoDAOImpl emprestimoDAoImpl;
	private EmprestimoFactory emprestimoFactory;

	private Long id;
	private Aluno aluno;
	private Professor professor;
	private Date dataEmprestimo;
	private Date dataEntrega;
	private List<Emprestimo> listEmprestimo;
	
	public String listEmprestimos() {

		emprestimoDAoImpl = new EmprestimoDAOImpl();

		listEmprestimo = new ArrayList<Emprestimo>();
		listEmprestimo = emprestimoDAoImpl.findAll();

		return "list";
	}

	public String registerEmprestimo() {

		emprestimoFactory = new EmprestimoFactory();
		emprestimoDAoImpl = new EmprestimoDAOImpl();

		Emprestimo emprestimo = emprestimoFactory.createEmprestimo(id, dataEmprestimo, dataEntrega, aluno.getId(), professor.getId());
		emprestimoDAoImpl.save(emprestimo);

		return this.listEmprestimos();
	}

	public String form() {
		return "form";
	}

}
