<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fatec:genericpage title="Professores Cadastrados">
	<jsp:attribute name="body">

	<table class="table">
	
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>RG</th>
			<th>RA</th>
		</tr>
		
		<c:forEach var="professor" items="${listProfessor}">
		
		<tr>
			<td> ${professor.id} </td>
			<td> ${professor.nome} </td>
			<td> ${professor.rg} </td>
			<td> ${professor.registro} </td>
		</tr>
		
		</c:forEach>
		
	</table>
	
	</jsp:attribute>
</fatec:genericpage>