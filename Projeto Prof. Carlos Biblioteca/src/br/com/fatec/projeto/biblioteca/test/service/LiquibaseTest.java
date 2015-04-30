package br.com.fatec.projeto.biblioteca.test.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;

/**
 * @author Carlos
 *
 * @version
 */
public class LiquibaseTest {

	@Test
	public void criarBancoTest() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/projeto/biblioteca/liquibase/changelog-master.xml",
				new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");

		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Aluno.TABLE);
		ResultSet resultado = query.executeQuery();
		Assert.assertTrue(!resultado.next());
	}

	@Test
	public void insertSelectTest() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/projeto/biblioteca/liquibase/changelog-master.xml",
				new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");

		PreparedStatement insert = conn.prepareStatement("INSERT INTO " + Aluno.TABLE
				+ " VALUES (1, null, null, null);");
		insert.execute();

		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Aluno.TABLE);
		ResultSet resultado = query.executeQuery();

		Assert.assertTrue(resultado.next());
		Assert.assertEquals(1L, resultado.getLong("ID"));
		Assert.assertEquals(1L, resultado.getLong(1));
	}
}
