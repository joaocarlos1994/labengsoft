package br.com.fatec.projeto.biblioteca.api.entity;

public abstract class Pessoa {
	
	private Long id;
	private String nome;
	private String rg;
	
	/**
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return rg
	 */
	public String getRg() {
		return this.rg;
	}

	/**
	 * @param rg
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}

}
