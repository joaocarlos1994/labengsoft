package br.com.fatec.projeto.biblioteca.web.listener;

public class ConfigDBListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent arg0){
		ConfigDBListener.getInstance().setDefaultConnetionName("fatec");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0){
		//Não vamos fazer nada neste evento, mas a inteface nos obriga a sobrescrevê-lo
	}
}
