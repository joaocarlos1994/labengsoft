package br.com.fatec.projeto.biblioteca.test.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.test.commons.ConfigDBTestCase;

/**
 * @author Carlos
 *
 * @version
 */
public class AdvancedQueryTest extends ConfigDBTestCase {

	@Test
	public void blah() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "sa", "");
		PreparedStatement insert = conn.prepareStatement("INSERT INTO " + Aluno.TABLE + " VALUES (?,?,?,?);");
		insert.setLong(1, 1L);
		insert.setString(2, "carlos");
		insert.setString(3, "4234524652");
		insert.setString(4, "1000000000");
		
		
		insert.execute();

		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Aluno.TABLE);
		ResultSet resultado = query.executeQuery();
		Assert.assertTrue(resultado.next());
		Assert.assertEquals(1L, resultado.getLong(1));
	}

}
