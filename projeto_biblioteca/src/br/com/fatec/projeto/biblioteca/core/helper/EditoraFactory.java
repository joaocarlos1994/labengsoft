package br.com.fatec.projeto.biblioteca.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Editora;

public class EditoraFactory {
	
	public Editora createEditora(Long id, String nomeEditora, String seguimento){
		
		Editora editora = new Editora();
		editora.setId(id);
		editora.setNomeEditora(nomeEditora);
		editora.setSeguimento(seguimento);
		
		return editora;
	}
	
	public Editora createEditora(ResultSet resultado) {
		try {
			return this.createEditora(resultado.getLong(Editora.COL_ID), resultado.getString(Editora.COL_EDITORA),
					resultado.getString(Editora.SEGUIMENTO));
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

	public List<Editora> createEditoras(ResultSet resultado) {
		try {
			List<Editora> editoras = new ArrayList<Editora>();
			while (resultado.next()) {
				editoras.add(this.createEditora(resultado.getLong(Editora.COL_ID), resultado.getString(Editora.COL_EDITORA),
						resultado.getString(Editora.SEGUIMENTO)));
			}
			return editoras;
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}
	
}
