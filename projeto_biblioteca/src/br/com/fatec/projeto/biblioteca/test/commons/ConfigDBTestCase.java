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
import br.com.fatec.projeto.biblioteca.core.helper.LiquibaseRunner;

/**
 * @author Carlos
 *
 * @version
 */
public abstract class ConfigDBTestCase {

	@Before
	public void setUp() throws Exception {
		LiquibaseRunner.runnerLiquibase("br/com/fatec/projeto/biblioteca/liquibase/changelog-master-teste.xml");
	}

	@After
	public void setDown() throws Exception {
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnetion();
		conn.prepareStatement("DROP SCHEMA PUBLIC CASCADE;").execute();
		conn.close();
	}
}
