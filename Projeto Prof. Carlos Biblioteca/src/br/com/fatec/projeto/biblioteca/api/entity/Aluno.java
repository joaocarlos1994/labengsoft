package br.com.fatec.projeto.biblioteca.api.entity;


/**
 * @author Carlos
 *
 * @version 1.0.0
 */

public class Aluno extends Pessoa{

	/** */
	public static final String TABLE = "FATEC_ALUNO";
	/** */
	public static final String COL_ID = "ID";
	/** */
	public static final String COL_NOME = "NOME";
	/** */
	public static final String COL_RG= "RG";
	/** */
	public static final String COL_RA = "RA";
	/** */
	

	private String ra;
	
	

	/**
	 * @return ra
	 */
	public String getRa() {
		return this.ra;
	}

	/**
	 * @param ra
	 */
	public void setRa(String ra) {
		this.ra = ra;
	}
	

}
