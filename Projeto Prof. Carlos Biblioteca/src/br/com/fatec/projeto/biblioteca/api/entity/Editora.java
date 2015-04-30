package br.com.fatec.projeto.biblioteca.api.entity;

public class Editora {
	
	public static final String TABLE = "EDITORA";
	/** */
	public static final String COL_ID = "ID";
	/** */
	public static final String COL_EDITORA = "NOME";
	/** */
	public static final String SEGUIMENTO = "SEGUIMENTO";
	/** */
	
	private Long id;
	private String nomeEditora;
	private String seguimento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeEditora() {
		return nomeEditora;
	}
	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}
	public String getSeguimento() {
		return seguimento;
	}
	public void setSeguimento(String seguimento) {
		this.seguimento = seguimento;
	}
	
	
	
}
