package br.com.fatec.projeto.biblioteca.core.helper;

import java.sql.Connection;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

public class LiquibaseRunner {
	
	public static void runnerLiquibase (String url) throws LiquibaseException{
		
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnetion();
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase(url,
				new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");
		
	}
	

}