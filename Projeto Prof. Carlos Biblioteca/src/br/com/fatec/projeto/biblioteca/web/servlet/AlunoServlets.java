package br.com.fatec.projeto.biblioteca.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlunoServlets extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		String nome, ra, rg;
		
		nome = req.getParameter("nome");
		rg = req.getParameter("rg"); 
		ra = req.getParameter("ra"); 
		
		out.println("<p>Nome: " + nome + "</p>");
		out.println("<p>RG: " + rg + "</p>");
		out.println("<p>RA: " + ra + "</p>");
		
		//new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date());
		
		out.println("</body>");
		out.println("</html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		
	}
	
}
