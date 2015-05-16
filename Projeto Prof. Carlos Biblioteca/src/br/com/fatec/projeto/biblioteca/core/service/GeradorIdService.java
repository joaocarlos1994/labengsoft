package br.com.fatec.projeto.biblioteca.core.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Carlos
 *
 * @version
 */
public class GeradorIdService {

	private static GeradorIdService instance;
	private Long id;
	private Connection connection = null;
	private long idSequence = 0;
	
	private GeradorIdService() {
		this.id = 1L;
	}

	public static GeradorIdService getInstance() {
		if (instance == null) {
			instance = new GeradorIdService();
		}
		return instance;
	}
	
	public synchronized Long getNextId(String tableName){
		try {
			//Iremos cosiderar essa table no SQL que irá retorna o NEXT_ID
			PreparedStatement query = connection.prepareStatement("SELECT NEXT_ID FROM FATEC_IDS WHERE TABLE_NAME = ?;");
			query.setString(1, tableName); //Não se esqueça de fazer o set da tableName
			ResultSet resultSetNextId = query.executeQuery();
			resultSetNextId.next();
			idSequence = resultSetNextId.getLong("NEXT_ID");
			//Agora iremos atualizar o valor do NEXT_ID na Tabela, também utilizando o tableName
			PreparedStatement updateID = connection.prepareStatement("UPDATE FATEC_IDS SET NEXT_ID = ? WHERE TABLE_NAME = ?;");
			updateID.setLong(1, idSequence + 1);
			updateID.setString(1, tableName);
			updateID.execute();
			updateID.close();
			return idSequence;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao pegar o ID para tabela '" + tableName + "'", e);
		}
	}

}
