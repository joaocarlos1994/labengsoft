
<%
	String a = "Isso � um scriplet";
%>
<jsp:scriptlet>String b = "Isso � um outro scriplet";</jsp:scriptlet>

<jsp:scriptlet>out.println(a + "\n");
			out.println(b);</jsp:scriptlet>