package br.com.fatec.projeto.biblioteca.test.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

/**
 * @author Carlos
 *
 * @version
 */
public class QueryControlledTest extends ConfigDBTestCase {

	@Test
	public void insert() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		PreparedStatement insert = conn.prepareStatement("INSERT INTO FATEC_ALUNO VALUES(1, null, null, null);");
		insert.execute();
		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Aluno.TABLE);
		Assert.assertTrue(query.executeQuery().next());
	}

	@Test
	public void query() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Aluno.TABLE);
		ResultSet resultado = query.executeQuery();

		Assert.assertFalse(resultado.next());
	}

}
