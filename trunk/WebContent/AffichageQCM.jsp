<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 	Modele m = (Modele)request.getSession().getAttribute("m");
	QCM qcm = m.trouver((String)request.getSession().getAttribute("QCM")); %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="style.css" rel="stylesheet" type="text/css" />
		<title>QCM Arel de <%= qcm.getNom() %></title>
	</head>
	<body>
		<%@ page import="Modele.*" %>
		<h1 align="center">Répondez aux questions !!! =)</h1>
		<br/>
		<br/>
		<br/>
		<form method="post" action="ControleurSelectQCM">
		<%
			for (int i = 0; i < qcm.getNbQuestions(); i++)
			{
			%>
			<table>
				<p class=question> Question n°<%=i%>
				<% Question q = qcm.getLesQuestions().get(i); %>
				<br/><%= q.getQuestion() %> </p>
				<%
					for (int j = 0; j < q.getNbReponse(); j++)
					{
					%>
				<tr>
					<td class=choix>
						<input type="radio" name= "<%= "q" +  i %>" value= "<%= q.getReponse(j) %>" >
							<%= q.getReponse(j) %>
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
					<input class="bouton" type="submit" name="Submit" value= "<%= ("Valider ").concat(qcm.getNom()) %>" />
				</td>
			</tr>
		</form>
	</body>
</html>