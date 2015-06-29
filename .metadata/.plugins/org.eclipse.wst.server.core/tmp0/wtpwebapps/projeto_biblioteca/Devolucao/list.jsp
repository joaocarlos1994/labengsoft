<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fatec:genericpage title="Alunos Cadastrados">
	<jsp:attribute name="body">

	<table class="table">
	
		<tr>
			<th>Id</th>
			<th>Data de Devolução</th>
			<th>Exemplar</th>
			<th>Empréstimo</th>
		</tr>
		
		<c:forEach var="devolucao" items="${listDevolucao}">
		
		<tr>
			<td> ${devolucao.id} </td>
			<td> ${devolucao.dataDevolucao} </td>
			<td> ${devolucao.exemplar.nome} </td>
			<td> ${devolucao.emprestimo.nome} </td>
		</tr>
		
		</c:forEach>
		
	</table>
	
	</jsp:attribute>
</fatec:genericpage>