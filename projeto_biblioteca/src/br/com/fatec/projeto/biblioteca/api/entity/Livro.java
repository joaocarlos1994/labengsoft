package br.com.fatec.projeto.biblioteca.api.entity;

import java.util.Date;

public class Livro {
	
	public static final String TABLE = "FATEC_LIVRO";
	/** */
	public static final String COL_ID = "ID";
	/** */
	public static final String COL_TITULO = "TITULO";
	/** */
	public static final String COL_EDICAO = "EDICAO";
	/** */
	public static final String COL_ANO_PUBLICACAO = "PUBLICACAO";
	/** */
	public static final String COL_EDITORA = "EDITORA_ID";
	/** */
	
	private Long id;
	private String titulo;
	private long edicao;
	private Date anoPublicacao;
	private Editora editora;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public long getEdicao() {
		return edicao;
	}
	public void setEdicao(long edicao) {
		this.edicao = edicao;
	}
	public Date getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(Date anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	
}
