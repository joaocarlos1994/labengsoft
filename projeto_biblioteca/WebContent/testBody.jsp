
<%
	String a = "Isso é um scriplet";
%>
<jsp:scriptlet>String b = "Isso é um outro scriplet";</jsp:scriptlet>

<jsp:scriptlet>out.println(a + "\n");
			out.println(b);</jsp:scriptlet>