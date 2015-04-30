package br.com.fatec.projeto.biblioteca.enums;

public enum TipoPessoa {
	
	
	PROFESSOR(1), ALUNO(2);
	
	private int type;
	
	private TipoPessoa(int type){
		this.type = type;
	}
	

}
