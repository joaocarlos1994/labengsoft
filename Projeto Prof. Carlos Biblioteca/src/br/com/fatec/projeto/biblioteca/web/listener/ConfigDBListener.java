package br.com.fatec.projeto.biblioteca.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.fatec.projeto.biblioteca.core.helper.ConfigDBMapper;

public class ConfigDBListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent arg0){
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0){
		//Não vamos fazer nada neste evento, mas a inteface nos obriga a sobrescrevê-lo
	}
}
