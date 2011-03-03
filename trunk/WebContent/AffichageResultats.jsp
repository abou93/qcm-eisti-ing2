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

<%@ page import="Modele.*" %>
<%
// On affiche les choix, ils ne sont pas finis
if (request.getParameter("fini") == null) {%>
	<h1>Choisissez les résultats que vous voulez afficher</h1>
	<%
	if (((ArrayList<String>) request.getSession().getAttribute("liste")).size() == 0) {
		%>Aucun QCM n'a été réalisé jusqu'à présent.<%;}
	else {%>
		<form <%if ((Boolean)request.getSession().getAttribute("estProf")) { %>method="get"
		<%} else { %> method="post" <%} %> action="ControleurResultats">
			<select name="choix">
				<%
				for (int i = 0; i < ((ArrayList<String>) request.getSession().getAttribute("liste")).size(); i++) {
				%>
				<option value="<%=((ArrayList<String>) request.getSession().getAttribute("liste")).get(i) %>"><%=((ArrayList<String>) request.getSession().getAttribute("liste")).get(i) %></option>
				<%} %>
			</select>
			<%if (request.getSession().getAttribute("choixUser") != null) {%>
				<input type="text" name="fini" value="<%=request.getSession().getAttribute("choixUser") %>"/>
			<%} %>
			<br/><br/>
			<input class="bouton" type="submit" name="valider" value="Valider"/>
		</form>
	<%}
}
// On affiche le résultat
else {%>
	<h4>Réusltat de 
	<%=request.getSession().getAttribute("choixUser") %> 
	au QCM : 
	<%=((Modele)request.getSession().getAttribute("m")).getResultatCourant().getNom() %>
	</h4>
	Score = 
	<%=((Modele)request.getSession().getAttribute("m")).getResultatCourant().getScore() %> 
<%-- <%@ page import="Modele.*" %> --%>
<!-- 		<h1 align="center">Réponse aux questions !!! =)</h1> -->
<!-- 		<br/> -->
<!-- 		<br/> -->
<!-- 		<br/> -->
<%-- 		<% --%>
<!-- 			for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getResultatCourant().getNbQuestions(); i++) -->
<!-- 			{ -->
<!-- 			%> -->
<!-- 		<table> -->
<%-- 			<p class="question"> Q <%=i%><%= ((Modele)request.getSession().getAttribute("m")).getResultatCourant().getQuestion(i).getExpression() %> </p> --%>
<%-- 			<% --%>
<!-- 				for(int j = 0; j < ((Modele)request.getSession().getAttribute("m")).getResultatCourant().getQuestion(i).getNbReponses(); j++) -->
<!-- 				{ -->
<!-- 					%> -->
<!-- 				<p class="reponse"> -->
<!-- 					Réponse donnée :  -->
<%-- 					<%= ((Modele)request.getSession().getAttribute("m")).getResultatCourant().getQuestion(i).getReponse(j).getExpression() %> --%>
<%-- 					<%if(((Modele)request.getSession().getAttribute("m")).getResultatCourant().getQuestion(i).getReponse(j).isTrue()) --%>
<!-- 						{%> -->
<!-- 						<p class="vrai"> -->
<!-- 							Bonne Réponse -->
<!-- 						</p> -->
<%-- 						<%} --%>
<!-- 					else {%> -->
<!-- 					<p class="correction"> -->
<!-- 						Réponse Fausse -->
<!-- 					</p> -->
<%-- 					<%} %> --%>
<!-- 				</p>	 -->
<%-- 			<% } %> --%>
<!-- 			<br/> -->
<!-- 		</table> -->
<%-- 		<% } %> --%>
<!-- 		<br/> -->
<!-- 		<br/> -->
<%-- 		<%=request.getSession().getAttribute("choixUser") + " a réalisé(e) ce QCM avec un total de " + ((Modele)request.getSession().getAttribute("m")).getResultatCourant().getScore() + " bonnes réponses sur " + ((Modele)request.getSession().getAttribute("m")).getResultatCourant().getNbQuestions() + "." %> --%>
<!-- 		<br/> -->
<!-- 		<br/> -->


	<%} %>
<br/><br/>
<a href="accueil.jsp">Accueil</a>
</body>
</html>