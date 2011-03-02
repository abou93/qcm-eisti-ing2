<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style type="text/css">	<%@ include file="style.css" %>	</style>
		<title>Résultat du QCM Arel de <%= ((Modele)request.getSession().getAttribute("m")).getNomQCMCourant() %></title>
	</head>
	<body>
		<%@ page import="Modele.*" %>
		<h1 align="center">Réponse aux questions !!! =)</h1>
		<br/>
		<br/>
		<br/>
		<%
			for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions(); i++)
			{
			%>
		<table>
			<p class="question"> <%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getExpression() %> </p>
			<%
				for(int j = 0; j < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getNbReponses(); j++)
				{
					if(((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).isSelect())
					{
					%>
			<p class="reponse">
				<%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).getExpression() %>
				<br/>
			</p>	
					<%		
					}
				}
					%>
			<br/>
			<br/>
					<%	
				for(int j = 0; j < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getNbReponses(); j++)
				{
					if(((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).isTrue())
					{
					%>
			<p class="correction">
				<%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).getExpression() %>
				<br/>
			</p>
					<%
					}
				}
				if(((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).evaluerReponses() && ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).aRepondu())
				{
				%>
			<p class="vrai">
				Bravo, vous avez bien répondu !!<br/>
				<br/>
			</p>
				<%
				}
				if (!(((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).aRepondu()))
				{
				%>
			<p class="faux">
				Vous n'avez pas répondu à cette question... <br/>
				<br/>
			</p>
				<%
				}
				else if (!(((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).evaluerReponses()))
				{
				%>
			<p class="faux">
				Vous vous êtes trompé, retournez réviser... <br/>
				<br/>
			</p>
				<%
				}
			}
			%>
		</table>
		<br/>
		<br/>
			<%= "Vous avez réalisé ce QCM avec un total de " + ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getScore() + " bonnes réponses sur " + ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions() + "." %>
		<br/>
		<br/>
		<form method="get" action="ControleurResultQCM">
			<table>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="ordre" value="Enregistrer"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="ordre" value="Retour"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>