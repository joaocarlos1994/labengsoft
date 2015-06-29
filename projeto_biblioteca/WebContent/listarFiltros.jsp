<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<c:forEach var="filtro" items="${alunos}">
		<p>${filtro.nome}</p>
	</c:forEach>
</body>
</html>