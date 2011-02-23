<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="modele.Personne"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/TD3_MVC/style.css" rel="stylesheet" type="text/css" />
<title>TD3 JEE - MVC</title>
</head>
<body>
<h1>Affichage des données saisies</h1>
<% Personne p = (Personne)request.getAttribute("personne"); %>
<table>
	<tr><td>Nom</td><td><%= p.getNom() %></td></tr>
	<tr><td>Prénom</td><td><%= p.getPrenom() %></td></tr>
	<tr><td>Sexe</td><td><%= p.getSexe() %></td></tr>
	<%
		if (p.getTransport() != null) {
			%><tr><td>Transport</td><td><%= p.getTransport().get(0) %></td></tr>
			<%
			for (int i = 1; i < p.getTransport().size(); i++) {
				%><tr><td></td><td><%= p.getTransport().get(i) %>
			<%}
		}
	%>
	<tr><td>Code postal</td><td><%= p.getCodePostal() %></td></tr>
</table>
<br/>
<%
request.removeAttribute("personne");
request.removeAttribute("erreurs");
%>
<a href="index.jsp">Retour au formulaire</a>
</body>
</html>