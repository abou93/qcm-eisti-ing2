<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Affichage des résultats</title>
<style type="text/css">	<%@ include file="style.css" %>	</style>
</head>
<body>
<h1>Choisissez les résultats que vous voulez afficher</h1>
<%
if (((ArrayList<String>) request.getSession().getAttribute("liste")).size() == 0) {
	%>Pas d'utilisateurs<%;}
else {%>
	<form method="post" action="ControleurResultats">
		<select name="sexe">
			<%
			for (int i = 0; i < ((ArrayList<String>) request.getSession().getAttribute("liste")).size(); i++) {
			%>
			<option value="<%=((ArrayList<String>) request.getSession().getAttribute("liste")).get(i) %>"><%=((ArrayList<String>) request.getSession().getAttribute("liste")).get(i) %></option>
			<%} %>
		</select>
		<br/><br/>
		<input class="bouton" type="submit" name="choix" value="Valider"/>
	</form>
<%}
%>
<br/><br/>
<a href="accueil.jsp">Accueil</a>
</body>
</html>