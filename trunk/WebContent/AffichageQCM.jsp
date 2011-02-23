<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="style.css" rel="stylesheet" type="text/css" />
		<title>QCM Arel de <%= ((Modele)request.getSession().getAttribute("m")).getNomQCMCourant() %></title>
	</head>
	<body>
		<%@ page import="Modele.*" %>
		<h1 align="center">Répondez aux questions !!! =)</h1>
		<br/>
		<br/>
		<br/>
		<form method="post" action="ControleurSelectQCM">
		<%
			for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions(); i++)
			{
			%>
			<table>
				<p class=question> <%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getExpression() %> </p>
				<%
					for (int j = 0; j < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getNbReponse(); j++)
					{
					%>
				<tr>
					<td class=choix>
						<input type="radio" name= "<%= "q" +  i %>" value= "<%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).getExpression() %>" >
							<%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).getExpression() %>
						</input>
					</td>
				</tr>
					<%
					}
					%>
			</table>
			<br/>
			<%
			}
			%>
			<tr>
				<td align="center">
					<input class="bouton" type="submit" name="Submit" value= "<%= ("Valider ").concat(((Modele)request.getSession().getAttribute("m")).getNomQCMCourant()) %>" />
				</td>
			</tr>
		</form>
	</body>
</html>