<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Résultat du QCM Arel</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
	<body class="body">
		<div id="header"></div>
		<%@ page import="Modele.*" %>
		<h1 align="center">Réponse aux questions !!! =)</h1>
		<br/>
		<br/>
		<br/>
		<% QCM q = (QCM) request.getSession().getAttribute("QCM");%>
		<%
			for (int i = 0; i < q.getNbQuestions(); i++)
			{
			%>

			<div class="question"> <p><%= q.getQuestion(i).getExpression() %> </p> </div>
			<%
				for(int j = 0; j < q.getQuestion(i).getNbReponses(); j++)
				{
					if(q.getQuestion(i).getReponse(j).isSelect())
					{
					%>
			<p class="reponse">
				<%= q.getQuestion(i).getReponse(j).getExpression() %>
				<br/>
			</p>	
					<%		
					}
				}
					%>
			<br/>
			<br/>
					<%	
				for(int j = 0; j < q.getQuestion(i).getNbReponses(); j++)
				{
					if(q.getQuestion(i).getReponse(j).isTrue())
					{
					%>
			<p class="correction">
				<%= q.getQuestion(i).getReponse(j).getExpression() %>
				<br/>
			</p>
					<%
					}
				}
				if(q.getQuestion(i).evaluerReponses() && q.getQuestion(i).aRepondu())
				{
				%>
			<p class="vrai">
				Bravo, vous avez bien répondu !!<br/>
				<br/>
			</p>
				<%
				}
				if (!(q.getQuestion(i).aRepondu()))
				{
				%>
			<p class="faux">
				Vous n'avez pas répondu à cette question... <br/>
				<br/>
			</p>
				<%
				}
				else if (!(q.getQuestion(i).evaluerReponses()))
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

		<br/>
		<br/>
			<%= "Vous avez réalisé ce QCM avec un total de " + q.getScore() + " bonnes réponses sur " + q.getNbQuestions() + "." %>
		<br/>
		<br/>
		<form method="get" action="ControleurResultQCM">
			<table>
				<tr>
					<td align="center">
						<button width="100%" class="save_button blue styled_button" type="submit" name="ordre" value="Enregistrer">Enregistrer le résultat</button>
					</td>
				</tr>
				<tr>
					<td align="center">
						<button width="100%" class="back_button blue styled_button" type="submit" name="ordre" value="Retour">Retour</button> 
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>