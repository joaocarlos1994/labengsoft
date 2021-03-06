<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fatec:genericpage title="Emprestimos Cadastrados">
	<jsp:attribute name="body">

	<table class="table">
	
		<tr>
			<th>Id</th>
			<th>Data de Emprestimo</th>
			<th>Data de Entrega</th>
			<th>Aluno</th>
			<th>Professor</th>
		</tr>
		
		<c:forEach var="emprestimo" items="${listEmprestimo}">
		
		<tr>
			<td> ${emprestimo.id} </td>
			<td> ${emprestimo.dataEmprestimo} </td>
			<td> ${emprestimo.dataEntrega} </td>
			<td> ${emprestimo.aluno.nome} </td>
			<td> ${emprestimo.professor.nome} </td>
		</tr>
		
		</c:forEach>
		
	</table>
	
	</jsp:attribute>
</fatec:genericpage>