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

/**
 * @author Carlos
 *
 * @version
 */
public abstract class ConfigDBTestCase {

	@Before
	public void setUp() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/projeto/biblioteca/liquibase/changelog-master.xml",
				new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");
	}

	@After
	public void setDown() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		conn.prepareStatement("DROP SCHEMA PUBLIC CASCADE;").execute();
		conn.close();
	}
}
