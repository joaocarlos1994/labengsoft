package br.com.fatec.projeto.biblioteca.test.commons;

import java.sql.Connection;
import java.sql.DriverManager;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.junit.After;
import org.junit.Before;

import br.com.fatec.projeto.biblioteca.core.helper.ConfigDBMapper;

/**
 * @author Carlos
 *
 * @version
 */
public abstract class ConfigDBTestCase {

	@Before
	public void setUp() throws Exception {
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnetion();
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/projeto/biblioteca/liquibase/changelog-master-teste.xml",
				new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");
	}

	@After
	public void setDown() throws Exception {
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnetion();
		conn.prepareStatement("DROP SCHEMA PUBLIC CASCADE;").execute();
		conn.close();
	}
}
