package br.com.fatec.projeto.biblioteca.test.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

import br.com.fatec.projeto.biblioteca.core.helper.ConfigDBMapper;

public class ConfigCenarioTestCase extends ConfigDBTestCase {

	@Before
	public void upCenario() throws Exception {
		Connection jdbcConnection = ConfigDBMapper.getInstance().getDefaultConnetion();
		IDatabaseConnection conn = new DatabaseConnection(jdbcConnection);

		ClassLoader classLoader = this.getClass().getClassLoader();

		FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder()
				.build(classLoader
						.getResource("br/com/fatec/projeto/biblioteca/test/resources/cenarios/basico.xml"));

		DatabaseOperation.CLEAN_INSERT.execute(conn, dataSet);
		
	}

}
