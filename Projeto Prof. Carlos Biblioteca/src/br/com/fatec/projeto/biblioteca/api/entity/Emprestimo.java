package br.com.fatec.projeto.biblioteca.api.entity;

import java.util.Date;

public class Emprestimo {
	
	public static final String TABLE = "FATEC_EMPRESTIMO";
	/** */
	public static final String COL_ID = "ID";
	/** */
	public static final String COL_PESSOA = "PESSOA";
	/** */
	public static final String COL_EMPRESTIMO = "EMPRESTIMO";
	/** */
	public static final String COL_ENTREGA = "ENTREGA";
	/** */
	
	private Long id;
	private Pessoa pessoa;
	private Date dataEmprestimo;
	private Date dataEntrega;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	
}
