package br.com.fatec.projeto.biblioteca.api.entity;

public class Exemplar {
	
	public static final String TABLE = "FATEC_EXEMPLAR";
	/** */
	public static final String COL_ID = "ID";
	/** */
	public static final String COL_LIVRO = "LIVRO_ID";
	/** */
	public static final String COL_EXEMPLAR = "NUMERO_EXEMPLAR";
	
	private Long id;
	private Livro livro;
	private long codigoExemplar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public long getCodigoExemplar() {
		return codigoExemplar;
	}
	public void setCodigoExemplar(long codigoExemplar) {
		this.codigoExemplar = codigoExemplar;
	}
	
	
}
