<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 	Modele m = (Modele)request.getSession().getAttribute("m");
	QCM qcm = m.trouver((String)request.getSession().getAttribute("QCM")); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="style.css" rel="stylesheet" type="text/css" />
		<title>Résultat du QCM Arel de <%= qcm.getNom() %></title>
	</head>
	<body>
		<%@ page import="Modele.*" %>
		<h1 align="center">Réponse aux questions !!! =)</h1>
		<br/>
		<br/>
		<br/>
		<%
			Reponse r;
			int nbBonnesReponses = 0;
			for (int i = 0; i < qcm.getNbQuestions(); i++)
			{
			%>
		<table>
			<% Question q = qcm.getLesQuestions().get(i); %>
			<p class=question> <%= q.getQuestion() %> </p>
			<%
				r = (Reponse) (request.getParameter("q" + i));
				if (q.getReponse() == r)
				{
				%>
					<p class=vrai> <%= r.toString() %> </p>
					Bravo, vous avez bien répondu !!<br/>
				<%	
					nbBonnesReponses++;
				}
				else
				{
				%>
					<p class=faux> <%=r.toString() %> </p>
					Vous vous êtes trompé, retournez réviser...<br />
				<%
				}
			}
			%>
		</table>
		<br/>
		<br/>
			<%= "Vous avez réalisé ce QCM avec un total de " + nbBonnesReponses + " bonnes réponses sur 10." %>
		<br/>
		<br/>
		<a href="http://localhost:8081/Application_L2/">Retour au choix des QCM's</a>
	</body>
</html>