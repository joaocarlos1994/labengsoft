package br.com.fatec.projeto.biblioteca.web.listener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import br.com.fatec.projeto.biblioteca.core.helper.ConfigDBMapper;


public class ConfigDBServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
	}

}
