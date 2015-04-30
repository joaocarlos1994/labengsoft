package br.com.fatec.projeto.biblioteca.core.service;

/**
 * @author Carlos
 *
 * @version
 */
public class GeradorIdService {

	private static GeradorIdService instance;
	private Long id;

	private GeradorIdService() {
		this.id = 1L;
	}

	public static GeradorIdService getInstance() {
		if (instance == null) {
			instance = new GeradorIdService();
		}
		return instance;
	}

	public Long nextId() {
		return this.id++;
	}

}
