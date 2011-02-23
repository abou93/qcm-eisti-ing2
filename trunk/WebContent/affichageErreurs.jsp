<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Vector"
    import="modele.Personne"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/TD3_MVC/style.css" rel="stylesheet" type="text/css" />
<title>TD3 JEE - MVC</title>
</head>
<body>
<h1>Vous avez mal saisi les informations</h1>
<ul>
<%
	Vector<String> erreurs = (Vector<String>)request.getAttribute("erreur"); 
	for (String s : erreurs) {
		%><li>
		<%= s %></li>
		<%
	}
%>
</ul>
<br/>
<%
Personne p = (Personne) request.getAttribute("personne");
RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
request.setAttribute("personne", p);
dispatch.forward(request, response);
%>
</body>
</html>