package br.com.fatec.projeto.biblioteca.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import liquibase.exception.LiquibaseException;
import br.com.fatec.projeto.biblioteca.core.helper.ConfigDBMapper;
import br.com.fatec.projeto.biblioteca.core.helper.LiquibaseRunner;

public class ConfigDBListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent arg0){
		try {
			ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
			LiquibaseRunner.runnerLiquibase("br/com/fatec/projeto/biblioteca/liquibase/changelog-master-prod.xml");
		} catch (LiquibaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0){
		//Não vamos fazer nada neste evento, mas a inteface nos obriga a sobrescrevê-lo
	}
}
