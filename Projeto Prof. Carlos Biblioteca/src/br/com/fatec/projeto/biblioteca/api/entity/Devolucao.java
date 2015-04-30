package br.com.fatec.projeto.biblioteca.api.entity;

import java.util.Date;

public class Devolucao {
	
	/** */
	public static final String TABLE = "FATEC_DEVOLUCAO";
	/** */
	public static final String COL_ID = "ID";
	/** */
	public static final String COL_DATA_DEVOLUCAO = "DEVOLUCAO";
	/** */
	public static final String COL_EXEMPLAR= "EXEMPLAR";
	/** */
	public static final String COL_EMPRESTIMO = "EMPRESTIMO";
	/** */
	
	private Long id;
	private Date dataDevolucao;
	private Exemplar exemplar;
	private Emprestimo emprestimo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public Exemplar getExemplar() {
		return exemplar;
	}
	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
	
	

}
