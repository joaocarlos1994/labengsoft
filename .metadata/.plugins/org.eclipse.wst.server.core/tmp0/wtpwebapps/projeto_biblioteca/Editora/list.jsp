<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fatec:genericpage title="Editora Cadastrados">
	<jsp:attribute name="body">

	<table class="table">
	
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Seguimento</th>
		</tr>
		
		<c:forEach var="editora" items="${listEditora}">
		
		<tr>
			<td> ${editora.id} </td>
			<td> ${editora.nomeEditora} </td>
			<td> ${editora.seguimento} </td>
		</tr>
		
		</c:forEach>
		
	</table>
	
	</jsp:attribute>
</fatec:genericpage>