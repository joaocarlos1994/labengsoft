<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fatec:genericpage title="Livros Cadastrados">
	<jsp:attribute name="body">

	<table class="table">
	
		<tr>
			<th>Id</th>
			<th>T�tulo</th>
			<th>Edi��o</th>
			<th>Publica��o</th>
			<th>Editora</th>
		</tr>
		
		<c:forEach var="livro" items="${listLivro}">
		
		<tr>
			<td> ${livro.id} </td>
			<td> ${livro.titulo} </td>
			<td> ${livro.edicao} </td>
			<td> ${livro.publicacao} </td>
			<td> ${livro.editora.nome} </td>
		</tr>
		
		</c:forEach>
		
	</table>
	
	</jsp:attribute>
</fatec:genericpage>