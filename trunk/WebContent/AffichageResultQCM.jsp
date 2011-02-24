<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="style.css" rel="stylesheet" type="text/css" />
		<title>R�sultat du QCM Arel de <%= ((Modele)request.getSession().getAttribute("m")).getNomQCMCourant() %></title>
	</head>
	<body>
		<%@ page import="Modele.*" %>
		<h1 align="center">R�ponse aux questions !!! =)</h1>
		<br/>
		<br/>
		<br/>
		<%
			String reponse;
			int nbBonnesReponses = 0;
			for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions(); i++)
			{
			%>
		<table>
			<p class=question> <%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getExpression() %> </p>
			<%
				reponse = request.getParameter("q" + i);
				if (((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse().getExpression().matches(reponse))
				{
				%>
			<p class=vrai> <%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse().getExpression() %> </p>
			Bravo, vous avez bien r�pondu !!<br/>
				<%	
					nbBonnesReponses++;
				}
				else
				{
				%>
			<p class=faux> <%=((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse().getExpression() %> </p>
			Vous vous �tes tromp�, retournez r�viser...<br />
				<%
				}
			}
			%>
		</table>
		<br/>
		<br/>
			<%= "Vous avez r�alis� ce QCM avec un total de " + nbBonnesReponses + " bonnes r�ponses sur " + ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions() + "." %>
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