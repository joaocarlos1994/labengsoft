package br.com.fatec.projeto.biblioteca.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fatec.projeto.biblioteca.api.entity.Devolucao;
import br.com.fatec.projeto.biblioteca.api.entity.Emprestimo;
import br.com.fatec.projeto.biblioteca.api.entity.Exemplar;
import br.com.fatec.projeto.biblioteca.api.service.DevolucaoDAO;
import br.com.fatec.projeto.biblioteca.api.service.EmprestimoDAO;
import br.com.fatec.projeto.biblioteca.api.service.ExemplarDAO;
import br.com.fatec.projeto.biblioteca.core.impl.DevolucaoDAOImpl;

public class DevolucaoFactory {
	
	private ExemplarDAO exemplarDAO;
	private EmprestimoDAO emprestimoDAO;

	public Devolucao createDevolucao(Long id, Date dataDevolucao,
			Exemplar exemplar, Emprestimo emprestimo) {

		Devolucao devolucao = new Devolucao();
		devolucao.setId(id);
		devolucao.setDataDevolucao(dataDevolucao);
		devolucao.setExemplar(exemplar);
		devolucao.setEmprestimo(emprestimo);

		return devolucao;
	}
	
	public Devolucao createDevolucao(Long id, Date dataDevolucao,
			long exemplarId, long emprestimoId) {

		Devolucao devolucao = new Devolucao();
		devolucao.setId(id);
		devolucao.setDataDevolucao(dataDevolucao);
		devolucao.setExemplar(exemplarDAO.findById(emprestimoId));
		devolucao.setEmprestimo(emprestimoDAO.findById(emprestimoId));

		return devolucao;
	}

	public Devolucao createDevolucao(ResultSet resultado) {
		try {
			return this.createDevolucao(resultado.getLong(Devolucao.COL_ID),
					resultado.getDate(Devolucao.COL_DATA_DEVOLUCAO),
					resultado.getLong(Devolucao.COL_EXEMPLAR),
					resultado.getLong(Devolucao.COL_EMPRESTIMO));
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

	public List<Devolucao> createDevolucaos(ResultSet resultado) {
		try {
			List<Devolucao> devolucao = new ArrayList<Devolucao>();
			while (resultado.next()) {
				devolucao.add(this.createDevolucao(resultado.getLong(Devolucao.COL_ID),
						resultado.getDate(Devolucao.COL_DATA_DEVOLUCAO),
						resultado.getLong(Devolucao.COL_EXEMPLAR),
						resultado.getLong(Devolucao.COL_EMPRESTIMO)));
			}
			return devolucao;
		} catch (SQLException e) {
			throw new RuntimeException("resultado não inicializado");
		}
	}

}
