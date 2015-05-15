package br.com.fatec.projeto.biblioteca.web.listener;

import br.com.fatec.projeto.biblioteca.core.helper.ConfigDBMapper;


public class ConfigDBServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		ConfigDBMapper.getInstance().setDefaultConnetionName("fatec");
	}

}
