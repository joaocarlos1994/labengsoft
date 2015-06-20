<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fatec:genericpage title="Alunos Cadastrados">
	<jsp:attribute name="body">

	<table class="table">
	
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>RG</th>
			<th>RA</th>
		</tr>
		
		<c:forEach var="aluno" items="${listAluno}"></c:forEach>
		
		<tr>
			<td> ${aluno.id} </td>
			<td> ${aluno.nome} </td>
			<td> ${aluno.rg} </td>
			<td> ${aluno.ra} </td>
		</tr>
		
	</table>
	
	</jsp:attribute>
</fatec:genericpage>