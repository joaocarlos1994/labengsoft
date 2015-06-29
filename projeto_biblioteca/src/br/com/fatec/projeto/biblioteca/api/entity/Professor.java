package br.com.fatec.projeto.biblioteca.api.entity;

import java.util.Date;

public class Professor extends Pessoa{
	
	public static final String TABLE = "FATEC_PROFESSOR";
	/** */
	public static final String COL_ID = "ID";
	/** */
	public static final String COL_NOME = "NOME";
	/** */
	public static final String COL_REGISTRO = "REGISTRO";
	/** */
	public static final String COL_RG = "RG";
	/** */
	
	private String registro;
	
	
	public String getRegistro() {
		return registro;
	}
	
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	
	
}
