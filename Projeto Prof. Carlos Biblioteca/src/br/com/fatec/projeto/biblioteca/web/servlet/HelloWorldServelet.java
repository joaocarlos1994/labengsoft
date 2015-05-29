package br.com.fatec.projeto.biblioteca.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.SimpleAttributeSet;

import br.com.fatec.projeto.biblioteca.api.entity.Aluno;
import br.com.fatec.projeto.biblioteca.core.helper.AlunoFactory;


public class HelloWorldServelet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Aluno> alunos;
	private AlunoFactory alunoFactory = new AlunoFactory();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
	
	alunos = new ArrayList<Aluno>();
	
	
	PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		
		alunos.add(alunoFactory.createAluno(null, "Carlos", "1111", "222"));
		alunos.add(alunoFactory.createAluno(null, "Janaira", "3333", "444"));
		alunos.add(alunoFactory.createAluno(null, "Fabiana", "555", "666"));
		alunos.add(alunoFactory.createAluno(null, "Janaira Negritude", "3333", "444"));
		
		/*double num1 = Double.parseDouble(req.getParameter("numero1")); 
		double num2 = Double.parseDouble(req.getParameter("numero2"));
		double soma = 0, subtracao = 0;
		String operacao = req.getParameter("operacao");
		
		
		if(operacao.equals("+")){
			soma = num1 + num2;
			out.println("<p>Soma: " + soma + "</p>");
		}else { 
			subtracao = num1 - num2;
			out.println("<p>Subtração: " + subtracao + "</p>");
		}*/
		
		
		req.setAttribute("alunos", alunos);
		req.getRequestDispatcher("/listarFiltros.jsp").forward(req, resp);
		
		//new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date());
		
		out.println("</body>");
		out.println("</html>");
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		
	}

}
