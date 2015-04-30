package br.com.fatec.projeto.biblioteca.api.entity;

public class ItemEmprestimo {
	
	/** */
	public static final String TABLE = "FATEC_ITEM_EMPRESTIMO";
	/** */
	public static final String COL_ID = "ID";
	/** */
	public static final String COL_EMPRESTIMO = "EMPRESTIMO";
	/** */
	public static final String COL_EXEMPLAR = "EXEMPLAR";
	/** */
	public static final String COL_RA = "RA";
	
	private Long id;
	private Emprestimo emprestimo;
	private Exemplar exemplar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
	public Exemplar getExemplar() {
		return exemplar;
	}
	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}
	
	

}
